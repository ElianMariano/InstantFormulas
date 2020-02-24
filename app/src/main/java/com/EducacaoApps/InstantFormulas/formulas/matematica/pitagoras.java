package com.EducacaoApps.InstantFormulas.formulas.matematica;

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
import java.lang.Math;

import com.EducacaoApps.InstantFormulas.ConvertStringtoData;
import com.EducacaoApps.InstantFormulas.Models.HistoricoHelper;
import com.EducacaoApps.InstantFormulas.ItensLibrary.EmptyFragment;
import com.EducacaoApps.InstantFormulas.ItensLibrary.IvalidDataFragment;
import com.EducacaoApps.InstantFormulas.ItensLibrary.TudoPreenchido;
import com.EducacaoApps.InstantFormulas.form_choose;
import com.EducacaoApps.InstantFormulas.formulas.R;

public class pitagoras extends AppCompatActivity {
    private EditText hipotenusa, co, ca;
    private TextView line1, line2, line3, line4, line5, line6;
    private Button calcular;
    private boolean isDone;
    // Variável que verifica se a activity possui dados de Intent
    private boolean hasIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pitagoras);

        // Referencia os itens utilizados na activity
        hipotenusa = (EditText) findViewById(R.id.hipotenusa);
        co = (EditText) findViewById(R.id.co);
        ca = (EditText) findViewById(R.id.ca);
        line1 = (TextView) findViewById(R.id.line1);
        line2 = (TextView) findViewById(R.id.line2);
        line3 = (TextView) findViewById(R.id.line3);
        line4 = (TextView) findViewById(R.id.line4);
        line5 = (TextView) findViewById(R.id.line5);
        line6 = (TextView) findViewById(R.id.line6);
        calcular = (Button) findViewById(R.id.calcular);

        // Define o valor de isDone como false
        isDone = false;

        // Cria um listener para o botão calcular
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                solve();
            }
        });

        // Define hasIntent como false
        hasIntent = false;

        // Obtêm os dados de Intent
        Intent in = getIntent();
        String data = in.getStringExtra("data");

        String[] split;

        if (data != null){
            // Define o valor de hasIntent como true
            hasIntent = true;

            // Converte os dados e armazena na variável
            split = ConvertStringtoData.SplitString(data);

            // Obtém os valores
            String sh, sco, sca;

            // Previne que ocorram erros
            try{
                sh = split[0];
            }
            catch (IndexOutOfBoundsException e){
                sh = "";
            }

            try{
                sco = split[1];
            }
            catch(IndexOutOfBoundsException e){
                sco = "";
            }

            try{
                sca = split[2];
            }
            catch(IndexOutOfBoundsException e){
                sca = "";
            }

            // Adicciona os dados nos campos de texto
            hipotenusa.setText(sh);
            co.setText(sco);
            ca.setText(sca);

            // Usa a função solve
            solve();
        }

        // Cria o botão voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    public void solve(){
        // Define as variáveis necessárias
        Double l_hipotenusa, l_co, l_ca;

        // Prevem possiveis erros
        try{
            l_hipotenusa = Double.parseDouble(hipotenusa.getText().toString());
        }
        catch(Exception e){
            l_hipotenusa = null;
        }

        try{
            l_co = Double.parseDouble(co.getText().toString());
        }
        catch (Exception e){
            l_co = null;
        }

        try{
            l_ca = Double.parseDouble(ca.getText().toString());
        }
        catch (Exception e){
            l_ca = null;
        }

        if(!isDone){
            if(l_hipotenusa == null && l_co != null && l_ca  != null){
                // Define isDone como true
                isDone = true;

                // Define a visibilidade das linhas
                line1.setVisibility(View.VISIBLE);
                line2.setVisibility(View.VISIBLE);
                line3.setVisibility(View.VISIBLE);
                line4.setVisibility(View.VISIBLE);
                line5.setVisibility(View.VISIBLE);

                // Resultado
                Double rst = Math.sqrt((l_co * l_co) + (l_ca * l_ca));
                // Resultado da soma dos quadrados dos catetos
                Double ct = ((l_co * l_co) + (l_ca * l_ca));

                line1.setText("h² = " + String.valueOf(l_co) + "² + " + String.valueOf(l_ca) + "²");
                line2.setText("h² = " + String.valueOf(l_co * l_co) + " + "
                        + String.valueOf(l_ca * l_ca));
                line3.setText("h² = " + String.valueOf(ct));
                line4.setText("h = √" + String.valueOf(ct));
                line5.setText("h = " + String.format("%.2f", rst));

                // Define o estilo do botão quando ele está vermelho
                calcular.setBackgroundColor(ContextCompat.getColor(this, R.color.clearButton));
                calcular.setText(R.string.limpar);

                // Verifica e armazena os dados
                if (!hasIntent){
                    // Armazena os dados
                    Double[] dt = new Double[3];
                    dt[0] = null;
                    dt[1] = l_co;
                    dt[2] = l_ca;

                    // Converte os dados e armazenam em uma String para ser usada no banco de dados
                    String convert = ConvertStringtoData.DataToString(dt);

                    // Cria um Contentvalues
                    ContentValues cv = new ContentValues();
                    cv.put("titulo", getResources().getString(R.string.pitagoras));
                    cv.put("data", convert);

                    // Armazena no banco de dados
                    HistoricoHelper hh = new HistoricoHelper(this);
                    hh.inserir(cv);
                }
            }
            else if (l_hipotenusa != null && l_co == null && l_ca != null){
                if (((l_hipotenusa * l_hipotenusa) - (l_ca * l_ca)) < 0){
                    IvalidDataFragment dialog = new IvalidDataFragment();
                    dialog.show(getFragmentManager(), null);
                }
                else{
                    // Define isDone como true
                    isDone = true;

                    // Define a visibilidade das linhas
                    line1.setVisibility(View.VISIBLE);
                    line2.setVisibility(View.VISIBLE);
                    line3.setVisibility(View.VISIBLE);
                    line4.setVisibility(View.VISIBLE);
                    line5.setVisibility(View.VISIBLE);
                    line6.setVisibility(View.VISIBLE);

                    // Armazena o resultado
                    Double rst = Math.sqrt(((l_hipotenusa * l_hipotenusa) - (l_ca * l_ca)));

                    line1.setText(l_hipotenusa + "² = c² + " + l_ca + "²");
                    line2.setText((l_hipotenusa * l_hipotenusa) + " = c² + " + (l_ca * l_ca));
                    line3.setText("c² = " + (l_hipotenusa * l_hipotenusa) + " - " + (l_ca * l_ca));
                    line4.setText("c² = " + ((l_hipotenusa * l_hipotenusa) - (l_ca * l_ca)));
                    line5.setText("c = √" + ((l_hipotenusa * l_hipotenusa) - (l_ca * l_ca)));
                    line6.setText("c = " + String.format("%.2f", rst));

                    // Define o estilo do botão quando ele está vermelho
                    calcular.setBackgroundColor(ContextCompat.getColor(this,
                            R.color.clearButton));
                    calcular.setText(R.string.limpar);

                    // Verifica e armazena os dados
                    if (!hasIntent){
                        // Armazena os dados
                        Double[] dt = new Double[3];
                        dt[0] = l_hipotenusa;
                        dt[1] = null;
                        dt[2] = l_ca;

                        // Converte os dados e armazenam em uma String para ser usada no banco de dados
                        String convert = ConvertStringtoData.DataToString(dt);

                        // Cria um Contentvalues
                        ContentValues cv = new ContentValues();
                        cv.put("titulo", getResources().getString(R.string.pitagoras));
                        cv.put("data", convert);

                        // Armazena no banco de dados
                        HistoricoHelper hh = new HistoricoHelper(this);
                        hh.inserir(cv);
                    }
                }
            }
            else if (l_hipotenusa != null && l_co != null && l_ca == null){
                if (((l_hipotenusa * l_hipotenusa) - (l_co * l_co)) < 0){
                    IvalidDataFragment dialog = new IvalidDataFragment();
                    dialog.show(getFragmentManager(), null);
                }
                else{
                    // Define isDone como true
                    isDone = true;

                    // Define a visibilidade das linhas
                    line1.setVisibility(View.VISIBLE);
                    line2.setVisibility(View.VISIBLE);
                    line3.setVisibility(View.VISIBLE);
                    line4.setVisibility(View.VISIBLE);
                    line5.setVisibility(View.VISIBLE);
                    line6.setVisibility(View.VISIBLE);

                    // Resultado
                    Double rst = Math.sqrt(((l_hipotenusa * l_hipotenusa) - (l_co * l_co)));

                    line1.setText(l_hipotenusa + "² = " + l_co + "² + c²");
                    line2.setText((l_hipotenusa * l_hipotenusa) + " = " + (l_co * l_co) + " + c²");
                    line3.setText("c²  = " + (l_hipotenusa * l_hipotenusa) + " - " + (l_co * l_co));
                    line4.setText("c² = " + ((l_hipotenusa * l_hipotenusa) - (l_co * l_co)));
                    line5.setText("c  = √" + ((l_hipotenusa * l_hipotenusa) - (l_co * l_co)));
                    line6.setText("c = " + String.format("%.2f", rst));

                    // Define o estilo do botão quando ele está vermelho
                    calcular.setBackgroundColor(ContextCompat.getColor(this, R.color.clearButton));
                    calcular.setText(R.string.limpar);

                    // Verifica e armazena os dados
                    if (!hasIntent){
                        // Armazena os dados
                        Double[] dt = new Double[3];
                        dt[0] = l_hipotenusa;
                        dt[1] = l_co;
                        dt[2] = null;

                        // Converte os dados e armazenam em uma String para ser usada no banco de dados
                        String convert = ConvertStringtoData.DataToString(dt);

                        // Cria um Contentvalues
                        ContentValues cv = new ContentValues();
                        cv.put("titulo", getResources().getString(R.string.pitagoras));
                        cv.put("data", convert);

                        // Armazena no banco de dados
                        HistoricoHelper hh = new HistoricoHelper(this);
                        hh.inserir(cv);
                    }
                }
            }
            else if (l_hipotenusa != null && l_co != null && l_ca != null){
                TudoPreenchido td = new TudoPreenchido();
                td.show(getFragmentManager(), null);
            }
            else {
                EmptyFragment em = new EmptyFragment();
                em.show(getSupportFragmentManager(), null);
            }
        }
        else{
            // Define isDone como como false
            isDone = false;

            // Define a visibilidade das linhas como gone
            line1.setVisibility(View.GONE);
            line2.setVisibility(View.GONE);
            line3.setVisibility(View.GONE);
            line4.setVisibility(View.GONE);
            line5.setVisibility(View.GONE);
            line6.setVisibility(View.GONE);

            // Define o campos como vazio
            hipotenusa.setText("");
            co.setText("");
            ca.setText("");

            // Define o estilo do botão quando ele está azul
            calcular.setBackgroundColor(ContextCompat.getColor(this, R.color.AppThemeBlue));
            calcular.setText(R.string.Calc);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        startActivity(new Intent(pitagoras.this, form_choose.class));
        finish();
        return true;
    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(pitagoras.this, form_choose.class));
        finish();
    }
}
