package com.company.app.formulas.quimica;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.company.app.ItensLibrary.EmptyFragment;
import com.company.app.ItensLibrary.TudoPreenchido;
import com.example.company.formulas.R;

import static android.view.View.GONE;

public class variacao_entalpia extends AppCompatActivity {
    // Declara os itens
    private EditText variacao;
    private EditText tf;
    private EditText ti;
    private Button calcular;
    private TextView line1;
    private TextView line2;
    private TextView line3;
    private boolean isDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_variacao_entalpia);

        // Referencia os itens utilizados na activity
        variacao = (EditText) findViewById(R.id.variacao);
        tf = (EditText) findViewById(R.id.tf);
        ti = (EditText) findViewById(R.id.ti);
        calcular = (Button) findViewById(R.id.calcular);
        line1 = (TextView) findViewById(R.id.line1);
        line2 = (TextView) findViewById(R.id.line2);
        line3 = (TextView) findViewById(R.id.line3);

        // Define isDone como false
        isDone = false;

        // Declara um listenner para o botão calcular
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                solve();
            }
        });
    }

    private void solve(){
        Double d_variacao, d_tf, d_ti;

        // Prevêm que ocorram erros de conversão
        try{
            d_variacao = Double.valueOf(variacao.getText().toString());
        }
        catch(Exception e){
            d_variacao = null;
        }

        try{
            d_tf = Double.valueOf(tf.getText().toString());
        }
        catch(Exception e){
            d_tf = null;
        }

        try{
            d_ti = Double.valueOf(ti.getText().toString());
        }
        catch(Exception e){
            d_ti = null;
        }

        // Executa o calculo de acordo com cada possibilidade
        if (!isDone){
            if(d_variacao == null && d_tf != null && d_ti != null){
                // Define a visibilidade das linhas como visivel
                line1.setVisibility(View.VISIBLE);
                line2.setVisibility(View.VISIBLE);

                line1.setText("ΔH = " + String.valueOf(d_tf) + " - " + String.valueOf(d_ti));
                line2.setText("ΔH = " + String.format("%.2f", d_tf - d_ti));

                // Define o estilo do botão calcular
                calcular.setBackgroundColor(ContextCompat.getColor(this,
                        R.color.clearButton));
                calcular.setText(R.string.limpar);
            }
            else if (d_variacao != null && d_tf == null && d_ti != null){
                // Define a visibilidade das linhas como visivel
                line1.setVisibility(View.VISIBLE);
                line2.setVisibility(View.VISIBLE);
                line3.setVisibility(View.VISIBLE);

                line1.setText(String.valueOf(d_variacao) + " = Tf - " + String.valueOf(d_ti));
                line2.setText("Tf = " + String.valueOf(d_variacao) + " + "  + String.valueOf(d_ti));
                line3.setText("Tf = " + String.format("%.2f", d_variacao = d_ti));

                // Define o estilo do botão calcular
                calcular.setBackgroundColor(ContextCompat.getColor(this,
                        R.color.clearButton));
                calcular.setText(R.string.limpar);
            }
            else if (d_variacao != null && d_tf != null && d_ti == null){
                // Define a visibilidade das linhas como visivel
                line1.setVisibility(View.VISIBLE);
                line2.setVisibility(View.VISIBLE);
                line3.setVisibility(View.VISIBLE);

                line1.setText(String.valueOf(d_variacao) + " = " + String.valueOf(d_tf) + " - Ti");
                line2.setText("Ti = " + String.valueOf(d_tf) + " - " + String.valueOf(d_variacao));
                line3.setText("Ti = " + String.format("%.2f", d_tf - d_variacao));

                // Define o estilo do botão calcular
                calcular.setBackgroundColor(ContextCompat.getColor(this,
                        R.color.clearButton));
                calcular.setText(R.string.limpar);
            }
            else if (d_variacao != null && d_tf != null && d_ti != null){
                TudoPreenchido td = new TudoPreenchido();
                td.show(getFragmentManager(), null);
            }
            else {
                EmptyFragment em = new EmptyFragment();
                em.show(getSupportFragmentManager(), null);
            }
        }
        else{
            // Redefine os estilos das linhas
            line1.setVisibility(GONE);
            line2.setVisibility(GONE);
            line3.setVisibility(GONE);

            // Deixa as linhas vazias
            line1.setText("");
            line2.setText("");
            line3.setText("");

            // Redefine o estilo do botão calcular
            calcular.setBackgroundColor(ContextCompat.getColor(this, R.color.AppThemeBlue));
            calcular.setText(R.string.Calc);
        }
    }
}