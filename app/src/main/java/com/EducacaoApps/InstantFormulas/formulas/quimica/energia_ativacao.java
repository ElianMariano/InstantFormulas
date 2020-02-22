package com.EducacaoApps.InstantFormulas.formulas.quimica;

import android.content.ContentValues;
import android.content.Intent;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.EducacaoApps.InstantFormulas.ConvertStringtoData;
import com.EducacaoApps.InstantFormulas.ItensLibrary.EmptyFragment;
import com.EducacaoApps.InstantFormulas.ItensLibrary.TudoPreenchido;
import com.EducacaoApps.InstantFormulas.Models.HistoricoHelper;
import com.EducacaoApps.InstantFormulas.form_choose;
import com.EducacaoApps.InstantFormulas.formulas.R;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class energia_ativacao extends AppCompatActivity {
    private EditText eat;
    private EditText ep;
    private EditText er;
    private TextView line;
    private TextView line2;
    private TextView line3;
    private Button calcular;
    // Variável que define se o calculo foi feito
    private boolean isDone;
    private boolean hasIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_energia_ativacao);

        // Referencia os itens utilizados na activity
        eat = findViewById(R.id.eat);
        ep = findViewById(R.id.ep);
        er = findViewById(R.id.er);
        line = findViewById(R.id.line);
        line2 = findViewById(R.id.line2);
        line3 = findViewById(R.id.line3);
        calcular = findViewById(R.id.calcular);

        // Define o valor de isDone
        isDone = false;

        // Define hasIntent como false
        hasIntent = false;

        // Cria um listener para o botão calcular
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                solve();
            }
        });

        // Cria o botão voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void solve(){
        Double deat, dep, der;

        // Evita que ocorram erros ao fazer o calculo
        try{
            deat = Double.parseDouble(eat.getText().toString());
        }
        catch(Exception e){
            deat = null;
        }

        try {
            dep = Double.parseDouble(ep.getText().toString());
        }
        catch(Exception e){
            dep = null;
        }

        try{
            der = Double.parseDouble(er.getText().toString());
        }
        catch (Exception e){
            der = null;
        }

        if (!isDone){
            if (deat == null && dep != null && der != null){
                // Define o valor de isDone como true
                isDone = true;

                // Define a visbilidade das linhas como VISIBLE
                line.setVisibility(VISIBLE);
                line2.setVisibility(VISIBLE);

                line.setText("Eat = " + String.valueOf(dep) + " - " + String.valueOf(der));
                line2.setText("Eat = " + String.format("%.2f", dep - der));

                // Define o estilo do botão calcular
                calcular.setText(R.string.limpar);
                calcular.setBackgroundColor(ContextCompat.getColor(this,
                        R.color.clearButton));

                if (!hasIntent){
                    // Variáveis que obtem os dados
                    Double[] dn = new Double[3];
                    dn[0] = null;
                    dn[1] = dep;
                    dn[2] = der;

                    // String que armazena o dado convertido
                    String data = ConvertStringtoData.DataToString(dn);

                    // Cria um ContentValues
                    ContentValues cv = new ContentValues();
                    cv.put("titulo", getResources().getString(R.string.energia_ativacao));
                    cv.put("data", data);

                    // Cria uma instância do banco de dados
                    HistoricoHelper hh = new HistoricoHelper(this);
                    hh.inserir(cv);
                }
            }
            else if (deat != null && dep == null && der != null){
                // Define o valor de isDone como true
                isDone = true;

                // Define a visbilidade das linhas como VISIBLE
                line.setVisibility(VISIBLE);
                line2.setVisibility(VISIBLE);
                line3.setVisibility(VISIBLE);

                line.setText(String.valueOf(deat) + " = Ep - " + String.valueOf(der));
                line2.setText("Ep = " + String.valueOf(der) + " + " + String.valueOf(deat));
                line3.setText("Ep = " + String.format("%.2f", deat + der));

                // Define o estilo do botão calcular
                calcular.setText(R.string.limpar);
                calcular.setBackgroundColor(ContextCompat.getColor(this,
                        R.color.clearButton));

                if (!hasIntent){
                    // Variáveis que obtem os dados
                    Double[] dn = new Double[3];
                    dn[0] = deat;
                    dn[1] = null;
                    dn[2] = der;

                    // String que armazena o dado convertido
                    String data = ConvertStringtoData.DataToString(dn);

                    // Cria um ContentValues
                    ContentValues cv = new ContentValues();
                    cv.put("titulo", getResources().getString(R.string.energia_ativacao));
                    cv.put("data", data);

                    // Cria uma instância do banco de dados
                    HistoricoHelper hh = new HistoricoHelper(this);
                    hh.inserir(cv);
                }
            }
            else if (deat != null && dep != null && der == null){
                // Define o valor de isDone como true
                isDone = true;

                // Define a visbilidade das linhas como VISIBLE
                line.setVisibility(VISIBLE);
                line2.setVisibility(VISIBLE);
                line3.setVisibility(VISIBLE);

                line.setText(String.valueOf(deat) + " = " + String.valueOf(dep) + " - Er");
                line2.setText("Er = " + String.valueOf(dep) + " - " + String.valueOf(deat));
                line3.setText("Er = " + String.format("%.2f", dep - deat));

                // Define o estilo do botão calcular
                calcular.setText(R.string.limpar);
                calcular.setBackgroundColor(ContextCompat.getColor(this,
                        R.color.clearButton));

                if (!hasIntent){
                    // Variáveis que obtem os dados
                    Double[] dn = new Double[3];
                    dn[0] = deat;
                    dn[1] = dep;
                    dn[2] = null;

                    // String que armazena o dado convertido
                    String data = ConvertStringtoData.DataToString(dn);

                    // Cria um ContentValues
                    ContentValues cv = new ContentValues();
                    cv.put("titulo", getResources().getString(R.string.area_q));
                    cv.put("data", data);

                    // Cria uma instância do banco de dados
                    HistoricoHelper hh = new HistoricoHelper(this);
                    hh.inserir(cv);
                }
            }
            else if (deat != null && dep != null && der != null){
                TudoPreenchido td = new TudoPreenchido();
                td.show(getFragmentManager(), null);
            }
            else {
                EmptyFragment em = new EmptyFragment();
                em.show(getSupportFragmentManager(), null);
            }
        }
        else {
            // Define o valor de isDone como false
            isDone = false;

            // Define a visbilidade das linhas como GONE
            line.setVisibility(GONE);
            line2.setVisibility(GONE);
            line3.setVisibility(GONE);

            // Define como vazio os campos de texto
            eat.setText("");
            ep.setText("");
            er.setText("");

            // Redefine o estilo do botão calcular
            calcular.setText(R.string.Calc);
            calcular.setBackgroundColor(ContextCompat.getColor(this, R.color.AppThemeBlue));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        startActivity(new Intent(energia_ativacao.this, form_choose.class));
        finish();
        return true;
    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(energia_ativacao.this, form_choose.class));
        finish();
    }
}
