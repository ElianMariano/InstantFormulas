package com.EducacaoApps.InstantFormulas.ItensLibrary;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

import com.EducacaoApps.InstantFormulas.formulas.R;

/*
AlertDialog usado quando o dado obtido pelo usuário for inválido
*/
public class IvalidDataFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alrt = new AlertDialog.Builder(getActivity());
        alrt.setMessage(R.string.dado_invalido);
        alrt.setPositiveButton(R.string.ok, null);

        return alrt.create();
    }
}
