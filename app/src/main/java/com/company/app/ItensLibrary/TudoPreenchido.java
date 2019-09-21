package com.company.app.ItensLibrary;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.example.company.formulas.R;

// Alert dialog fragment usado quando todos oss campos de uma formula estiverem preenchidos
public class TudoPreenchido extends DialogFragment{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alrt = new AlertDialog.Builder(getActivity());
        alrt.setMessage(R.string.preenchido);
        alrt.setPositiveButton(R.string.ok, null);

        return alrt.create();
    }
}
