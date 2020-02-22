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

public class variacao_entalpia extends AppCompatActivity {
    // Declara os itens
    private EditText variacao;
    private EditText tf;
    private EditText ti;
    private Button calcular;
    private TextView line1;
    private TextView line2;
    private TextView line3;
    private boolean isDone;
    private boolean hasIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_variacao_entalpia);

        // Referencia os itens utilizados na activity
        variacao = findViewById(R.id.variacao);
        tf = findViewById(R.id.tf);
        ti = findViewById(R.id.ti);
        calcular = findViewById(R.id.calcular);
        line1 = findViewById(R.id.line1);
        line2 = findViewById(R.id.line2);
        line3 = findViewById(R.id.line3);

        // Define isDone como false
        isDone = false;

        // Define hasIntent como false
        hasIntent = false;

        // Declara um listenner para o botão calcular
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
        Double d_variacao, d_tf, d_ti;

        // Prevêm que ocorram erros de conversão
        try{
            d_variacao = Double.valueOf(variacao.getText().toString());
        }
        catch(Exception e){
            d_variacao = null;
        }

        try{
            d_tf = Double.valueOf(tf.getText().toString());
        }
        catch(Exception e){
            d_tf = null;
        }

        try{
            d_ti = Double.valueOf(ti.getText().toString());
        }
        catch(Exception e){
            d_ti = null;
        }

        // Executa o calculo de acordo com cada possibilidade
        if (!isDone){
            if(d_variacao == null && d_tf != null && d_ti != null){
                // Define a visibilidade das linhas como visivel
                line1.setVisibility(View.VISIBLE);
                line2.setVisibility(View.VISIBLE);

                line1.setText("ΔH = " + String.valueOf(d_tf) + " - " + String.valueOf(d_ti));
                line2.setText("ΔH = " + String.format("%.2f", d_tf - d_ti));

                // Define o estilo do botão calcular
                calcular.setBackgroundColor(ContextCompat.getColor(this,
                        R.color.clearButton));
                calcular.setText(R.string.limpar);

                // Define isDone como true
                isDone = true;

                if (!hasIntent){
                    // Variáveis que obtem os dados
                    Double[] dn = new Double[3];
                    dn[0] = null;
                    dn[1] = d_tf;
                    dn[2] = d_ti;

                    // String que armazena o dado convertido
                    String data = ConvertStringtoData.DataToString(dn);

                    // Cria um ContentValues
                    ContentValues cv = new ContentValues();
                    cv.put("titulo", getResources().getString(R.string.variacao_entalpia));
                    cv.put("data", data);

                    // Cria uma instância do banco de dados
                    HistoricoHelper hh = new HistoricoHelper(this);
                    hh.inserir(cv);
                }
            }
            else if (d_variacao != null && d_tf == null && d_ti != null){
                // Define a visibilidade das linhas como visivel
                line1.setVisibility(View.VISIBLE);
                line2.setVisibility(View.VISIBLE);
                line3.setVisibility(View.VISIBLE);

                line1.setText(String.valueOf(d_variacao) + " = Tf - " + String.valueOf(d_ti));
                line2.setText("Tf = " + String.valueOf(d_variacao) + " + "  + String.valueOf(d_ti));
                line3.setText("Tf = " + String.format("%.2f", d_variacao = d_ti));

                // Define o estilo do botão calcular
                calcular.setBackgroundColor(ContextCompat.getColor(this,
                        R.color.clearButton));
                calcular.setText(R.string.limpar);

                // Define isDone como true
                isDone = true;

                if (!hasIntent){
                    // Variáveis que obtem os dados
                    Double[] dn = new Double[3];
                    dn[0] = d_variacao;
                    dn[1] = null;
                    dn[2] = d_ti;

                    // String que armazena o dado convertido
                    String data = ConvertStringtoData.DataToString(dn);

                    // Cria um ContentValues
                    ContentValues cv = new ContentValues();
                    cv.put("titulo", getResources().getString(R.string.variacao_entalpia));
                    cv.put("data", data);

                    // Cria uma instância do banco de dados
                    HistoricoHelper hh = new HistoricoHelper(this);
                    hh.inserir(cv);
                }
            }
            else if (d_variacao != null && d_tf != null && d_ti == null){
                // Define a visibilidade das linhas como visivel
                line1.setVisibility(View.VISIBLE);
                line2.setVisibility(View.VISIBLE);
                line3.setVisibility(View.VISIBLE);

                line1.setText(String.valueOf(d_variacao) + " = " + String.valueOf(d_tf) + " - Ti");
                line2.setText("Ti = " + String.valueOf(d_tf) + " - " + String.valueOf(d_variacao));
                line3.setText("Ti = " + String.format("%.2f", d_tf - d_variacao));

                // Define o estilo do botão calcular
                calcular.setBackgroundColor(ContextCompat.getColor(this,
                        R.color.clearButton));
                calcular.setText(R.string.limpar);

                // Define isDone como true
                isDone = true;

                if (!hasIntent){
                    // Variáveis que obtem os dados
                    Double[] dn = new Double[3];
                    dn[0] = d_variacao;
                    dn[1] = d_tf;
                    dn[2] = null;

                    // String que armazena o dado convertido
                    String data = ConvertStringtoData.DataToString(dn);

                    // Cria um ContentValues
                    ContentValues cv = new ContentValues();
                    cv.put("titulo", getResources().getString(R.string.variacao_entalpia));
                    cv.put("data", data);

                    // Cria uma instância do banco de dados
                    HistoricoHelper hh = new HistoricoHelper(this);
                    hh.inserir(cv);
                }
            }
            else if (d_variacao != null && d_tf != null && d_ti != null){
                TudoPreenchido td = new TudoPreenchido();
                td.show(getFragmentManager(), null);
            }
            else {
                EmptyFragment em = new EmptyFragment();
                em.show(getSupportFragmentManager(), null);
            }
        }
        else{
            // Redefine os estilos das linhas
            line1.setVisibility(GONE);
            line2.setVisibility(GONE);
            line3.setVisibility(GONE);

            // Deixa as linhas vazias
            line1.setText("");
            line2.setText("");
            line3.setText("");

            // Esvazia os EditTexts
            variacao.setText("");
            tf.setText("");
            ti.setText("");

            // Redefine o estilo do botão calcular
            calcular.setBackgroundColor(ContextCompat.getColor(this, R.color.AppThemeBlue));
            calcular.setText(R.string.Calc);

            // Define isDone como false
            isDone = false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        startActivity(new Intent(variacao_entalpia.this, form_choose.class));
        finish();
        return true;
    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(variacao_entalpia.this, form_choose.class));
        finish();
    }
}
