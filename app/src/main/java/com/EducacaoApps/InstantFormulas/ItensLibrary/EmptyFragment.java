package com.EducacaoApps.InstantFormulas.ItensLibrary;

import android.app.Dialog;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AlertDialog;

import com.EducacaoApps.InstantFormulas.formulas.R;

public class EmptyFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder alrt = new AlertDialog.Builder(getActivity());
        alrt.setMessage(R.string.isEmpty);
        alrt.setPositiveButton(R.string.ok, null);

        return alrt.create();
    }
}
