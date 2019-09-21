package com.company.app.ItensLibrary;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.example.company.formulas.R;

public class EmptyFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder alrt = new AlertDialog.Builder(getActivity());
        alrt.setMessage(R.string.isEmpty);
        alrt.setPositiveButton(R.string.ok, null);

        return alrt.create();
    }
}
