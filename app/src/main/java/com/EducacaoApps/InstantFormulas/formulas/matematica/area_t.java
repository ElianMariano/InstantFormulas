package com.EducacaoApps.InstantFormulas.formulas.matematica;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.EducacaoApps.InstantFormulas.ConvertStringtoData;
import com.EducacaoApps.InstantFormulas.Models.HistoricoHelper;
import com.EducacaoApps.InstantFormulas.ItensLibrary.EmptyFragment;
import com.EducacaoApps.InstantFormulas.ItensLibrary.TudoPreenchido;
import com.EducacaoApps.InstantFormulas.form_choose;
import com.EducacaoApps.InstantFormulas.formulas.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.Random;

public class area_t extends AppCompatActivity {
    // Variáveis que armazenam os itens que serão utilizados
    private EditText area;
    private EditText base;
    private EditText altura;
    private TextView line1;
    private TextView line2;
    private TextView line3;
    private TextView line4;
    private Button calcular;
    private boolean hasIntent;
    // Variável que define se o calculo foi feito ou não
    private boolean isDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_t);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        // Referencia os itens utilizados na activity
        area = findViewById(R.id.area);
        base = findViewById(R.id.base);
        altura = findViewById(R.id.altura);
        line1 = findViewById(R.id.line1);
        line2 = findViewById(R.id.line2);
        line3 = findViewById(R.id.line3);
        line4 = findViewById(R.id.line4);
        calcular = findViewById(R.id.calcular);

        // Define o valor para isDone
        isDone = false;

        // Define um listener de eventos para o botão calcular
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                solve();
            }
        });

        // Define hasIntent como false
        hasIntent = false;

        // Obtêm o intent
        Intent in = getIntent();
        // Variável que armazena os dados
        String data = in.getStringExtra("data");

        if (data != null){
            // Define hasIntent como true
            hasIntent = true;

            // Obtêm os valores e armazena dentro da variável
            String[] split = ConvertStringtoData.SplitString(data);

            // Variáveis que armazenam os dados
            String s_area, s_base, s_altura;

            // Previne que ocorram erros
            try{
                s_area = split[0];
            }
            catch(IndexOutOfBoundsException e){
                s_area = "";
            }

            try{
                s_base = split[1];
            }
            catch(IndexOutOfBoundsException e){
                s_base = "";
            }

            try{
                s_altura = split[2];
            }
            catch(IndexOutOfBoundsException e){
                s_altura = "";
            }

            // Define os textos do itens
            area.setText(s_area);
            base.setText(s_base);
            altura.setText(s_altura);

            // Executa o calculo
            solve();
        }

        // Cria o botão voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // Inicializa os anúncios
        MobileAds.initialize(this);
    }

    public void solve(){
        Double Dnum1, Dnum2, Dnum3;

        // Prevêm que ocorram possíveis erros de conversão de tipos
        try{
            Dnum1 = Double.valueOf(area.getText().toString());
        }
        catch (Exception e){
            Dnum1 = null;
        }

        try{
            Dnum2 = Double.valueOf(base.getText().toString());
        }
        catch (Exception e){
            Dnum2 = null;
        }

        try{
            Dnum3 = Double.valueOf(altura.getText().toString());
        }
        catch (Exception e){
            Dnum3 = null;
        }

        if((Dnum1 == null && Dnum2 != null && Dnum3 != null) && !isDone){
            // Define isDone como true
            isDone = true;

            // Define a visibilidade das linhas
            line1.setVisibility(View.VISIBLE);
            line2.setVisibility(View.VISIBLE);
            line3.setVisibility(View.VISIBLE);

            // Resultados das operações utilizadas
            Double rstMult = Dnum2 * Dnum3;
            Double rst = (Dnum2 * Dnum3) / 2;

            line1.setText("A = " + String.valueOf(Dnum2) + " * " + String.valueOf(Dnum3) + "/2");
            line2.setText("A = " + String.format("%.2f", rstMult) + "/2");
            line3.setText("A = " + String.format("%.2f", rst));

            // Define o estilo do botão quando ele limpa a operação
            calcular.setBackgroundColor(ContextCompat.getColor(this, R.color.clearButton));
            calcular.setText(R.string.limpar);

            if (!hasIntent){
                Double[] dt = new Double[3];
                dt[0] = null;
                dt[1] = Dnum2;
                dt[2] = Dnum3;

                // Variável que armazena os dados
                String data = ConvertStringtoData.DataToString(dt);
                Log.e("AreaT", String.format("Data: %s", data));

                // Cria um ContentValues
                ContentValues cv = new ContentValues();
                cv.put("titulo", getResources().getString(R.string.area_t));
                cv.put("data", data);

                // Cria uma intância do banco de dados
                HistoricoHelper hh = new HistoricoHelper(this);
                hh.inserir(cv);
            }
        }
        else if ((Dnum1 != null && Dnum2 == null && Dnum3 != null) && !isDone){
            // Define isDone como true
            isDone = true;

            // Define a visibilidade das linhas
            line1.setVisibility(View.VISIBLE);
            line2.setVisibility(View.VISIBLE);
            line3.setVisibility(View.VISIBLE);
            line4.setVisibility(View.VISIBLE);

            // Armazena o resultado das operações utilizadas
            Double rstDiv = Dnum3 / 2;
            Double rst = Dnum1 / rstDiv;

            line1.setText(String.valueOf(Dnum1) + " = b * " + String.valueOf(Dnum3) + "/2");
            line2.setText(String.valueOf(Dnum1) + " = b * " + String.format("%.2f", rstDiv));
            line3.setText("b = " + String.valueOf(Dnum1) + "/" + String.format("%.2f", rstDiv));
            line4.setText("b = " + String.format("%.2f", rst));

            // Define o estilo do botão quando ele limpa a operação
            calcular.setBackgroundColor(ContextCompat.getColor(this, R.color.clearButton));
            calcular.setText(R.string.limpar);

            if (!hasIntent){
                Double[] dt = new Double[3];
                dt[0] = Dnum1;
                dt[1] = null;
                dt[2] = Dnum3;

                // Variável que armazena os dados
                String data = ConvertStringtoData.DataToString(dt);
                Log.e("AreaT", String.format("Data: %s", data));

                // Cria um ContentValues
                ContentValues cv = new ContentValues();
                cv.put("titulo", getResources().getString(R.string.area_t));
                cv.put("data", data);

                // Cria uma intância do banco de dados
                HistoricoHelper hh = new HistoricoHelper(this);
                hh.inserir(cv);
            }
        }
        else if ((Dnum1 != null && Dnum2 != null && Dnum3 == null) && !isDone){
            // Define isDone como true
            isDone = true;

            // Define a visibilidade das linhas
            line1.setVisibility(View.VISIBLE);
            line2.setVisibility(View.VISIBLE);
            line3.setVisibility(View.VISIBLE);
            line4.setVisibility(View.VISIBLE);

            // Resultados que serão utilizados
            Double rstMult = Dnum1 * 2;
            Double rst = rstMult / Dnum2;

            line1.setText(String.valueOf(Dnum1) + " = " + String.valueOf(Dnum2) + " * h /2");
            line2.setText(String.valueOf(Dnum1) + " * 2 = " + String.valueOf(Dnum2) + " * h");
            line3.setText("h = " + String.format("%.2f", rstMult) + " / " + String.valueOf(Dnum2));
            line4.setText("h = " + String.format("%.2f", rst));

            // Define o estilo do botão quando ele limpa a operação
            calcular.setBackgroundColor(ContextCompat.getColor(this, R.color.clearButton));
            calcular.setText(R.string.limpar);

            if (!hasIntent){
                Double[] dt = new Double[3];
                dt[0] = Dnum1;
                dt[1] = Dnum2;
                dt[2] = null;

                // Variável que armazena os dados
                String data = ConvertStringtoData.DataToString(dt);
                Log.e("AreaT", String.format("Data: %s", data));

                // Cria um ContentValues
                ContentValues cv = new ContentValues();
                cv.put("titulo", getResources().getString(R.string.area_t));
                cv.put("data", data);

                // Cria uma intância do banco de dados
                HistoricoHelper hh = new HistoricoHelper(this);
                hh.inserir(cv);
            }
        }
        else if ((Dnum1 != null && Dnum2 != null && Dnum3 != null) && !isDone){
            TudoPreenchido td = new TudoPreenchido();
            td.show(getFragmentManager(), "");
        }
        else if (isDone){
            // Verifica se o os anúncios foram removidos
            SharedPreferences shared = getPreferences(Context.MODE_PRIVATE);
            boolean isAdRemoved = shared.getBoolean("isAdRemoved", false);

            // Gera um número com a chance de mostrar o anúncio
            Random random = new Random();
            int num = random.nextInt(3);

            if (!isAdRemoved && num == 0){
                // Carrega os anúncios
                final InterstitialAd interstitialAd = new InterstitialAd(this);
                interstitialAd.setAdUnitId("AD_ID");
                interstitialAd.loadAd(new AdRequest.Builder().build());

                // Mostra o anúncio
                interstitialAd.setAdListener(new AdListener(){
                    @Override
                    public void onAdLoaded(){
                        interstitialAd.show();
                    }
                });
            }

            // Define isDone como false
            isDone = false;

            // Define a visibilidade das linhas
            line1.setVisibility(View.GONE);
            line2.setVisibility(View.GONE);
            line3.setVisibility(View.GONE);
            line4.setVisibility(View.GONE);

            // Faz com que os campos fiquem vazios
            area.setText("");
            base.setText("");
            altura.setText("");

            // Redefine o estilo do botão
            calcular.setBackgroundColor(ContextCompat.getColor(this, R.color.AppThemeBlue));
            calcular.setText(R.string.Calc);
        }
        else{
            EmptyFragment em = new EmptyFragment();
            em.show(getSupportFragmentManager(), "");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        startActivity(new Intent(area_t.this, form_choose.class));
        finish();
        return true;
    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(area_t.this, form_choose.class));
        finish();
    }
}
