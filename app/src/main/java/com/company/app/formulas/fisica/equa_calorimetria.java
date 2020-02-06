package com.company.app.formulas.fisica;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.company.app.ConvertStringtoData;
import com.company.app.ItensLibrary.EmptyFragment;
import com.company.app.ItensLibrary.TudoPreenchido;
import com.company.app.Models.HistoricoHelper;
import com.company.app.form_choose;
import com.example.company.formulas.R;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class equa_calorimetria extends AppCompatActivity {
    private EditText q;
    private EditText m;
    private EditText c;
    private EditText t;
    private TextView line;
    private TextView line2;
    private TextView line3;
    private TextView line4;
    private boolean isDone;
    private Button calcular;
    private boolean hasIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equa_calorimetria);

        q = findViewById(R.id.q);
        m = findViewById(R.id.m);
        c = findViewById(R.id.c);
        t = findViewById(R.id.t);
        line = findViewById(R.id.line);
        line2 = findViewById(R.id.line2);
        line3 = findViewById(R.id.line3);
        line4 = findViewById(R.id.line4);
        calcular = findViewById(R.id.calcular);

        // Define o valor de isDone
        isDone = false;

        // Define hasIntent como false
        hasIntent = false;

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
        Double dq, dm, dc, dt;

        try{
            dq = Double.parseDouble(q.getText().toString());
        }
        catch (Exception e){
            dq = null;
        }

        try{
            dm = Double.parseDouble(m.getText().toString());
        }
        catch (Exception e){
            dm = null;
        }

        try{
            dc = Double.parseDouble(c.getText().toString());
        }
        catch (Exception e){
            dc = null;
        }

        try{
           dt = Double.parseDouble(t.getText().toString());
        }
        catch (Exception e){
            dt = null;
        }

        if(!isDone){
            if(dq == null && dm != null && dc != null && dt != null){
                // Define o valor de isDone como true
                isDone = true;

                // Define a visibilidade das linhas como visivel
                line.setVisibility(VISIBLE);
                line2.setVisibility(VISIBLE);
                line3.setVisibility(VISIBLE);

                line.setText("Q = " + String.valueOf(dm) + " * " + String.valueOf(dc) + " * " +
                        String.valueOf(dt));
                line2.setText("Q = " + String.format("%.2f", dm * dc) + " * " + String.valueOf(dt));
                line3.setText("Q = " + String.format("%.2f", dm * dc * dt));

                // Define o estilo do botão calcular
                calcular.setText(R.string.limpar);
                calcular.setBackgroundColor(ContextCompat.getColor(this,
                        R.color.clearButton));

                if (!hasIntent){
                    // Variáveis que obtem os dados
                    Double[] dn = new Double[4];
                    dn[0] = null;
                    dn[1] = dm;
                    dn[2] = dc;
                    dn[3] = dt;

                    // String que armazena o dado convertido
                    String data = ConvertStringtoData.DataToString(dn);

                    // Cria um ContentValues
                    ContentValues cv = new ContentValues();
                    cv.put("titulo", getResources().getString(R.string.equa_calorimetria));
                    cv.put("data", data);

                    // Cria uma instância do banco de dados
                    HistoricoHelper hh = new HistoricoHelper(this);
                    hh.inserir(cv);
                }
            }
            else if(dq != null && dm == null && dc != null && dt != null){
                // Define o valor de isDone como true
                isDone = true;

                // Define a visibilidade das linhas como visivel
                line.setVisibility(VISIBLE);
                line2.setVisibility(VISIBLE);
                line3.setVisibility(VISIBLE);
                line4.setVisibility(VISIBLE);

                line.setText(String.valueOf(dq) + " = m * " + String.valueOf(dc) + " * " +
                        String.valueOf(dt));
                line2.setText(String.valueOf(dq) + " = m * " + String.format("%.2f", dc * dt));
                line3.setText("m = " + String.valueOf(dq) + "/" + String.format("%.2f", dc * dt));
                line4.setText("m = " + String.format("%.2f", dq / (dc * dt)));

                // Define o estilo do botão calcular
                calcular.setText(R.string.limpar);
                calcular.setBackgroundColor(ContextCompat.getColor(this,
                        R.color.clearButton));

                if (!hasIntent){
                    // Variáveis que obtem os dados
                    Double[] dn = new Double[4];
                    dn[0] = dq;
                    dn[1] = null;
                    dn[2] = dc;
                    dn[3] = dt;

                    // String que armazena o dado convertido
                    String data = ConvertStringtoData.DataToString(dn);

                    // Cria um ContentValues
                    ContentValues cv = new ContentValues();
                    cv.put("titulo", getResources().getString(R.string.equa_calorimetria));
                    cv.put("data", data);

                    // Cria uma instância do banco de dados
                    HistoricoHelper hh = new HistoricoHelper(this);
                    hh.inserir(cv);
                }
            }
            else if(dq != null && dm != null && dc == null && dt != null){
                // Define o valor de isDone como true
                isDone = true;

                // Define a visibilidade das linhas como visivel
                line.setVisibility(VISIBLE);
                line2.setVisibility(VISIBLE);
                line3.setVisibility(VISIBLE);
                line4.setVisibility(VISIBLE);

                line.setText(String.valueOf(dq) + " = " + String.valueOf(dm) + " * c * " +
                        String.valueOf(dt));
                line2.setText(String.valueOf(dq) + " = c * " + String.format("%.2f", dm * dt));
                line3.setText("c = " + String.valueOf(dq) + "/" + String.format("%.2f", dm * dt));
                line4.setText("c = " + String.format("%.2f", dq / (dm * dt)));

                // Define o estilo do botão calcular
                calcular.setText(R.string.limpar);
                calcular.setBackgroundColor(ContextCompat.getColor(this,
                        R.color.clearButton));

                if (!hasIntent){
                    // Variáveis que obtem os dados
                    Double[] dn = new Double[4];
                    dn[0] = dq;
                    dn[1] = dm;
                    dn[2] = null;
                    dn[3] = dt;

                    // String que armazena o dado convertido
                    String data = ConvertStringtoData.DataToString(dn);

                    // Cria um ContentValues
                    ContentValues cv = new ContentValues();
                    cv.put("titulo", getResources().getString(R.string.equa_calorimetria));
                    cv.put("data", data);

                    // Cria uma instância do banco de dados
                    HistoricoHelper hh = new HistoricoHelper(this);
                    hh.inserir(cv);
                }
            }
            else if(dq != null && dm != null && dc != null && dt == null){
                // Define o valor de isDone como true
                isDone = true;

                // Define a visibilidade das linhas como visivel
                line.setVisibility(VISIBLE);
                line2.setVisibility(VISIBLE);
                line3.setVisibility(VISIBLE);
                line4.setVisibility(VISIBLE);

                line.setText(String.valueOf(dq) + " = " + String.valueOf(dm) + " * " +
                        String.valueOf(dc) + " * ΔT");
                line2.setText(String.valueOf(dq) + " = ΔT * " + String.format("%.2f", dm * dc));
                line3.setText("ΔT = " + String.valueOf(dq) + "/" + String.format("%.2f", dm * dc));
                line4.setText("ΔT = " + String.format("%.2f", dq / (dm * dc)));

                // Define o estilo do botão calcular
                calcular.setText(R.string.limpar);
                calcular.setBackgroundColor(ContextCompat.getColor(this,
                        R.color.clearButton));

                if (!hasIntent){
                    // Variáveis que obtem os dados
                    Double[] dn = new Double[4];
                    dn[0] = dq;
                    dn[1] = dm;
                    dn[2] = dc;
                    dn[3] = null;

                    // String que armazena o dado convertido
                    String data = ConvertStringtoData.DataToString(dn);

                    // Cria um ContentValues
                    ContentValues cv = new ContentValues();
                    cv.put("titulo", getResources().getString(R.string.equa_calorimetria));
                    cv.put("data", data);

                    // Cria uma instância do banco de dados
                    HistoricoHelper hh = new HistoricoHelper(this);
                    hh.inserir(cv);
                }
            }
            else if (dq != null && dm != null && dc != null && dt != null){
                TudoPreenchido td = new TudoPreenchido();
                td.show(getFragmentManager(), null);
            }
            else {
                EmptyFragment em = new EmptyFragment();
                em.show(getSupportFragmentManager(), null);
            }

        }
        else{
            // Define o valor de isDone como false
            isDone = false;

            line.setVisibility(GONE);
            line2.setVisibility(GONE);
            line3.setVisibility(GONE);
            line4.setVisibility(GONE);

            // Deixa os campos de textos vazios
            q.setText("");
            m.setText("");
            c.setText("");
            t.setText("");

            // Redefine os estilo do botão
            calcular.setText(R.string.Calc);
            calcular.setBackgroundColor(ContextCompat.getColor(this, R.color.AppThemeBlue));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        startActivity(new Intent(equa_calorimetria.this, form_choose.class));
        finish();
        return true;
    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(equa_calorimetria.this, form_choose.class));
        finish();
    }
}
