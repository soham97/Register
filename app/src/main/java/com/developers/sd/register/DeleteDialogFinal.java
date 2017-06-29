package com.developers.sd.register;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import java.util.UUID;

public class DeleteDialogFinal extends DialogFragment {

    private static final String ARG_DELETE_NOTE_FINAL = "delete";
    public static final String EXTRA_DELETE_FINAL = "com.sd.android.note.delete";



    public DeleteDialogFinal newInstance(UUID Id) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DELETE_NOTE_FINAL, Id);
        DeleteDialogFinal fragment = new DeleteDialogFinal();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final UUID Id = (UUID) getArguments().getSerializable(ARG_DELETE_NOTE_FINAL);


        return new AlertDialog.Builder(getActivity())
                .setTitle("Do you want to delete this entire entry")
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Register c = new Register(Id);
                                sendResult(Activity.RESULT_OK, false);
                                getActivity().finish();
                            }
                        })
                .setNegativeButton(android.R.string.cancel,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                .create();
    }

    private void sendResult(int resultCode, Boolean deleted) {
        if (getTargetFragment() == null) {
            return; }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_DELETE_FINAL, deleted);
        getTargetFragment()
                .onActivityResult(getTargetRequestCode(), resultCode, intent);
    }
}
