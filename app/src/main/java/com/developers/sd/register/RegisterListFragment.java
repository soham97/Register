package com.developers.sd.register;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.io.IOException;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by sohamdeshmukh on 21/06/17.
 */

public class RegisterListFragment extends Fragment {

    private RecyclerView mRegisterRecyclerView;
    private RegisterAdapter mAdapter;
    private List<Register> mRegisters;
    private SearchView mSearchView;
    String TAG = "error";

    private PdfPCell cell;
    private Image bgImage;
    ListView list;
    private String path;
    private File dir;
    private File file;
    private DrawerLayout drawer;


    BaseColor myColor = WebColors.getRGBColor("#9E9E9E");
    BaseColor myColor1 = WebColors.getRGBColor("#757575");


    @Override
    public void onCreate(Bundle savedInstanceSate) {
        super.onCreate(savedInstanceSate);
        setHasOptionsMenu(true);
        path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/com.developers.sd.register/PDF Files";
        dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_list, container, false);

        mRegisterRecyclerView = (RecyclerView) view.findViewById(R.id.register_recycler_view);
        mRegisterRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        View v = getActivity().findViewById(R.id.drawer_layout);
        drawer = (DrawerLayout) v.findViewById(R.id.drawer_layout);

        final Toolbar tToolbar = (Toolbar) view.findViewById(R.id.tToolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(tToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.app_name);
        tToolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawer, tToolbar, R.string.openDrawer, R.string.closeDrawer) {
            @Override
            public void onDrawerClosed(View drawerView) {
                tToolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);
                super.onDrawerClosed(drawerView);
            }
            @Override
            public void onDrawerOpened(View drawerView) {
                tToolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);
                super.onDrawerOpened(drawerView);
            }
        };

        drawer.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

//        updateUI();
        return view;
    }

    private void updateUI() {
        Log.e(TAG,"updateui started");
        RegisterLab registerLab = RegisterLab.get(getActivity());
        Log.e(TAG,"updateui1 started");
        List<Register> registers = registerLab.getRegisters();
        Log.e(TAG,"updateui2 started");
        if (mAdapter == null) {
            Log.e(TAG,"updateui3 started");
            mAdapter = new RegisterAdapter(registers);
            mRegisterRecyclerView.setAdapter(mAdapter);
            Log.e(TAG,"recyclerview");
        } else {
            Log.e(TAG,"problem identified");
            mAdapter.setRegisters(registers);
            mAdapter.notifyDataSetChanged();
            Log.e(TAG,"else");
        }
    }

    private class RegisterHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private Register mRegister;
        private Button mDateButton;
        private TextView mProject;
        private TextView mVendor;
        private TextView mTeams;
        private TextView mTeamsTotal;
        private TextView mDC;
        private TextView mHV;
        private TextView mWorks;
        private TextView mUnknown;
        private TextView mDCCompl;
        private TextView mHVCompl;
        private TextView mRemarks;

        public RegisterHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mDateButton = (Button) itemView.findViewById(R.id.dateff);
            mProject = (TextView) itemView.findViewById(R.id.projectff);
            mVendor = (TextView) itemView.findViewById(R.id.vendorff);
            mTeams = (TextView) itemView.findViewById(R.id.teamsff);
            mTeamsTotal = (TextView) itemView.findViewById(R.id.total_teamsff);
            mDC = (TextView) itemView.findViewById(R.id.dcff);
            mHV = (TextView) itemView.findViewById(R.id.hvff);
            mWorks = (TextView) itemView.findViewById(R.id.workff);
            mUnknown = (TextView) itemView.findViewById(R.id.unknownff);
            mDCCompl = (TextView) itemView.findViewById(R.id.dc_completeff);
            mHVCompl = (TextView) itemView.findViewById(R.id.hv_completeff);
            mRemarks = (TextView) itemView.findViewById(R.id.remarksff);
        }


        public void bindRegister(Register register) {
            mRegister = register;
            mDateButton.setText(mRegister.getDate());
            mProject.setText(mRegister.getProject());
            mVendor.setText(mRegister.getVendor());
            mTeams.setText(mRegister.getTeams());
            mTeamsTotal.setText(mRegister.getTeamstotal());
            mDC.setText(mRegister.getDC());
            mHV.setText(mRegister.getHV());
            mWorks.setText(mRegister.getWork());
            mUnknown.setText(mRegister.getUnknown());
            mDCCompl.setText(mRegister.getDCCompl());
            mHVCompl.setText(mRegister.getHVCompl());
            mRemarks.setText(mRegister.getRemarks());
        }


        public void onClick(View v) {
            Intent intent = RegisterActivity.newIntent(getActivity(), mRegister.getId());
            startActivity(intent);
            mRegisterRecyclerView.invalidate();
        }
    }

    private class RegisterAdapter extends RecyclerView.Adapter<RegisterHolder> {

        private SparseBooleanArray selectedItems;
//        private List<Register> mRegisters;


        public RegisterAdapter(List<Register> registers) {
            Log.e(TAG,"does it return");
            mRegisters = registers;
            selectedItems = new SparseBooleanArray();
        }

        @Override
        public RegisterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_register, parent, false);
            return new RegisterHolder(view);
        }

        @Override
        public void onBindViewHolder(RegisterHolder holder, int position) {
//                  Note note = mNotes.get(getItemCount() - position -1);
            Register register = mRegisters.get(position);
//            Register register2 = mRegisters.get(getItemCount() - position -1);
            holder.bindRegister(register);
        }

        @Override
        public int getItemCount() {
            return mRegisters.size();
        }

        public void setRegisters(List<Register> registers) {
            mRegisters = registers;
            Log.e(TAG,"does it return2");
        }

        public Register removeItem(int position) {
            final Register register = mRegisters.remove(position);
            notifyItemRemoved(position);
            return register;
        }


        public void addItem(int position, Register register) {
            mRegisters.add(position, register);
            notifyItemInserted(position);
        }

        public void moveItem(int fromPosition, int toPosition) {
            final Register register = mRegisters.remove(fromPosition);
            mRegisters.add(toPosition, register);
            notifyItemMoved(fromPosition, toPosition);
        }

        public void toggleSelection (int position) {
            if (selectedItems.get(position, false)) {
                selectedItems.delete(position);
            } else {
                selectedItems.put(position, true);
            }
            notifyItemChanged(position);
        }

        public void clearSelections() {
            selectedItems.clear();
            notifyDataSetChanged();
        }

        public int getSelectedItemCount() { return  selectedItems.size(); }

        public List<Integer> getSelectedItems() {
            List<Integer> items = new ArrayList<>(selectedItems.size());
            for (int i=0; i<selectedItems.size(); i++) {
                items.add(selectedItems.keyAt(i));
            }
            return items;
        }

        public void animateTo(List<Register> registers) {
            applyAndAnimateRemovals(registers);
            applyAndAnimateAdditions(registers);
            applyAndAnimateMovedItems(registers);
        }


        private void applyAndAnimateRemovals(List<Register> newregisters) {
            for (int i = mRegisters.size() - 1; i >= 0; i--) {
                final Register register = mRegisters.get(i);
                if (!newregisters.contains(register)) {
                    removeItem(i);
                }
            }
        }


        private void applyAndAnimateAdditions(List<Register> newregisters) {
            for (int i = 0, count = newregisters.size(); i < count; i++) {
                final Register register = newregisters.get(i);
                if (!mRegisters.contains(register)) {
                    addItem(i, register);
                }
            }
        }

        private void applyAndAnimateMovedItems(List<Register> newregisters) {
            for (int toPosition = newregisters.size() - 1; toPosition >= 0; toPosition--) {
                final Register register = newregisters.get(toPosition);
                final int fromPosition = mRegisters.indexOf(register);
                if (fromPosition >= 0 && fromPosition != toPosition) {
                    moveItem(fromPosition, toPosition);
                }
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG,"before");
        updateUI();
        Log.e(TAG,"after");
    }

    @Override
    public void onPause() {
        super.onPause();
        updateUI();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_register_list, menu);
        Log.e(TAG,"is it working");
        MenuItem searchItem = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId()) {
            case R.id.action_add:
                Log.e(TAG,"hello1");
                Register register = new Register();
                Log.e(TAG,register.getId().toString());
                RegisterLab.get(getActivity()).addRegister(register);
                Log.e(TAG,"no probs");
                Intent intent = RegisterActivity.newIntent(getActivity(), register.getId());
                startActivity(intent);
                return true;

            case R.id.action_search:
                SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
                mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
                SearchView.OnQueryTextListener textChangeListener = new SearchView.OnQueryTextListener()
                {
                    @Override
                    public boolean onQueryTextChange(String query) {
                        if (query.matches("")) {
                            updateUI();
                        } else {
                            query = query.toLowerCase();
                            final List<Register> filteredRegisterList = new ArrayList<>();
                            for (Register register : mRegisters) {
                                final String text = register.getProject().toLowerCase();
                                final String text1 = register.getVendor().toLowerCase();
                                if (text.contains(query) || text1.contains(query)) {
                                    filteredRegisterList.add(register);
                                }
                            }
                            mAdapter.animateTo(filteredRegisterList);
                            mRegisterRecyclerView.scrollToPosition(0);
                        }

                        return true;
                    }
                    @Override
                    public boolean onQueryTextSubmit(String query)
                    {
//                updateUI();
                        mSearchView.clearFocus();
                        return true;
                    }
                };
                mSearchView.setOnQueryTextListener(textChangeListener);
                return true;

            case R.id.action_pdf:
                try {
                    createPDF();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
                return true;

            case R.id.action_delete_all:
                RegisterLab registerLab = RegisterLab.get(getActivity());
                registerLab.Delete_all();
                refreshFragment();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void createPDF() throws FileNotFoundException, DocumentException {

        //create document file
        Document doc = new Document();
        try {

            Log.e("PDFCreator", "PDF Path: " + path);
            SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy hh:mm a");
            file = new File(dir, "Report PDF" + sdf.format(Calendar.getInstance().getTime()) + ".pdf");
            FileOutputStream fOut = new FileOutputStream(file);
            PdfWriter writer = PdfWriter.getInstance(doc, fOut);

            //open the document
            doc.open();
            doc.add(new Chunk(""));

//create table
            PdfPTable pt = new PdfPTable(3);
            pt.setWidthPercentage(100);
            float[] fl = new float[]{20, 45, 35};
            pt.setWidths(fl);
            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);

            //set drawable in cell
            Drawable myImage = getActivity().getResources().getDrawable(R.drawable.siemens);
            Bitmap bitmap = ((BitmapDrawable) myImage).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] bitmapdata = stream.toByteArray();
            try {
                bgImage = Image.getInstance(bitmapdata);
                bgImage.setAbsolutePosition(330f, 642f);
                cell.addElement(new Paragraph(""));
                cell.addElement(bgImage);
                pt.addCell(cell);
                cell = new PdfPCell();
                cell.setBorder(Rectangle.NO_BORDER);
                cell.addElement(new Paragraph(""));

                cell.addElement(new Paragraph(""));
                cell.addElement(new Paragraph(""));
                pt.addCell(cell);
                cell = new PdfPCell(new Paragraph(""));
                cell.setBorder(Rectangle.NO_BORDER);
                pt.addCell(cell);

                PdfPTable pTable = new PdfPTable(1);
                pTable.setWidthPercentage(100);
                cell = new PdfPCell();
                cell.setColspan(1);
                cell.addElement(pt);
                pTable.addCell(cell);
                PdfPTable table = new PdfPTable(12);

                float[] columnWidth = new float[]{80, 120, 80, 80, 80, 80, 80, 120, 40, 60, 60, 80};
                table.setWidths(columnWidth);


                cell = new PdfPCell();


                cell.setBackgroundColor(myColor);
                cell.setColspan(12);
                cell.addElement(pTable);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(" "));
                cell.setColspan(12);
                table.addCell(cell);
                cell = new PdfPCell();
                cell.setColspan(12);

                cell.setBackgroundColor(myColor1);

//                cell = new PdfPCell(new Phrase("Sr"));
//                cell.setBackgroundColor(myColor1);
//                table.addCell(cell);
                cell = new PdfPCell(new Phrase("Date"));
                cell.setBackgroundColor(myColor1);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("Project"));
                cell.setBackgroundColor(myColor1);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("Vendor"));
                cell.setBackgroundColor(myColor1);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("Teams"));
                cell.setBackgroundColor(myColor1);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("Teamstotal"));
                cell.setBackgroundColor(myColor1);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("DC"));
                cell.setBackgroundColor(myColor1);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("HV"));
                cell.setBackgroundColor(myColor1);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("Work"));
                cell.setBackgroundColor(myColor1);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("Sec kit"));
                cell.setBackgroundColor(myColor1);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("DC Compl"));
                cell.setBackgroundColor(myColor1);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("HV Compl"));
                cell.setBackgroundColor(myColor1);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("Remarks"));
                cell.setBackgroundColor(myColor1);
                table.addCell(cell);

                //table.setHeaderRows(3);
                cell = new PdfPCell();
                cell.setColspan(12);

//retriving data from database
                RegisterLab registerLab = RegisterLab.get(getActivity());

                List<String> adate = registerLab.RegisterDate();
                List<String> aproject = registerLab.RegisterProject();
                List<String> avendor = registerLab.RegisterVendor();
                List<String> ateams = registerLab.RegisterTeams();
                List<String> ateamstotal = registerLab.RegisterTeamstotal();
                List<String> adc = registerLab.RegisterDC();
                List<String> ahv = registerLab.RegisterHV();
                List<String> awork = registerLab.RegisterWork();
                List<String> aunknown = registerLab.RegisterUnknown();
                List<String> adccompl = registerLab.RegisterDCCompl();
                List<String> ahvcompl = registerLab.RegisterHVCompl();
                List<String> aremarks = registerLab.RegisterRemarks();

                int sdc = 0;
                for (int k=0; k<=adc.size()-1; k++)
                {
                    if(adc.get(k) != null) {
                        sdc += Integer.parseInt(adc.get(k));
                    }
                }

                int shv = 0;
                for (int k=0; k<=adc.size()-1; k++)
                {
                    if(adc.get(k) != null) {
                        shv += Integer.parseInt(ahv.get(k));
                    }
                }

                int sdccompl = 0;
                for (int k=0; k<=adc.size()-1; k++)
                {
                    if(adc.get(k) != null) {
                        sdccompl += Integer.parseInt(adccompl.get(k));
                    }
                }

                int shvcompl = 0;
                for (int k=0; k<=adc.size()-1; k++)
                {
                    if(adc.get(k) != null) {
                        shvcompl += Integer.parseInt(ahvcompl.get(k));
                    }
                }

                int sunknown = 0;
                for (int k=0; k<=adc.size()-1; k++)
                {
                    if(adc.get(k) != null) {
                        sunknown += Integer.parseInt(aunknown.get(k));
                    }
                }


                for (int i = 0; i <= adate.size() - 1; i++) {
//                    table.addCell(String.valueOf(i));
                    table.addCell(adate.get(i));
                    table.addCell(aproject.get(i));
                    table.addCell(avendor.get(i));
                    table.addCell(ateams.get(i));
                    table.addCell(ateamstotal.get(i));
                    table.addCell(adc.get(i));
                    table.addCell(ahv.get(i));
                    table.addCell(awork.get(i));
                    table.addCell(aunknown.get(i));
                    table.addCell(adccompl.get(i));
                    table.addCell(ahvcompl.get(i));
                    table.addCell(aremarks.get(i));
                }

//                for (int i = 1; i <= 10; i++) {
////                    table.addCell(String.valueOf(i));
//                    table.addCell("Header 1 row " + i);
//                    table.addCell("Header 2 row " + i);
//                    table.addCell("Header 3 row " + i);
//                    table.addCell("Header 4 row " + i);
//                    table.addCell("Header 5 row " + i);
//                    table.addCell("Header 6 row " + i);
//                    table.addCell("Header 7 row " + i);
//                    table.addCell("Header 8 row " + i);
//                    table.addCell("Header 9 row " + i);
//                    table.addCell("Header 10 row " + i);
//                    table.addCell("Header 11 row " + i);
//                    table.addCell("Header 12 row " + i);
//                }

                PdfPTable ftable = new PdfPTable(12);
                ftable.setWidthPercentage(100);
                float[] columnWidthaa = new float[]{80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80};
                ftable.setWidths(columnWidthaa);
                cell = new PdfPCell();
                cell.setColspan(12);
                cell.setBackgroundColor(myColor1);
                cell = new PdfPCell(new Phrase("Total"));
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setBackgroundColor(myColor1);
                ftable.addCell(cell);
                cell = new PdfPCell(new Phrase(""));
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setBackgroundColor(myColor1);
                ftable.addCell(cell);
                cell = new PdfPCell(new Phrase(""));
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setBackgroundColor(myColor1);
                ftable.addCell(cell);
                cell = new PdfPCell(new Phrase(""));
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setBackgroundColor(myColor1);
                ftable.addCell(cell);
                cell = new PdfPCell(new Phrase(""));
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setBackgroundColor(myColor1);
                ftable.addCell(cell);
                cell = new PdfPCell(new Phrase("" + sdc));
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setBackgroundColor(myColor1);
                ftable.addCell(cell);
                cell = new PdfPCell(new Phrase("" + shv));
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setBackgroundColor(myColor1);
                ftable.addCell(cell);
                cell = new PdfPCell(new Phrase(""));
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setBackgroundColor(myColor1);
                ftable.addCell(cell);
                cell = new PdfPCell(new Phrase("" + sunknown));
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setBackgroundColor(myColor1);
                ftable.addCell(cell);
                cell = new PdfPCell(new Phrase("" + sdccompl));
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setBackgroundColor(myColor1);
                ftable.addCell(cell);
                cell = new PdfPCell(new Phrase("" + shvcompl));
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setBackgroundColor(myColor1);
                ftable.addCell(cell);
                cell = new PdfPCell(new Phrase(""));
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setBackgroundColor(myColor1);
                ftable.addCell(cell);
                cell = new PdfPCell(new Paragraph("Footer"));
                cell.setColspan(12);
                ftable.addCell(cell);
                cell = new PdfPCell();
                cell.setColspan(12);
                cell.addElement(ftable);
                table.addCell(cell);
                doc.add(table);
                Toast.makeText(getActivity().getApplicationContext(), "PDF created", Toast.LENGTH_LONG).show();
            } catch (DocumentException de) {
                Log.e("PDFCreator", "DocumentException:" + de);
            } catch (IOException e) {
                Log.e("PDFCreator", "ioException:" + e);
            } finally {
                doc.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refreshFragment()
    {
        Fragment currentFragment = getFragmentManager().findFragmentById(R.id.fragment_container);
        FragmentTransaction fragTransaction = getFragmentManager().beginTransaction();
        fragTransaction.detach(currentFragment);
        fragTransaction.attach(currentFragment);
        fragTransaction.commit();
    }
}
