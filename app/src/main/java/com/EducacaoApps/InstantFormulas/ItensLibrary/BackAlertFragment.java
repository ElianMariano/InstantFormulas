package com.EducacaoApps.InstantFormulas.ItensLibrary;

import android.content.DialogInterface;
import android.app.Dialog;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AlertDialog;

import com.EducacaoApps.InstantFormulas.formulas.R;

/*
Esta classe é usada para criar um AlertDialog para a Activity principal que pergunta ao usuário
se ele quer mesmo sair.
*/

public class BackAlertFragment extends DialogFragment{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder alrt = new AlertDialog.Builder(getActivity());
        alrt.setMessage(R.string.Back_P);
        alrt.setNegativeButton(R.string.Nao, null);
        alrt.setPositiveButton(R.string.Sim, new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
                getActivity().finishAffinity();
            }
        });

        return alrt.create();
    }

}
