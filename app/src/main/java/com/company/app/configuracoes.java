package com.company.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.MenuItem;

import com.example.company.formulas.R;

public class configuracoes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);

        //Faz com que o botão voltar alinhe-se à direita
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true); //Ativar o botão
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        startActivity(new Intent(configuracoes.this, MainActivity.class));
        return true;
    }

    @Override
    public void onBackPressed(){
        Intent in = new Intent(configuracoes.this, MainActivity.class);
        startActivity(in);
    }
}
