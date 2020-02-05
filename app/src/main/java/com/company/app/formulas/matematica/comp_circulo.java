package com.company.app.formulas.matematica;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    // TODO Consertar o erro ao calcular o raio do circulo

    // TODO Criar um botão para definir o valor de pi
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comp_circulo);

        // Referencia os itens que serão utilizados
        comprimento = (EditText) findViewById(R.id.comprimento);
        raio = (EditText) findViewById(R.id.raio);
        line1 = (TextView) findViewById(R.id.line1);
        line2 = (TextView) findViewById(R.id.line2);
        line3 = (TextView) findViewById(R.id.line3);
        calcular = (Button) findViewById(R.id.calcular);

        // Define hasIntent como false
        hasIntent = false;

        // Define o valor de isDone como false
        isDone = false;

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                solve();
            }
        });
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
                Double[] dn = new Double[3];
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
                Double[] dn = new Double[3];
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
        else if (isDone){
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
    public void onBackPressed() {
        super.onBackPressed();
        Intent in = new Intent(comp_circulo.this, form_choose.class);
        startActivity(in);
        finish();
    }
}
