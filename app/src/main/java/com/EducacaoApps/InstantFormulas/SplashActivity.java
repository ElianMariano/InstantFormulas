package com.EducacaoApps.InstantFormulas;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.EducacaoApps.InstantFormulas.formulas.R;

public class SplashActivity extends AppCompatActivity {
    //Tempo em milissegundos em que será mostrado a SplashScreen
    private int SPlASH_TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Handler que dá um delay para executar a instrução
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Termina a SplashScreen
                Intent in = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(in);
                finish();
            }
        }, SPlASH_TIME);
    }

    //Faz com que nada aconteça caso o botão voltar seja pressionado
    @Override
    public void onBackPressed(){

    }
}
