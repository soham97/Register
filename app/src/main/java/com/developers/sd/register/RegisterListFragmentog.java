package com.developers.sd.register;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sohamdeshmukh on 21/06/17.
 */

public class RegisterListFragmentog extends Fragment {

    private RecyclerView mRegisterRecyclerView;
    private RegisterAdapter mAdapter;
    private List<Register> mRegisters;
    private SearchView mSearchView;
    String TAG = "error";


    @Override
    public void onCreate(Bundle savedInstanceSate) {
        super.onCreate(savedInstanceSate);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_list, container, false);

        mRegisterRecyclerView = (RecyclerView) view.findViewById(R.id.register_recycler_view);
        mRegisterRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

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
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        updateUI();
//    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        Log.e(TAG,"hello");
        inflater.inflate(R.menu.fragment_register_list, menu);
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
                                if (text.contains(query)) {
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

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
