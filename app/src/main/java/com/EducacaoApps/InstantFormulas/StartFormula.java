package com.EducacaoApps.InstantFormulas;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

/*
Classe usada para chamar a activy desejada de acordo com o enum formulas
*/

public class StartFormula {
    private AppCompatActivity activity;
    private Formulas formulas;

    public StartFormula(AppCompatActivity act, Formulas fm){
        activity = act;
        formulas = fm;
    }

    public void StartActivity(){
        switch (formulas){
            // Matemática
            case AREA_QUADRADO:
                Intent quadrado = new Intent(activity,
                        com.EducacaoApps.InstantFormulas.formulas.matematica.area_q.class);
                activity.startActivity(quadrado);
                break;
            case AREA_TRIANGULO:
                Intent triangulo = new Intent(activity,
                        com.EducacaoApps.InstantFormulas.formulas.matematica.area_t.class);
                activity.startActivity(triangulo);
                break;
            case COMPRIMENTO_CIRCULO:
                Intent circulo = new Intent(activity,
                        com.EducacaoApps.InstantFormulas.formulas.matematica.comp_circulo.class);
                activity.startActivity(circulo);
                break;
            case PITAGORAS:
                Intent pitagoras = new Intent(activity,
                        com.EducacaoApps.InstantFormulas.formulas.matematica.pitagoras.class);
                activity.startActivity(pitagoras);
                break;
            case REGRA_TRES:
                Intent regra_tres = new Intent(activity,
                        com.EducacaoApps.InstantFormulas.formulas.matematica.regra_tres.class);
                activity.startActivity(regra_tres);
                break;

            // Quimica
            case DENSIDADE:
                Intent densidade = new Intent(activity,
                        com.EducacaoApps.InstantFormulas.formulas.quimica.densidade.class);
                activity.startActivity(densidade);
                break;
            case ENERGIA_ATIVACAO:
                Intent energia_ativacao = new Intent(activity,
                        com.EducacaoApps.InstantFormulas.formulas.quimica.energia_ativacao.class);
                activity.startActivity(energia_ativacao);
                break;
            case VARIACAO_ENTALPIA:
                Intent variacao_entalpia = new Intent(activity,
                        com.EducacaoApps.InstantFormulas.formulas.quimica.variacao_entalpia.class);
                activity.startActivity(variacao_entalpia);
                break;
            case VELOCIADADE_MEDIA_REACAO:
                Intent velociade_media_reacao = new Intent(activity,
                        com.EducacaoApps.InstantFormulas.formulas.quimica.velocidade_media.class);
                activity.startActivity(velociade_media_reacao);
                break;

            // Física
            case ACELERACAO_MEDIA:
                Intent aceleracao_media = new Intent(activity,
                        com.EducacaoApps.InstantFormulas.formulas.fisica.acel_media.class);
                activity.startActivity(aceleracao_media);
                break;
            case CLAPEYRON:
                Intent clapeyron = new Intent(activity,
                        com.EducacaoApps.InstantFormulas.formulas.fisica.clapeyron.class);
                activity.startActivity(clapeyron);
                break;
            case DILATACAO_LINEAR:
                Intent d_linear = new Intent(activity,
                        com.EducacaoApps.InstantFormulas.formulas.fisica.dilatacao_linear.class);
                activity.startActivity(d_linear);
                break;
            case EQUACAO_CALORIMETRIA:
                Intent e_calorimetria = new Intent(activity,
                        com.EducacaoApps.InstantFormulas.formulas.fisica.equa_calorimetria.class);
                activity.startActivity(e_calorimetria);
                break;
            case EQUACAO_TORRICELI:
                Intent  e_torriceli = new Intent(activity,
                        com.EducacaoApps.InstantFormulas.formulas.fisica.equa_torricelli.class);
                activity.startActivity(e_torriceli);
                break;
            case VELOCIADADE_MEDIA:
                Intent v_media = new Intent(activity,
                        com.EducacaoApps.InstantFormulas.formulas.fisica.velo_media.class);
                activity.startActivity(v_media);
                break;

            default:
                activity.finish();
        }
    }
}
