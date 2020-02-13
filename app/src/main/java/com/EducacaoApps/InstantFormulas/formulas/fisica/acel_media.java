package com.EducacaoApps.InstantFormulas.formulas.fisica;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
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

public class acel_media extends AppCompatActivity {
    private EditText aceleracao;
    private EditText velocidade;
    private EditText tempo;
    private Button calcular;
    private TextView line;
    private TextView line2;
    private TextView line3;
    private TextView line4;
    // Define se o calculo foi feito
    private boolean isDone;
    private boolean hasIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acel_media);

        // Referencia os itens utilizados na activity
        aceleracao = (EditText) findViewById(R.id.aceleracao);
        velocidade = (EditText) findViewById(R.id.velocidade);
        tempo = (EditText) findViewById(R.id.tempo);
        calcular = (Button) findViewById(R.id.calcular);
        line = (TextView) findViewById(R.id.line);
        line2 = (TextView) findViewById(R.id.line2);
        line3 = (TextView) findViewById(R.id.line3);
        line4 = (TextView) findViewById(R.id.line4);

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
        // Cria as variáveis necessárias para cada item
        Double ace, velo, tem;

        // Prevem possíveis erros
        try{
            ace = Double.parseDouble(aceleracao.getText().toString());
        }
        catch (Exception e){
            ace = null;
        }

        try{
            velo = Double.parseDouble(velocidade.getText().toString());
        }
        catch (Exception e){
            velo = null;
        }

        try{
            tem = Double.parseDouble(tempo.getText().toString());
        }
        catch (Exception e){
            tem = null;
        }

        if (!isDone){
            if (ace == null && velo != null && tem != null){
                // Define isDone para true
                isDone = true;

                // Define a visibilidade das linha como VISIBLE
                line.setVisibility(VISIBLE);
                line2.setVisibility(VISIBLE);

                // Resultado da divisao
                Double rst = velo / tem;

                // Mostra o resultado
                line.setText("A = " + String.valueOf(velo) + "/" + String.valueOf(tem));
                line2.setText("A = " + String.format("%.2f", rst));

                // Define o estilo do botão calcuar
                calcular.setBackgroundColor(ContextCompat.getColor(this, R.color.clearButton));
                calcular.setText(R.string.limpar);

                if (!hasIntent){
                    // Variáveis que obtem os dados
                    Double[] dn = new Double[3];
                    dn[0] = null;
                    dn[1] = velo;
                    dn[2] = tem;

                    // String que armazena o dado convertido
                    String data = ConvertStringtoData.DataToString(dn);

                    // Cria um ContentValues
                    ContentValues cv = new ContentValues();
                    cv.put("titulo", getResources().getString(R.string.acel_media));
                    cv.put("data", data);

                    // Cria uma instância do banco de dados
                    HistoricoHelper hh = new HistoricoHelper(this);
                    hh.inserir(cv);
                }
            }
            else if (ace != null && velo == null && tem != null){
                // Define isDone para true
                isDone = true;

                // Define a visibilidade das linha como VISIBLE
                line.setVisibility(VISIBLE);
                line2.setVisibility(VISIBLE);
                line3.setVisibility(VISIBLE);

                // Mostra o resultado
                line.setText(String.valueOf(ace) + " = Δv/" + String.valueOf(tem));
                line2.setText("Δv = " + String.valueOf(ace) + " * " + String.valueOf(tem));
                line3.setText("Δv = " + String.format("%.2f", ace * tem));

                // Define o estilo do botão calcuar
                calcular.setBackgroundColor(ContextCompat.getColor(this, R.color.clearButton));
                calcular.setText(R.string.limpar);

                if (!hasIntent){
                    // Variáveis que obtem os dados
                    Double[] dn = new Double[3];
                    dn[0] = ace;
                    dn[1] = null;
                    dn[2] = tem;

                    // String que armazena o dado convertido
                    String data = ConvertStringtoData.DataToString(dn);

                    // Cria um ContentValues
                    ContentValues cv = new ContentValues();
                    cv.put("titulo", getResources().getString(R.string.acel_media));
                    cv.put("data", data);

                    // Cria uma instância do banco de dados
                    HistoricoHelper hh = new HistoricoHelper(this);
                    hh.inserir(cv);
                }
            }
            else if (ace != null && velo != null && tem == null){
                // Define isDone para true
                isDone = true;

                // Define a visibilidade das linha como VISIBLE
                line.setVisibility(VISIBLE);
                line2.setVisibility(VISIBLE);
                line3.setVisibility(VISIBLE);
                line4.setVisibility(VISIBLE);

                // Mostra o resultado
                line.setText(String.valueOf(ace) + " = " + String.valueOf(velo) + "/ Δt");
                line2.setText(String.valueOf(ace) + " * Δt = " + String.valueOf(velo));
                line3.setText("Δt = " + String.valueOf(velo) + "/" + String.valueOf(ace));
                line4.setText("Δt = " + String.format("%.2f", velo / ace));

                // Define o estilo do botão calcuar
                calcular.setBackgroundColor(ContextCompat.getColor(this, R.color.clearButton));
                calcular.setText(R.string.limpar);

                if (!hasIntent){
                    // Variáveis que obtem os dados
                    Double[] dn = new Double[3];
                    dn[0] = ace;
                    dn[1] = velo;
                    dn[2] = null;

                    // String que armazena o dado convertido
                    String data = ConvertStringtoData.DataToString(dn);

                    // Cria um ContentValues
                    ContentValues cv = new ContentValues();
                    cv.put("titulo", getResources().getString(R.string.acel_media));
                    cv.put("data", data);

                    // Cria uma instância do banco de dados
                    HistoricoHelper hh = new HistoricoHelper(this);
                    hh.inserir(cv);
                }
            }
            else if (ace != null && velo != null && tem != null){
                TudoPreenchido td = new TudoPreenchido();
                td.show(getFragmentManager(), null);
            }
            else {
                EmptyFragment em = new EmptyFragment();
                em.show(getSupportFragmentManager(), null);
            }
        }
        else{
            // Redefine o valor de isDone
            isDone = false;

            // Define a visibilidade das linhas como GONE
            line.setVisibility(GONE);
            line2.setVisibility(GONE);
            line3.setVisibility(GONE);
            line4.setVisibility(GONE);

            // Deixa os campos de texto vazios
            aceleracao.setText("");
            velocidade.setText("");
            tempo.setText("");

            // Redefine o estilo do botão calcular
            calcular.setBackgroundColor(ContextCompat.getColor(this, R.color.AppThemeBlue));
            calcular.setText(R.string.Calc);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        startActivity(new Intent(acel_media.this, form_choose.class));
        finish();
        return true;
    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(acel_media.this, form_choose.class));
        finish();
    }
}
