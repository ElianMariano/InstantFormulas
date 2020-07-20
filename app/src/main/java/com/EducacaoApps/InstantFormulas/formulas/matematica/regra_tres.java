package com.EducacaoApps.InstantFormulas.formulas.matematica;

import android.annotation.SuppressLint;
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
import com.EducacaoApps.InstantFormulas.formulas.R;
import com.EducacaoApps.InstantFormulas.form_choose;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.List;
import java.util.Random;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class regra_tres extends AppCompatActivity {
    private TextView line1, line2, line3;
    private EditText num1, num2, num3;
    private Button calcular;
    private boolean isDone;
    private boolean hasIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regra_tres);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        // Declara a variável isDone como false
        isDone = false;

        // Declara hasIntent como false
        hasIntent = false;

        // Declara os TextViews
        line1 = findViewById(R.id.line1);
        line2 = findViewById(R.id.line2);
        line3 = findViewById(R.id.line3);

        // Declara o campos de textos
        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        num3 = findViewById(R.id.num3);

        // Declara e cria um listener para o botão calcular
        calcular = (Button) findViewById(R.id.calcular);
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solve();
            }
        });

        // Obtem os dados a partir do intent
        Intent in = getIntent();
        String data = in.getStringExtra("data");

        // Variável que armazena os dados
        List<Double> dt;

        if (data !=  null){
            // Obtêm os dados
            dt = ConvertStringtoData.StringToData(data);

            // Adiciona no Edittext
            num1.setText(String.valueOf(dt.get(0)));
            num2.setText(String.valueOf(dt.get(1)));
            num3.setText(String.valueOf(dt.get(2)));

            // Define hasIntent como true
            hasIntent = true;

            // Executa a função solve
            solve();
        }

        // Cria o botão voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // Inicializa os anúncios
        MobileAds.initialize(this);
    }

    @SuppressLint("DefaultLocale")
    public void solve(){
        Double Dnum1;
        try{
            Dnum1 = Double.parseDouble(num1.getText().toString());
        }
        catch(Exception e){
            Dnum1 = null;
        }

        Double Dnum2;
        try{
            Dnum2 = Double.parseDouble(num2.getText().toString());
        }
        catch(Exception  e){
            Dnum2 = null;
        }

        Double Dnum3;
        try{
            Dnum3 = Double.parseDouble(num3.getText().toString());
        }
        catch(Exception e){
            Dnum3 = null;
        }


        if ((Dnum1 != null && Dnum2 != null && Dnum3 !=null) || isDone ){
            if (!isDone){
                isDone = true;

                // Resultado da operação
                Double rst = (Dnum2 * Dnum3)/Dnum1;

                String l1, l2, l3;

                l1 = Dnum1 + " X = " + (Dnum2 * Dnum3);
                l2 = "X = " + (Dnum2 * Dnum3) + "/" + Dnum1;
                l3 = "X = " + String.format("%.2f", rst);

                //Define o conteúdo e visibilidade dos itens
                line1.setText(l1);
                line1.setVisibility(VISIBLE);
                line2.setText(l2);
                line2.setVisibility(VISIBLE);
                line3.setText(l3);
                line3.setVisibility(VISIBLE);

                //Define a cor do botão calcular
                calcular.setBackgroundColor(ContextCompat.getColor(this, R.color.clearButton));
                calcular.setText(R.string.limpar);

                // Adiciona o item de historico ao banco de dados
                if (!hasIntent){
                    // Variável que armazena os valores
                    Double data[] = {Dnum1, Dnum2, Dnum3};

                    // Converte estes dados para uma String
                    String dt = ConvertStringtoData.DataToString(data);

                    // Cria um ContentValues para ser usado no banco de dados
                    ContentValues cv = new ContentValues();
                    cv.put("titulo", getResources().getString(R.string.regra_de_tres));
                    cv.put("data", dt);

                    // Cria uma instancia do banco de dados e faz o armazenamento
                    HistoricoHelper hh = new HistoricoHelper(this);
                    hh.inserir(cv);
                }
            }
            else{
                // Verifica se o os anúncios foram removidos
                SharedPreferences shared = getSharedPreferences("isAdRemoved",
                        Context.MODE_PRIVATE);
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

                isDone = false;

                //Deixa os Editexts com os textos vazios
                num1.setText("");
                num2.setText("");
                num3.setText("");

                //Desativa a visibilidade dos itens
                line1.setVisibility(GONE);
                line2.setVisibility(GONE);
                line3.setVisibility(GONE);

                calcular.setBackgroundColor(ContextCompat.getColor(this, R.color.AppThemeBlue));
                calcular.setText(R.string.Calc);
            }
        }
        else{
            EmptyFragment dlf = new EmptyFragment();
            dlf.show(getSupportFragmentManager(), "");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        startActivity(new Intent(regra_tres.this, form_choose.class));
        finish();
        return true;
    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(regra_tres.this, form_choose.class));
        finish();
    }
}
