package com.EducacaoApps.InstantFormulas.ItensLibrary;

import android.content.DialogInterface;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.example.company.formulas.R;

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
