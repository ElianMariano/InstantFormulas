package com.EducacaoApps.InstantFormulas.formulas.quimica;

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

public class velocidade_media extends AppCompatActivity {
    private EditText vm;
    private EditText cf;
    private EditText ci;
    private EditText tf;
    private EditText ti;
    private TextView line;
    private TextView line2;
    private TextView line3;
    private TextView line4;
    private TextView line5;
    private TextView line6;
    private TextView line7;
    private TextView line8;
    private Button calcular;
    // Variável que define se a formula foi calculada
    private boolean isDone;
    private boolean hasIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_velocidade_media);

        // Referencia os itens utilizados na activity
        vm = findViewById(R.id.vm);
        cf = findViewById(R.id.cf);
        ci = findViewById(R.id.ci);
        tf = findViewById(R.id.tf);
        ti = findViewById(R.id.ti);
        line = findViewById(R.id.line);
        line2 = findViewById(R.id.line2);
        line3 = findViewById(R.id.line3);
        line4 = findViewById(R.id.line4);
        line5 = findViewById(R.id.line5);
        line6 = findViewById(R.id.line6);
        line7 = findViewById(R.id.line7);
        line8 = findViewById(R.id.line8);
        calcular = findViewById(R.id.calcular);

        // Define o valor inicial de isDone
        isDone = false;

        // Define hasIntent como false
        hasIntent = false;

        // Cria um listenner para o botão calcular
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
        // Variáveis que armazenam os valores dos itens de texto
        Double d_vm, d_cf, d_ci, d_tf, d_ti;

        // Previne que ocorram erros de conversão
        try{
            d_vm = Double.parseDouble(vm.getText().toString());
        }
        catch (Exception e){
            d_vm = null;
        }

        try{
            d_cf = Double.parseDouble(cf.getText().toString());
        }
        catch (Exception e){
            d_cf = null;
        }

        try{
            d_ci = Double.parseDouble(ci.getText().toString());
        }
        catch (Exception e){
            d_ci = null;
        }

        try{
            d_tf = Double.parseDouble(tf.getText().toString());
        }
        catch (Exception e){
            d_tf = null;
        }

        try{
            d_ti = Double.parseDouble(ti.getText().toString());
        }
        catch (Exception e){
            d_ti = null;
        }

        if (!isDone){
            if (d_vm == null && d_cf != null && d_ci != null && d_tf != null && d_ti != null){
                // Define o valor de isDone como true
                isDone = true;

                // Define a visibilidade das linhas como visivel
                line.setVisibility(VISIBLE);
                line2.setVisibility(VISIBLE);
                line3.setVisibility(VISIBLE);

                line.setText("Vm = " + String.valueOf(d_cf) + " - " + String.valueOf(d_ci) + "/" +
                String.valueOf(d_tf) + " - " + String.valueOf(d_ti));
                line2.setText("Vm = " + String.format("%.2f", d_cf - d_ci) + "/" +
                String.format("%.2f", d_tf - d_ti));
                line3.setText("Vm = " + String.format("%.2f", (d_cf - d_ci) / (d_tf - d_ti)));

                // Define o estilo do botão calcular
                calcular.setText(R.string.limpar);
                calcular.setBackgroundColor(ContextCompat.getColor(this,
                        R.color.clearButton));

                if (!hasIntent){
                    // Variáveis que obtem os dados
                    Double[] dn = new Double[5];
                    dn[0] = null;
                    dn[1] = d_cf;
                    dn[2] = d_ci;
                    dn[3] = d_tf;
                    dn[4] = d_ti;

                    // String que armazena o dado convertido
                    String data = ConvertStringtoData.DataToString(dn);

                    // Cria um ContentValues
                    ContentValues cv = new ContentValues();
                    cv.put("titulo", getResources().getString(R.string.velocidade_media));
                    cv.put("data", data);

                    // Cria uma instância do banco de dados
                    HistoricoHelper hh = new HistoricoHelper(this);
                    hh.inserir(cv);
                }
            }
            else if (d_vm != null && d_cf == null && d_ci != null && d_tf != null && d_ti != null){
                // Define o valor de isDone como true
                isDone = true;

                // Define a visibilidade das linhas como visivel
                line.setVisibility(VISIBLE);
                line2.setVisibility(VISIBLE);
                line3.setVisibility(VISIBLE);
                line4.setVisibility(VISIBLE);
                line5.setVisibility(VISIBLE);
                line6.setVisibility(VISIBLE);

                line.setText(String.valueOf(d_vm) + " = Cf - " + String.valueOf(d_ci) + "/" +
                        String.valueOf(d_tf) + " - " + String.valueOf(d_ti));
                line2.setText(String.valueOf(d_vm) + " = Cf - " + String.valueOf(d_ci) + "/" +
                        String.format("%.2f", d_tf - d_ti));
                line3.setText(String.valueOf(d_vm) + " * " + String.format("%.2f", d_tf - d_ti) +
                        " = Cf - " + String.valueOf(d_ci));
                line4.setText(String.format("%.2f", d_vm * (d_tf - d_ti)) + " = Cf - " +
                        String.valueOf(d_ci));
                line5.setText("Cf = " + String.format("%.2f", d_vm * (d_tf - d_ti)) + " + " +
                        String.valueOf(d_ci));
                line6.setText("Cf = " + String.format("%.2f", (d_vm * (d_tf - d_ti) + d_ci)));


                // Define o estilo do botão calcular
                calcular.setText(R.string.limpar);
                calcular.setBackgroundColor(ContextCompat.getColor(this,
                        R.color.clearButton));

                if (!hasIntent){
                    // Variáveis que obtem os dados
                    Double[] dn = new Double[5];
                    dn[0] = d_vm;
                    dn[1] = null;
                    dn[2] = d_ci;
                    dn[3] = d_tf;
                    dn[4] = d_ti;

                    // String que armazena o dado convertido
                    String data = ConvertStringtoData.DataToString(dn);

                    // Cria um ContentValues
                    ContentValues cv = new ContentValues();
                    cv.put("titulo", getResources().getString(R.string.velocidade_media));
                    cv.put("data", data);

                    // Cria uma instância do banco de dados
                    HistoricoHelper hh = new HistoricoHelper(this);
                    hh.inserir(cv);
                }
            }
            else if (d_vm != null && d_cf != null && d_ci == null && d_tf != null && d_ti != null){
                // Define o valor de isDone como true
                isDone = true;

                // Define a visibilidade das linhas como visivel
                line.setVisibility(VISIBLE);
                line2.setVisibility(VISIBLE);
                line3.setVisibility(VISIBLE);
                line4.setVisibility(VISIBLE);
                line5.setVisibility(VISIBLE);
                line6.setVisibility(VISIBLE);

                line.setText(String.valueOf(d_vm) + " = " + String.valueOf(d_cf) + " - Ci/" +
                        String.valueOf(d_tf) + " - " + String.valueOf(d_ti));
                line2.setText(String.valueOf(d_vm) + " = " + String.valueOf(d_cf) + " - Ci/" +
                        String.format("%.2f", d_tf - d_ti));
                line3.setText(String.valueOf(d_cf) + " - Ci = " + String.valueOf(d_vm) + " * " +
                        String.format("%.2f", d_tf - d_ti));
                line4.setText(String.valueOf(d_cf) + " - Ci = " +
                        String.format("%.2f", d_vm * (d_tf - d_ti)));
                line5.setText("Ci = " + String.valueOf(d_cf) + " - " +
                        String.format("%.2f", d_vm * (d_tf - d_ti)));
                line6.setText("Ci = " + String.format("%.2f", d_cf - (d_vm * (d_tf - d_ti))));

                // Define o estilo do botão calcular
                calcular.setText(R.string.limpar);
                calcular.setBackgroundColor(ContextCompat.getColor(this,
                        R.color.clearButton));

                if (!hasIntent){
                    // Variáveis que obtem os dados
                    Double[] dn = new Double[5];
                    dn[0] = d_vm;
                    dn[1] = d_cf;
                    dn[2] = null;
                    dn[3] = d_tf;
                    dn[4] = d_ti;

                    // String que armazena o dado convertido
                    String data = ConvertStringtoData.DataToString(dn);

                    // Cria um ContentValues
                    ContentValues cv = new ContentValues();
                    cv.put("titulo", getResources().getString(R.string.velocidade_media));
                    cv.put("data", data);

                    // Cria uma instância do banco de dados
                    HistoricoHelper hh = new HistoricoHelper(this);
                    hh.inserir(cv);
                }
            }
            else if (d_vm != null && d_cf != null && d_ci != null && d_tf == null && d_ti != null){
                // Define o valor de isDone como true
                isDone = true;

                // Define a visibilidade das linhas como visivel
                line.setVisibility(VISIBLE);
                line2.setVisibility(VISIBLE);
                line3.setVisibility(VISIBLE);
                line4.setVisibility(VISIBLE);
                line5.setVisibility(VISIBLE);
                line6.setVisibility(VISIBLE);
                line7.setVisibility(VISIBLE);
                line8.setVisibility(VISIBLE);

                line.setText(String.valueOf(d_vm) + " = " + String.valueOf(d_cf) + " - " +
                        String.valueOf(d_ci) + "/Tf - " + String.valueOf(d_ti));
                line2.setText(String.valueOf(d_vm) + " = " + String.format("%.2f", d_cf - d_ci) +
                        "/Tf - " + String.valueOf(d_ti));
                line3.setText(String.format("%.2f", d_cf - d_ci) + " = " + String.valueOf(d_vm) +
                        " * (Tf - " + String.valueOf(d_ti) + ")");
                line4.setText(String.format("%.2f", d_cf - d_ci) + " = " + String.valueOf(d_vm) +
                        " * Tf - " + String.valueOf(d_vm) + " * " + String.valueOf(d_ti));
                line5.setText(String.format("%.2f", d_cf - d_ci) + " = " + String.valueOf(d_vm) +
                        " * Tf - " + String.format("%.2f", d_vm * d_ti));
                line6.setText(String.valueOf(d_vm) + " * Tf = " +
                        String.format("%.2f", (d_cf - d_ci) + d_vm * d_ti));
                line7.setText("Tf = " + String.format("%.2f", (d_cf - d_ci) + d_vm * d_ti) + "/" +
                        String.valueOf(d_vm));
                line8.setText("Tf = " + String.format("%.2f", ((d_cf - d_ci) + d_vm * d_ti)
                        / d_vm));

                // Define o estilo do botão calcular
                calcular.setText(R.string.limpar);
                calcular.setBackgroundColor(ContextCompat.getColor(this,
                        R.color.clearButton));

                if (!hasIntent){
                    // Variáveis que obtem os dados
                    Double[] dn = new Double[5];
                    dn[0] = d_vm;
                    dn[1] = d_cf;
                    dn[2] = d_ci;
                    dn[3] = null;
                    dn[4] = d_ti;

                    // String que armazena o dado convertido
                    String data = ConvertStringtoData.DataToString(dn);

                    // Cria um ContentValues
                    ContentValues cv = new ContentValues();
                    cv.put("titulo", getResources().getString(R.string.velocidade_media));
                    cv.put("data", data);

                    // Cria uma instância do banco de dados
                    HistoricoHelper hh = new HistoricoHelper(this);
                    hh.inserir(cv);
                }
            }
            else if (d_vm != null && d_cf != null && d_ci != null && d_tf != null && d_ti == null){
                // Define o valor de isDone como true
                isDone = true;

                // Define a visibilidade das linhas como visivel
                line.setVisibility(VISIBLE);
                line2.setVisibility(VISIBLE);
                line3.setVisibility(VISIBLE);
                line4.setVisibility(VISIBLE);
                line5.setVisibility(VISIBLE);
                line6.setVisibility(VISIBLE);
                line7.setVisibility(VISIBLE);
                line8.setVisibility(VISIBLE);

                line.setText(String.valueOf(d_vm) + " = " + String.valueOf(d_cf) + " - " +
                        String.valueOf(d_ci) + "/" + String.valueOf(d_tf) + " - Ti");
                line2.setText(String.valueOf(d_vm) + " = " + String.format("%.2f", d_cf - d_ci)
                        + "/" + String.valueOf(d_tf) + " - Ti");
                line3.setText(String.format("%.2f", d_cf - d_ci) + " = " + String.valueOf(d_vm) +
                        " * (" + String.valueOf(d_tf) + " - Ti)");
                /*
                Verificar se o calculo esta correto e se funciona caso a variável d_vm for negativa
                */
                line4.setText(String.format("%.2f", d_cf - d_ci) + " = " +
                        String.format("%.2f", d_vm * d_tf) + " - " + String.valueOf(d_vm)
                        + " * Ti");
                line5.setText(String.valueOf(d_vm) + " * Ti = " + String.format("%.2f", d_vm * d_tf)
                        + " - " + String.format("%.2f", d_cf - d_ci));
                line6.setText(String.valueOf(d_vm) + " * Ti = " +
                        String.format("%.2f", (d_vm * d_tf) - (d_cf - d_ci)));
                line7.setText("Ti = " + String.format("%.2f", (d_vm * d_tf) - (d_cf - d_ci)) + "/"
                        + String.valueOf(d_vm));
                line8.setText("Ti = " + String.format("%.2f", ((d_vm * d_tf)
                        - (d_cf - d_ci) / d_vm)));

                // Define o estilo do botão calcular
                calcular.setText(R.string.limpar);
                calcular.setBackgroundColor(ContextCompat.getColor(this,
                        R.color.clearButton));

                if (!hasIntent){
                    // Variáveis que obtem os dados
                    Double[] dn = new Double[5];
                    dn[0] = d_vm;
                    dn[1] = d_cf;
                    dn[2] = d_ci;
                    dn[3] = d_tf;
                    dn[4] = null;

                    // String que armazena o dado convertido
                    String data = ConvertStringtoData.DataToString(dn);

                    // Cria um ContentValues
                    ContentValues cv = new ContentValues();
                    cv.put("titulo", getResources().getString(R.string.velocidade_media));
                    cv.put("data", data);

                    // Cria uma instância do banco de dados
                    HistoricoHelper hh = new HistoricoHelper(this);
                    hh.inserir(cv);
                }
            }
            else if (d_vm != null && d_cf != null && d_ci != null && d_tf != null && d_ti != null){
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

            // Define a visibilidade das linhas
            line.setVisibility(GONE);
            line2.setVisibility(GONE);
            line3.setVisibility(GONE);
            line4.setVisibility(GONE);
            line5.setVisibility(GONE);
            line6.setVisibility(GONE);
            line7.setVisibility(GONE);
            line8.setVisibility(GONE);

            // Define como vazio os campos de texto
            vm.setText("");
            cf.setText("");
            ci.setText("");
            tf.setText("");
            ti.setText("");

            // Redefine o estilo do botão calcular
            calcular.setText(R.string.Calc);
            calcular.setBackgroundColor(ContextCompat.getColor(this, R.color.AppThemeBlue));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        startActivity(new Intent(velocidade_media.this, form_choose.class));
        finish();
        return true;
    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(velocidade_media.this, form_choose.class));
        finish();
    }
}
