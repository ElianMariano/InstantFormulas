package com.company.app.formulas.fisica;

import android.annotation.SuppressLint;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.lang.Math;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.company.app.ItensLibrary.EmptyFragment;
import com.company.app.ItensLibrary.TudoPreenchido;
import com.example.company.formulas.R;

public class equa_torricelli extends AppCompatActivity {
    private EditText V, Vo, a, d;
    private TextView line1, line2, line3, line4, line5, line6, line7;
    private Button calcular;
    private boolean isDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equa_torricelli);

        // Referencia os itens
        V = (EditText) findViewById(R.id.V);
        Vo = (EditText) findViewById(R.id.Vo);
        a = (EditText) findViewById(R.id.a);
        d = (EditText) findViewById(R.id.d);
        line1 = (TextView) findViewById(R.id.line1);
        line2 = (TextView) findViewById(R.id.line2);
        line3 = (TextView) findViewById(R.id.line3);
        line4 = (TextView) findViewById(R.id.line4);
        line5 = (TextView) findViewById(R.id.line5);
        line6 = (TextView) findViewById(R.id.line6);
        line7 = (TextView) findViewById(R.id.line7);
        calcular = (Button) findViewById(R.id.calcular);

        // Define o valor de isDone como false
        isDone = false;

        // Define um listener para o botão calcular
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                solve();
            }
        });
    }

    @SuppressLint("DefaultLocale")
    private void solve(){
        Double dv, dvo, da, dd;

        // Previne que ocorram erros
        try{
            dv = Double.valueOf(V.getText().toString());
        }
        catch(Exception e){
            dv = null;
        }

        try{
            dvo = Double.valueOf(Vo.getText().toString());
        }
        catch(Exception e){
            dvo = null;
        }

        try{
            da = Double.valueOf(a.getText().toString());
        }
        catch(Exception e){
            da = null;
        }

        try{
            dd = Double.valueOf(d.getText().toString());
        }
        catch(Exception e){
            dd = null;
        }

        if (!isDone){
            // Define o valor de isDone como true
            isDone = true;

            // Realiza o calculo de acordo com as possibilidades
            if (dv == null && dvo != null && da != null && dd != null){
                // Define a visibilidade das linhas
                line1.setVisibility(View.VISIBLE);
                line2.setVisibility(View.VISIBLE);
                line3.setVisibility(View.VISIBLE);
                line4.setVisibility(View.VISIBLE);
                line5.setVisibility(View.VISIBLE);
                line6.setVisibility(View.VISIBLE);
                line7.setVisibility(View.VISIBLE);

                // Strings usadas nas linhas
                String l, l2, l3, l4, l5, l6, l7;

                // Variável que armazena o resultado
                Double rst = Math.sqrt((dvo * dvo) + (da * 2 * dd));

                l = "V² = " + String.valueOf(dvo) + "² + 2 * " + String.valueOf(da) + " * " +
                        String.valueOf(dd);
                l2 = "V² = " + String.valueOf(dvo) + "² + " + String.format("%.2f", da * 2) + " * " +
                        String.valueOf(dd);
                l3 = "V² = " + String.valueOf(dvo) + "² + " + String.format("%.2f", da * 2 * dd);
                l4 = "V² = " + String.format("%.2f", dvo * dvo) + " + "
                        + String.format("%.2f", da * 2 * dd);
                l5 = "V² = " + String.format("%.2f", (dvo * dvo) + (da * 2 * dd));
                l6 = "V = √" + String.format("%.2f", (dvo * dvo) + (da * 2 * dd));
                l7 = "V = " + String.format("%.2f", rst);

                line1.setText(l);
                line2.setText(l2);
                line3.setText(l3);
                line4.setText(l4);
                line5.setText(l5);
                line6.setText(l6);
                line7.setText(l7);

                // Define o estilo do botão
                calcular.setBackgroundColor(ContextCompat.getColor(this,
                        R.color.clearButton));
                calcular.setText(R.string.limpar);
            }
            else if (dv != null && dvo == null && da != null && dd != null){
                // Define a visibilidade das linhas
                line1.setVisibility(View.VISIBLE);
                line2.setVisibility(View.VISIBLE);
                line3.setVisibility(View.VISIBLE);
                line4.setVisibility(View.VISIBLE);
                line5.setVisibility(View.VISIBLE);
                line6.setVisibility(View.VISIBLE);
                line7.setVisibility(View.VISIBLE);

                // Strings usadas nas linhas
                String l, l2, l3, l4, l5, l6, l7;

                // Variável que armazena o resultado
                Double rst = Math.sqrt((dv * dv) - (2 * da * dd));

                // Declara as variáveis
                l = String.valueOf(dv) + "² = Vo² + 2 * " + String.valueOf(da) + " * " +
                        String.valueOf(dd);
                l2 = String.valueOf(dv) + "² = Vo² + " + String.format("%.2f", da * 2) + " * " +
                        String.valueOf(dd);

                l3 = String.format("%.2f", dv * dv) + " = Vo² + " + String.format("%.2f", 2 * da * dd);
                l4 = "Vo² = " + String.format("%.2f", dv * dv) + " - " +
                        String.format("%.2f", 2 * da * dd);
                l5 = "Vo² = " + String.format("%.2f", (dv * dv) - (2 * da * dd));
                l6 = "Vo = √" + String.format("%.2f", (dv * dv) - (2 * da * dd));
                l7 = "Vo = " + String.format("%.2f", rst);

                line1.setText(l);
                line2.setText(l2);
                line3.setText(l3);
                line4.setText(l4);
                line5.setText(l5);
                line6.setText(l6);
                line7.setText(l7);

                // Define o estilo do botão
                calcular.setBackgroundColor(ContextCompat.getColor(this,
                        R.color.clearButton));
                calcular.setText(R.string.limpar);
            }
            else if (dv != null && dvo != null && da == null && dd != null){
                // Define a visibilidade das linhas
                line1.setVisibility(View.VISIBLE);
                line2.setVisibility(View.VISIBLE);
                line3.setVisibility(View.VISIBLE);
                line4.setVisibility(View.VISIBLE);
                line5.setVisibility(View.VISIBLE);
                line6.setVisibility(View.VISIBLE);
                line7.setVisibility(View.VISIBLE);

                // Strings usadas nas linhas
                String l, l2, l3, l4, l5, l6, l7;

                // Declara as variáveis
                l = String.valueOf(dv) + "² = " + String.valueOf(dvo) + "² + 2 * a * " +
                        String.valueOf(dd);
                l2 = String.valueOf(dv) + "² = " + String.valueOf(dvo) + "² + " +
                        String.format("%.2f", 2 * dd) + " * a";
                l3 = String.format("%.2f", dv * dv) + " = " + String.format("%.2f", dvo * dvo) +
                        " + " + String.format("%.2f", 2 * dd) + " * a";
                l4 = String.format("%.2f", 2 * dd) + " * a = " + String.format("%.2f", dv * dv) +
                        " - " + String.format("%.2f", dvo * dvo);
                l5 = String.format("%.2f", 2 * dd) + " * a = " +
                        String.format("%.2f", (dv * dv)- (dvo * dvo));
                l6 = "a = " + String.format("%.2f", (dv * dv)- (dvo * dvo)) + "/" +
                        String.format("%.2f", 2 * dd);
                l7 = "a = " + String.format("%.2f", ((dv * dv)- (dvo * dvo)) / (2 * dd));

                line1.setText(l);
                line2.setText(l2);
                line3.setText(l3);
                line4.setText(l4);
                line5.setText(l5);
                line6.setText(l6);
                line7.setText(l7);

                // Define o estilo do botão
                calcular.setBackgroundColor(ContextCompat.getColor(this,
                        R.color.clearButton));
                calcular.setText(R.string.limpar);
            }
            else if(dv != null && dvo != null && da != null && dd == null){
                // Define a visibilidade das linhas
                line1.setVisibility(View.VISIBLE);
                line2.setVisibility(View.VISIBLE);
                line3.setVisibility(View.VISIBLE);
                line4.setVisibility(View.VISIBLE);
                line5.setVisibility(View.VISIBLE);
                line6.setVisibility(View.VISIBLE);
                line7.setVisibility(View.VISIBLE);

                // Strings usadas nas linhas
                String l, l2, l3, l4, l5, l6, l7;

                // Declara as variáveis
                l = String.valueOf(dv) + "² = " + String.valueOf(dvo) + "² + 2 * " +
                        String.valueOf(da) + " * Δd";
                l2 = String.valueOf(dv) + "² = " + String.valueOf(dvo) + "² + " +
                        String.format("%.2f", 2 * da) + " * Δd";
                l3 = String.format("%.2f", dv * dv) + " = " + String.format("%.2f", dvo * dvo) +
                        " + " + String.format("%.2f", 2 * da) + " * Δd";
                l4 = String.format("%.2f", 2 * da) + " * Δd = " + String.format("%.2f", dv * dv) +
                        " - " + String.format("%.2f", dvo * dvo);
                l5 = String.format("%.2f", 2 * da) + " * Δd = " +
                        String.format("%.2f", (dv * dv) - (dvo * dvo));
                l6 = "Δd = " + String.format("%.2f", (dv * dv) - (dvo * dvo)) + "/" +
                        String.format("%.2f", 2 * da);
                l7 = "Δd = " + String.format("%.2f", ((dv * dv) - (dvo * dvo)) / (2 * da));

                line1.setText(l);
                line2.setText(l2);
                line3.setText(l3);
                line4.setText(l4);
                line5.setText(l5);
                line6.setText(l6);
                line7.setText(l7);

                // Define o estilo do botão
                calcular.setBackgroundColor(ContextCompat.getColor(this,
                        R.color.clearButton));
                calcular.setText(R.string.limpar);
            }
            else if (dv == null && dvo == null && da == null && dd == null){
                TudoPreenchido td = new TudoPreenchido();
                td.show(getFragmentManager(), null);
            }
            else{
                EmptyFragment em = new EmptyFragment();
                em.show(getSupportFragmentManager(), null);
            }
        }
        else{
            // Define o valor de isDone como false
            isDone = false;

            // Apaga os textos dos Edittexts
            V.setText("");
            Vo.setText("");
            a.setText("");
            d.setText("");

            // Redefine a visibilidade das linhas
            line1.setVisibility(View.GONE);
            line2.setVisibility(View.GONE);
            line3.setVisibility(View.GONE);
            line4.setVisibility(View.GONE);
            line5.setVisibility(View.GONE);
            line6.setVisibility(View.GONE);
            line7.setVisibility(View.GONE);

            // Redefine o estilo do botão
            calcular.setBackgroundColor(ContextCompat.getColor(this, R.color.AppThemeBlue));
            calcular.setText(R.string.Calc);
        }
    }
}
