package com.developers.sd.register;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by sohamdeshmukh on 22/06/17.
 */

public class RegisterFragment extends Fragment {

    private static final String ARG_REGISTER_ID = "register_id";
    private static final String DIALOG_DELETE_FINAL = "DialogDeleteFinal";
    private static final int REQUEST_DELETE_FINAL = 23;


    private Register mRegister;
    private Button mDateButton;
    private EditText mProject;
    private EditText mVendor;
    private EditText mTeams;
    private EditText mTeamsTotal;
    private EditText mDC;
    private EditText mHV;
    private EditText mWorks;
    private EditText mUnknown;
    private EditText mDCCompl;
    private EditText mHVCompl;
    private EditText mRemarks;


    public static RegisterFragment newInstance(UUID registerId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_REGISTER_ID, registerId);
        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID registerId = (UUID) getArguments().getSerializable(ARG_REGISTER_ID);
        mRegister = RegisterLab.get(getActivity()).getRegister(registerId);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_register, container, false);

        Toolbar tToolbar = (Toolbar) v.findViewById(R.id.tToolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(tToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.app_name);

        mDateButton = (Button) v.findViewById(R.id.dateF);
        mDateButton.setText(mRegister.getDate());
        mDateButton.setEnabled(false);


        mProject = (EditText) v.findViewById(R.id.projectF);
        mProject.setText(mRegister.getProject());
        mProject.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mRegister.setProject(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This one too
            }
        });

        mVendor = (EditText) v.findViewById(R.id.vendorF);
        mVendor.setText(mRegister.getVendor());
        mVendor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mRegister.setVendor(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This one too
            }
        });

        mTeams = (EditText) v.findViewById(R.id.teamsF);
        mTeams.setText(mRegister.getTeams());
        mTeams.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mRegister.setTeams(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This one too
            }
        });

        mTeamsTotal = (EditText) v.findViewById(R.id.teamstotalF);
        mTeamsTotal.setText(mRegister.getTeamstotal());
        mTeamsTotal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mRegister.setTeamstotal(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This one too
            }
        });

        mDC = (EditText) v.findViewById(R.id.dcF);
        mDC.setText(mRegister.getDC());
        mDC.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                String c = s.toString();
                mRegister.setDC(c);
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This one too
            }
        });

        mHV = (EditText) v.findViewById(R.id.hvF);
        mHV.setText(mRegister.getHV());
        mHV.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                String c = s.toString();
                mRegister.setHV(c);
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This one too
            }
        });

        mWorks = (EditText) v.findViewById(R.id.workF);
        mWorks.setText(mRegister.getWork());
        mWorks.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mRegister.setWork(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This one too
            }
        });

        mUnknown = (EditText) v.findViewById(R.id.unknownF);
        mUnknown.setText(mRegister.getUnknown());
        mUnknown.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mRegister.setUnknown(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This one too
            }
        });

        mDCCompl = (EditText) v.findViewById(R.id.dc_completeF);
        mDCCompl.setText(mRegister.getDCCompl());
        mDCCompl.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                String c = s.toString();
                mRegister.setDCCompl(c);
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This one too
            }
        });

        mHVCompl = (EditText) v.findViewById(R.id.hv_completeF);
        mHVCompl.setText(mRegister.getHVCompl());
        mHVCompl.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                String c = s.toString();
                mRegister.setHVCompl(c);
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This one too
            }
        });

        mRemarks = (EditText) v.findViewById(R.id.remarksF);
        mRemarks.setText(mRegister.getRemarks());
        mRemarks.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mRegister.setRemarks(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This one too
            }
        });

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_register, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_delete_register:
                    FragmentManager manager = getFragmentManager();
                    DeleteDialogFinal dialog = new DeleteDialogFinal().newInstance(mRegister.getId());
                    dialog.setTargetFragment(RegisterFragment.this, REQUEST_DELETE_FINAL);
                    dialog.show(manager, DIALOG_DELETE_FINAL);
                    return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if(requestCode==REQUEST_DELETE_FINAL){
            RegisterLab.get(getActivity()).deleteRegister(mRegister);
        }
    }

    @Override
    public void onPause() {
        RegisterLab.get(getActivity()).updateRegister(mRegister);
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        RegisterLab.get(getActivity()).updateRegister(mRegister);
    }

    @Override
    public void onStop() {
        RegisterLab.get(getActivity()).updateRegister(mRegister);
        super.onStop();
    }

}
