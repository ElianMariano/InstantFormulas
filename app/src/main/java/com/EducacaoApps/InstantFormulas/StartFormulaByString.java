package com.EducacaoApps.InstantFormulas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import com.example.company.formulas.R;

/*
   Esta classe Inicia uma activity através de um método estático que tomam uma activity e uma String
   de título como parámetro. Esta classe é usada pelos Itens "HistóricoItem" e "FavoriteItem".
*/
public class StartFormulaByString {

    // Método usado no FavoriteItem
    public static void Start(AppCompatActivity activity, String titulo){
        // Formulas de matemática
        if (titulo.equals(activity.getResources().getString(R.string.regra_de_tres))){
            Intent in = new Intent(activity,
                    com.EducacaoApps.InstantFormulas.formulas.matematica.regra_tres.class);
            activity.startActivity(in);
            activity.finish();
        }
        else if (titulo.equals(activity.getResources().getString(R.string.area_q))){
            Intent in = new Intent(activity,
                    com.EducacaoApps.InstantFormulas.formulas.matematica.area_q.class);
            activity.startActivity(in);
            activity.finish();
        }
        else if (titulo.equals(activity.getResources().getString(R.string.area_t))){
            Intent in = new Intent(activity,
                    com.EducacaoApps.InstantFormulas.formulas.matematica.area_t.class);
            activity.startActivity(in);
            activity.finish();
        }
        else if (titulo.equals(activity.getResources().getString(R.string.comp_circulo))){
            Intent in = new Intent(activity,
                    com.EducacaoApps.InstantFormulas.formulas.matematica.comp_circulo.class);
            activity.startActivity(in);
            activity.finish();
        }
        else if (titulo.equals(activity.getResources().getString(R.string.pitagoras))){
            Intent in = new Intent(activity,
                    com.EducacaoApps.InstantFormulas.formulas.matematica.pitagoras.class);
            activity.startActivity(in);
            activity.finish();
        }

        // Formulas de química
        if (titulo.equals(activity.getResources().getString(R.string.densidade))){
            Intent in = new Intent(activity,
                    com.EducacaoApps.InstantFormulas.formulas.quimica.densidade.class);
            activity.startActivity(in);
            activity.finish();
        }
        else if (titulo.equals(activity.getResources().getString(R.string.velocidade_media))){
            Intent in = new Intent(activity,
                    com.EducacaoApps.InstantFormulas.formulas.quimica.velocidade_media.class);
            activity.startActivity(in);
            activity.finish();
        }
        else if (titulo.equals(activity.getResources().getString(R.string.variacao_entalpia))){
            Intent in = new Intent(activity,
                    com.EducacaoApps.InstantFormulas.formulas.quimica.variacao_entalpia.class);
            activity.startActivity(in);
            activity.finish();
        }
        else if (titulo.equals(activity.getResources().getString(R.string.energia_ativacao))){
            Intent in = new Intent(activity,
                    com.EducacaoApps.InstantFormulas.formulas.quimica.energia_ativacao.class);
            activity.startActivity(in);
            activity.finish();
        }

        // Formulas de física
        if (titulo.equals(activity.getResources().getString(R.string.dilat_linear))){
            Intent in = new Intent(activity,
                    com.EducacaoApps.InstantFormulas.formulas.fisica.dilatacao_linear.class);
            activity.startActivity(in);
            activity.finish();
        }
        else if (titulo.equals(activity.getResources().getString(R.string.velo_media))){
            Intent in = new Intent(activity,
                    com.EducacaoApps.InstantFormulas.formulas.fisica.velo_media.class);
            activity.startActivity(in);
            activity.finish();
        }
        else if (titulo.equals(activity.getResources().getString(R.string.acel_media))){
            Intent in = new Intent(activity,
                    com.EducacaoApps.InstantFormulas.formulas.fisica.acel_media.class);
            activity.startActivity(in);
            activity.finish();
        }
        else if (titulo.equals(activity.getResources().getString(R.string.equa_torricelli))){
            Intent in = new Intent(activity,
                    com.EducacaoApps.InstantFormulas.formulas.fisica.equa_torricelli.class);
            activity.startActivity(in);
            activity.finish();
        }
        else if (titulo.equals(activity.getResources().getString(R.string.equa_calorimetria))){
            Intent in = new Intent(activity,
                    com.EducacaoApps.InstantFormulas.formulas.fisica.equa_calorimetria.class);
            activity.startActivity(in);
            activity.finish();
        }
        else if (titulo.equals(activity.getResources().getString(R.string.clapeyron))){
            Intent in = new Intent(activity,
                    com.EducacaoApps.InstantFormulas.formulas.fisica.clapeyron.class);
            activity.startActivity(in);
            activity.finish();
        }
    }

    // Método usado no HistoricoItem
    public static void Start(AppCompatActivity activity, String titulo, String data){
        // Formulas de matemática
        if (titulo.equals(activity.getResources().getString(R.string.regra_de_tres))){
            Intent in = new Intent(activity,
                    com.EducacaoApps.InstantFormulas.formulas.matematica.regra_tres.class);

            // Adiciona os dados à activity
            in.putExtra("data", data);

            activity.startActivity(in);
            activity.finish();
        }
        else if (titulo.equals(activity.getResources().getString(R.string.area_q))){
            Intent in = new Intent(activity,
                    com.EducacaoApps.InstantFormulas.formulas.matematica.area_q.class);

            // Adiciona os dados à activity
            in.putExtra("data", data);

            activity.startActivity(in);
            activity.finish();
        }
        else if (titulo.equals(activity.getResources().getString(R.string.area_t))){
            Intent in = new Intent(activity,
                    com.EducacaoApps.InstantFormulas.formulas.matematica.area_t.class);

            // Adiciona os dados à activity
            in.putExtra("data", data);

            activity.startActivity(in);
            activity.finish();
        }
        else if (titulo.equals(activity.getResources().getString(R.string.comp_circulo))){
            Intent in = new Intent(activity,
                    com.EducacaoApps.InstantFormulas.formulas.matematica.comp_circulo.class);

            // Adiciona os dados à activity
            in.putExtra("data", data);

            activity.startActivity(in);
            activity.finish();
        }
        else if (titulo.equals(activity.getResources().getString(R.string.pitagoras))){
            Intent in = new Intent(activity,
                    com.EducacaoApps.InstantFormulas.formulas.matematica.pitagoras.class);

            // Adiciona os dados à activity
            in.putExtra("data", data);

            activity.startActivity(in);
            activity.finish();
        }

        // Formulas de química
        if (titulo.equals(activity.getResources().getString(R.string.densidade))){
            Intent in = new Intent(activity,
                    com.EducacaoApps.InstantFormulas.formulas.quimica.densidade.class);

            // Adiciona os dados à activity
            in.putExtra("data", data);

            activity.startActivity(in);
            activity.finish();
        }
        else if (titulo.equals(activity.getResources().getString(R.string.velocidade_media))){
            Intent in = new Intent(activity,
                    com.EducacaoApps.InstantFormulas.formulas.quimica.velocidade_media.class);

            // Adiciona os dados à activity
            in.putExtra("data", data);

            activity.startActivity(in);
            activity.finish();
        }
        else if (titulo.equals(activity.getResources().getString(R.string.variacao_entalpia))){
            Intent in = new Intent(activity,
                    com.EducacaoApps.InstantFormulas.formulas.quimica.variacao_entalpia.class);

            // Adiciona os dados à activity
            in.putExtra("data", data);

            activity.startActivity(in);
            activity.finish();
        }
        else if (titulo.equals(activity.getResources().getString(R.string.energia_ativacao))){
            Intent in = new Intent(activity,
                    com.EducacaoApps.InstantFormulas.formulas.quimica.energia_ativacao.class);

            // Adiciona os dados à activity
            in.putExtra("data", data);

            activity.startActivity(in);
            activity.finish();
        }

        // Formulas de física
        if (titulo.equals(activity.getResources().getString(R.string.dilat_linear))){
            Intent in = new Intent(activity,
                    com.EducacaoApps.InstantFormulas.formulas.fisica.dilatacao_linear.class);

            // Adiciona os dados à activity
            in.putExtra("data", data);

            activity.startActivity(in);
            activity.finish();
        }
        else if (titulo.equals(activity.getResources().getString(R.string.velo_media))){
            Intent in = new Intent(activity,
                    com.EducacaoApps.InstantFormulas.formulas.fisica.velo_media.class);

            // Adiciona os dados à activity
            in.putExtra("data", data);

            activity.startActivity(in);
            activity.finish();
        }
        else if (titulo.equals(activity.getResources().getString(R.string.acel_media))){
            Intent in = new Intent(activity,
                    com.EducacaoApps.InstantFormulas.formulas.fisica.acel_media.class);

            // Adiciona os dados à activity
            in.putExtra("data", data);

            activity.startActivity(in);
            activity.finish();
        }
        else if (titulo.equals(activity.getResources().getString(R.string.equa_torricelli))){
            Intent in = new Intent(activity,
                    com.EducacaoApps.InstantFormulas.formulas.fisica.equa_torricelli.class);

            // Adiciona os dados à activity
            in.putExtra("data", data);

            activity.startActivity(in);
            activity.finish();
        }
        else if (titulo.equals(activity.getResources().getString(R.string.equa_calorimetria))){
            Intent in = new Intent(activity,
                    com.EducacaoApps.InstantFormulas.formulas.fisica.equa_calorimetria.class);

            // Adiciona os dados à activity
            in.putExtra("data", data);

            activity.startActivity(in);
            activity.finish();
        }
        else if (titulo.equals(activity.getResources().getString(R.string.clapeyron))){
            Intent in = new Intent(activity,
                    com.EducacaoApps.InstantFormulas.formulas.fisica.clapeyron.class);

            // Adiciona os dados à activity
            in.putExtra("data", data);

            activity.startActivity(in);
            activity.finish();
        }
    }
}
