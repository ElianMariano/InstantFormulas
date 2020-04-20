package com.EducacaoApps.InstantFormulas.formulas.matematica;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
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
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.Random;

import static android.view.View.GONE;

// TODO Erro de calculo nesta formula
public class comp_circulo extends AppCompatActivity {
    // Itens que sserão usados
    private EditText comprimento;
    private EditText raio;
    private TextView line1;
    private TextView line2;
    private TextView line3;
    private Button calcular;
    // Variável que define o estado da fórmula
    private boolean isDone;
    // Valor de pi utilizado na formula
    private final Double PI = 3.14;
    private boolean hasIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comp_circulo);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        // Referencia os itens que serão utilizados
        comprimento = findViewById(R.id.comprimento);
        raio = findViewById(R.id.raio);
        line1 = findViewById(R.id.line1);
        line2 = findViewById(R.id.line2);
        line3 = findViewById(R.id.line3);
        calcular = findViewById(R.id.calcular);

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
            String s_comp, s_raio;

            // Previne que ocorram erros
            try{
                s_comp = split[0];
            }
            catch(IndexOutOfBoundsException e){
                s_comp = "";
            }

            try{
                s_raio = split[1];
            }
            catch(IndexOutOfBoundsException e){
                s_raio = "";
            }

            // Preenche os edittexts com os respectivos valores
            comprimento.setText(s_comp);
            raio.setText(s_raio);

            // Executa o calculo
            solve();
        }

        // Define o valor de isDone como false
        isDone = false;

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                solve();
            }
        });

        // Cria o botão voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        MobileAds.initialize(this);
    }

    public void solve(){
        // Variáveis que armazenam os valores dados pelo usuário
        Double Dnum1, Dnum2;

        // Prevêm que ocorram possíveis erros
        try{
            Dnum1 = Double.valueOf(comprimento.getText().toString());
        }
        catch(Exception e){
            Dnum1 = null;
        }

        try{
            Dnum2 = Double.valueOf(raio.getText().toString());
        }
        catch(Exception e){
            Dnum2 = null;
        }

        if((Dnum1 == null && Dnum2 != null) && !isDone){
            // Define o valor de isDone
            isDone = true;

            // Define a visibilidade das linhas como visible
            line1.setVisibility(View.VISIBLE);
            line2.setVisibility(View.VISIBLE);
            line3.setVisibility(View.VISIBLE);

            // Define o conteeúdo das linhas
            line1.setText("C = 2 * " + String.valueOf(PI) + " * " + String.valueOf(Dnum2));
            line2.setText("C = " + String.valueOf(2 * PI) + " * " + String.valueOf(Dnum2));
            line3.setText("C = " + String.format("%.2f", 2 * PI * Dnum2));

            // Define o estilo do botão
            calcular.setBackgroundColor(ContextCompat.getColor(this, R.color.clearButton));
            calcular.setText(R.string.limpar);

            if (!hasIntent){
                // Variáveis que obtem os dados
                Double[] dn = new Double[2];
                dn[0] = null;
                dn[1] = Dnum2;

                // String que armazena o dado convertido
                String data = ConvertStringtoData.DataToString(dn);

                // Cria um ContentValues
                ContentValues cv = new ContentValues();
                cv.put("titulo", getResources().getString(R.string.comp_circulo));
                cv.put("data", data);

                // Cria uma instância do banco de dados
                HistoricoHelper hh = new HistoricoHelper(this);
                hh.inserir(cv);
            }
        }
        else if ((Dnum1 != null && Dnum2 == null) && isDone == false){
            // Define o valor de isDone
            isDone = true;

            // Define a visibilidade das linhas como visible
            line1.setVisibility(View.VISIBLE);
            line2.setVisibility(View.VISIBLE);
            line3.setVisibility(View.VISIBLE);

            // Define o conteeúdo das linhas
            line1.setText(String.valueOf(Dnum1) + " = 2 * " + String.valueOf(PI) + " *  R");
            line2.setText("R = " + String.valueOf(2 * PI) + " * " + String.valueOf(Dnum1));
            line3.setText("R = " + String.format("%.2f", 2 * PI * Dnum1));

            // Define o estilo do botão
            calcular.setBackgroundColor(ContextCompat.getColor(this, R.color.clearButton));
            calcular.setText(R.string.limpar);

            if (!hasIntent){
                // Variáveis que obtem os dados
                Double[] dn = new Double[2];
                dn[0] = Dnum1;
                dn[1] = null;

                // String que armazena o dado convertido
                String data = ConvertStringtoData.DataToString(dn);

                // Cria um ContentValues
                ContentValues cv = new ContentValues();
                cv.put("titulo", getResources().getString(R.string.comp_circulo));
                cv.put("data", data);

                // Cria uma instância do banco de dados
                HistoricoHelper hh = new HistoricoHelper(this);
                hh.inserir(cv);
            }
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

            // Define o valor de isDone
            isDone = false;

            // Define a visibilidade das linhas como gone
            line1.setVisibility(GONE);
            line2.setVisibility(GONE);
            line3.setVisibility(GONE);

            // Define o conteúdo dos EditTexts como vazios
            comprimento.setText("");
            raio.setText("");

            // Define o estilo do botão
            calcular.setBackgroundColor(ContextCompat.getColor(this, R.color.AppThemeBlue));
            calcular.setText(R.string.Calc);
        }
        else if(Dnum1 == null && Dnum2 == null){
            // Executado quando nenhum dos campos estão preenchidos
            EmptyFragment em = new EmptyFragment();
            em.show(getSupportFragmentManager(), "");
        }
        else if (Dnum1 != null && Dnum2 != null){
            // Executado quando todos os campos estão preenchidos
            TudoPreenchido td = new TudoPreenchido();
            td.show(getFragmentManager(), "");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        startActivity(new Intent(comp_circulo.this, form_choose.class));
        finish();
        return true;
    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(comp_circulo.this, form_choose.class));
        finish();
    }
}
