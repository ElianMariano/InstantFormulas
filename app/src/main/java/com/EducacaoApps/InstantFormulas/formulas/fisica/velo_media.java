package com.EducacaoApps.InstantFormulas.formulas.fisica;

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
import static android.view.View.VISIBLE;

public class velo_media extends AppCompatActivity {
    private EditText velocidade;
    private EditText distancia;
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
        setContentView(R.layout.activity_velo_media);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        // Referencia os itens da activity
        velocidade = findViewById(R.id.velocidade);
        distancia = findViewById(R.id.distancia);
        tempo = findViewById(R.id.tempo);
        calcular = findViewById(R.id.calcular);
        line = findViewById(R.id.line);
        line2 = findViewById(R.id.line2);
        line3 = findViewById(R.id.line3);
        line4 = findViewById(R.id.line4);

        // Define o valor de isDone
        isDone = false;

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
            String sv, sd, st;

            // Previne que ocorram erros
            try{
                sv = split[0];
            }
            catch(IndexOutOfBoundsException e){
                sv = "";
            }

            try{
                sd = split[1];
            }
            catch(IndexOutOfBoundsException e){
                sd = "";
            }

            try{
                st = split[2];
            }
            catch(IndexOutOfBoundsException e){
                st = "";
            }

            // Preenche os edittexts com os respectivos valores
            velocidade.setText(sv);
            distancia.setText(sd);
            tempo.setText(st);

            // Executa o calculo
            solve();
        }

        // Define um listener para o botão calcular
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                solve();
            }
        });

        // Cria o botão voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // Inicializa os anúncios
        MobileAds.initialize(this);
    }

    private void solve(){
        // Define as variáveis a serem utilizadas
        Double velo, dis, tem;

        // Prevem que ocorram erros
        try{
            velo = Double.parseDouble(velocidade.getText().toString());
        }
        catch (Exception e){
            velo = null;
        }

        try{
            dis = Double.parseDouble(distancia.getText().toString());
        }
        catch (Exception e){
            dis = null;
        }

        try{
            tem = Double.parseDouble(tempo.getText().toString());
        }
        catch (Exception e){
            tem = null;
        }

        if (!isDone){
            if (velo == null && dis != null && tem != null){
                // Define o valor de isDone como true
                isDone = true;

                // Define a visibilidade das linhas como VISIBLE
                line.setVisibility(VISIBLE);
                line2.setVisibility(VISIBLE);

                // Mostra o resultado
                line.setText("V = " + String.valueOf(dis) + "/" + String.valueOf(tem));
                line2.setText("V = " + String.format("%.2f", dis / tem));

                // Define o estilo do botão
                calcular.setBackgroundColor(ContextCompat.getColor(this, R.color.clearButton));
                calcular.setText(R.string.limpar);

                if (!hasIntent){
                    // Variáveis que obtem os dados
                    Double[] dn = new Double[3];
                    dn[0] = null;
                    dn[1] = dis;
                    dn[2] = tem;

                    // String que armazena o dado convertido
                    String data = ConvertStringtoData.DataToString(dn);

                    // Cria um ContentValues
                    ContentValues cv = new ContentValues();
                    cv.put("titulo", getResources().getString(R.string.velo_media));
                    cv.put("data", data);

                    // Cria uma instância do banco de dados
                    HistoricoHelper hh = new HistoricoHelper(this);
                    hh.inserir(cv);
                }
            }
            else if (velo != null && dis == null && tem != null){
                // Define o valor de isDone como true
                isDone = true;

                // Define a visibilidade das linhas como VISIBLE
                line.setVisibility(VISIBLE);
                line2.setVisibility(VISIBLE);
                line3.setVisibility(VISIBLE);

                // Mostra o resultado
                line.setText(String.valueOf(velo) + " = Δd/" + String.valueOf(tem));
                line2.setText("Δd = " + String.valueOf(velo) + " * " + String.valueOf(tem));
                line3.setText("Δd = " + String.format("%.2f", velo * tem));

                // Define o estilo do botão
                calcular.setBackgroundColor(ContextCompat.getColor(this, R.color.clearButton));
                calcular.setText(R.string.limpar);

                if (!hasIntent){
                    // Variáveis que obtem os dados
                    Double[] dn = new Double[3];
                    dn[0] = velo;
                    dn[1] = null;
                    dn[2] = tem;

                    // String que armazena o dado convertido
                    String data = ConvertStringtoData.DataToString(dn);

                    // Cria um ContentValues
                    ContentValues cv = new ContentValues();
                    cv.put("titulo", getResources().getString(R.string.velo_media));
                    cv.put("data", data);

                    // Cria uma instância do banco de dados
                    HistoricoHelper hh = new HistoricoHelper(this);
                    hh.inserir(cv);
                }
            }
            else if (velo != null && dis != null && tem == null){
                // Define o valor de isDone como true
                isDone = true;

                // Define a visibilidade das linhas como VISIBLE
                line.setVisibility(VISIBLE);
                line2.setVisibility(VISIBLE);
                line3.setVisibility(VISIBLE);
                line4.setVisibility(VISIBLE);

                // Mostra o resultado
                line.setText(String.valueOf(velo) + " = " + String.valueOf(dis) + "/Δt");
                line2.setText(String.valueOf(velo) + " * Δt = " + String.valueOf(dis));
                line3.setText("Δt = " + String.valueOf(dis) + "/" + String.valueOf(velo));
                line4.setText("Δt = " + String.format("%.2f", dis / velo));

                // Define o estilo do botão
                calcular.setBackgroundColor(ContextCompat.getColor(this, R.color.clearButton));
                calcular.setText(R.string.limpar);

                if (!hasIntent){
                    // Variáveis que obtem os dados
                    Double[] dn = new Double[3];
                    dn[0] = velo;
                    dn[1] = dis;
                    dn[2] = null;

                    // String que armazena o dado convertido
                    String data = ConvertStringtoData.DataToString(dn);

                    // Cria um ContentValues
                    ContentValues cv = new ContentValues();
                    cv.put("titulo", getResources().getString(R.string.velo_media));
                    cv.put("data", data);

                    // Cria uma instância do banco de dados
                    HistoricoHelper hh = new HistoricoHelper(this);
                    hh.inserir(cv);
                }
            }
            else if (velo != null && dis != null && tem != null){
                TudoPreenchido td = new TudoPreenchido();
                td.show(getFragmentManager(), null);
            }
            else{
                EmptyFragment em = new EmptyFragment();
                em.show(getSupportFragmentManager(), null);
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

            // Redefine o valor de isDone
            isDone = false;

            // Define a visibilidade das linhas como GONE
            line.setVisibility(GONE);
            line2.setVisibility(GONE);
            line3.setVisibility(GONE);
            line4.setVisibility(GONE);

            // Deixa os campos de texto vazios
            velocidade.setText("");
            distancia.setText("");
            tempo.setText("");

            // Define o estilo do botão
            calcular.setBackgroundColor(ContextCompat.getColor(this, R.color.AppThemeBlue));
            calcular.setText(R.string.Calc);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        startActivity(new Intent(velo_media.this, form_choose.class));
        finish();
        return true;
    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(velo_media.this, form_choose.class));
        finish();
    }
}
