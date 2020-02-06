package com.company.app.formulas.matematica;

import android.annotation.SuppressLint;
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

import com.company.app.ConvertStringtoData;
import com.company.app.Models.HistoricoHelper;
import com.company.app.ItensLibrary.EmptyFragment;
import com.company.app.ItensLibrary.TudoPreenchido;
import com.company.app.form_choose;
import com.company.app.formulas.fisica.acel_media;
import com.example.company.formulas.R;

import static android.view.View.GONE;

public class area_q extends AppCompatActivity {
    // Cria as variáveis que referenciam os itens da activity
    private Button calcular;
    private EditText num1, num2, num3;
    private TextView line1, line2, line3;
    private boolean hasIntent;
    // Vairável que verifica se ja esta calculado
    private boolean isDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_q);

        // Declara o valor das variáveis
        calcular = (Button) findViewById(R.id.calcular);
        num1 = (EditText) findViewById(R.id.num1);
        num2 = (EditText) findViewById(R.id.num2);
        num3 = (EditText) findViewById(R.id.num3);
        line1 = (TextView) findViewById(R.id.line1);
        line2 = (TextView) findViewById(R.id.line2);
        line3 = (TextView) findViewById(R.id.line3);

        // Declara o valor de isDone como false
        isDone = false;

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                solve();
            }
        });

        // Define o valor de hasIntent como false
        hasIntent = false;

        // Obtêm o intent
        Intent in = getIntent();
        String data = in.getStringExtra("data");

        // Array que armazena os dados divididos
        String[] split;

        if (data != null){
            // Define hasIntent como true
            hasIntent = true;

            // Converte os dados e armazena na variável
            split = ConvertStringtoData.SplitString(data);

            // Variáveis que armazenam os textos usados nos campos
            String snum, snum2, snum3;

            // Previne que ocorram erros
            try{
                snum = split[0];
            }
            catch(IndexOutOfBoundsException e){
                snum = "";
            }

            try{
                snum2 = split[1];
            }
            catch(IndexOutOfBoundsException e){
                snum2 = "";
            }

            try{
                snum3 = split[2];
            }
            catch(IndexOutOfBoundsException e){
                snum3 = "";
            }

            // Define os textos do EditTexts
            num1.setText(snum);
            num2.setText(snum2);
            num3.setText(snum3);

            // Executa o calculo
            solve();
        }

        // Cria o botão voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @SuppressLint("DefaultLocale")
    private void solve(){
        // Variaveis que armazenam os valores dos campos de texto
        Double Dnum1, Dnum2, Dnum3;
        // Prevem possíveis erros de conversão de String para Double
        try{
            Dnum1 = Double.parseDouble(num1.getText().toString());
        }
        catch (Exception e){
            Dnum1 = null;
        }

        try{
            Dnum2 = Double.parseDouble(num2.getText().toString());
        }
        catch (Exception e){
            Dnum2 = null;
        }

        try{
            Dnum3 = Double.parseDouble(num3.getText().toString());
        }
        catch (Exception e){
            Dnum3 = null;
        }

        // Executa o código necessário para cada situação
        if((Dnum1 == null && Dnum2 != null && Dnum3 != null) && !isDone){
            // Define o valor de isDone
            isDone = true;

            // Define as linha como visivel
            line1.setVisibility(View.VISIBLE);
            line2.setVisibility(View.VISIBLE);

            // Armazena o resultado
            Double rst = Dnum2 * Dnum3;

            String l1, l2;

            l1 = "A = " + String.valueOf(Dnum2) + " * " + String.valueOf(Dnum3);
            l2 = "A = " + String.format("%.2f", rst);

            // Define o conteudo das linhas
            line1.setText(l1);
            line2.setText(l2);

            // Define o estilo do botão
            calcular.setBackgroundColor(ContextCompat.getColor(this, R.color.clearButton));
            calcular.setText(R.string.limpar);

            if (!hasIntent){
                // Variáveis que obtem os dados
                Double[] dn = new Double[3];
                dn[0] = null;
                dn[1] = Dnum2;
                dn[2] = Dnum3;

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
        else if ((Dnum1 != null && Dnum2 == null && Dnum3 != null) && !isDone){
            // Define o valor de isDone
            isDone = true;

            // Define a visibilidade das linha
            line1.setVisibility(View.VISIBLE);
            line2.setVisibility(View.VISIBLE);
            line3.setVisibility(View.VISIBLE);

            // Armazena o resultado da operação
            Double rst = Dnum1 / Dnum3;

            // Variáveis que armazenam as linhas
            String l1, l2, l3;

            l1 = String.valueOf(Dnum1) + " =  b * " + String.valueOf(Dnum3);
            l2 = "b = " + String.valueOf(Dnum1) + "/" + String.valueOf(Dnum3);
            l3 = "b = " + String.format("%.2f", rst);

            // Mostra o calculo na tela
            line1.setText(l1);
            line2.setText(l2);
            line3.setText(l3);

            // Define o estilo do botão
            calcular.setBackgroundColor(ContextCompat.getColor(this, R.color.clearButton));
            calcular.setText(R.string.limpar);

            if (!hasIntent){
                // Variáveis que obtem os dados
                Double[] dn = new Double[3];
                dn[0] = Dnum1;
                dn[1] = null;
                dn[2] = Dnum3;

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
        else if ((Dnum1 != null && Dnum2 != null && Dnum3 == null) && !isDone){
            // Define o valor de isDone
            isDone = true;

            // Define a visibilidade das linhas
            line1.setVisibility(View.VISIBLE);
            line2.setVisibility(View.VISIBLE);
            line3.setVisibility(View.VISIBLE);

            // Armazena o resultado
            Double rst = Dnum1 / Dnum2;

            // Variável que armazena as linhas
            String l1, l2, l3;

            l1 = String.valueOf(Dnum1) + " = " + String.valueOf(Dnum2) + " * h";
            l2 = "h = " + String.valueOf(Dnum1) + "/" + String.valueOf(Dnum2);
            l3 = "h = " + String.format("%.2f", rst);

            line1.setText(l1);
            line2.setText(l2);
            line3.setText(l3);

            // Define o estilo do botão
            calcular.setBackgroundColor(ContextCompat.getColor(this, R.color.clearButton));
            calcular.setText(R.string.limpar);

            if (!hasIntent){
                // Variáveis que obtem os dados
                Double[] dn = new Double[3];
                dn[0] = Dnum1;
                dn[1] = Dnum2;
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
        else if ((Dnum1 != null && Dnum2 != null && Dnum3 != null) && !isDone){
            // Chama o AlertDialog quando todos os campos estão preenchidos
            TudoPreenchido td = new TudoPreenchido();
            td.show(getFragmentManager(), "");
        }
        else if (isDone){
            // Define isDone como false
            isDone = false;

            // Define a visibilidade das linhas como gone
            line1.setVisibility(GONE);
            line2.setVisibility(GONE);
            line3.setVisibility(GONE);

            // Define os campos como vazios
            num1.setText("");
            num2.setText("");
            num3.setText("");

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
        startActivity(new Intent(area_q.this, form_choose.class));
        finish();
        return true;
    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(area_q.this, form_choose.class));
        finish();
    }
}
