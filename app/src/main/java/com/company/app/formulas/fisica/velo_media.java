package com.company.app.formulas.fisica;

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
import static android.view.View.VISIBLE;

// TODO Consertar o erro de calculo ocorrido quando o ultimo dado não esta preenchido
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_velo_media);

        // Referencia os itens da activity
        velocidade = (EditText) findViewById(R.id.velocidade);
        distancia = (EditText) findViewById(R.id.distancia);
        tempo = (EditText) findViewById(R.id.tempo);
        calcular = (Button) findViewById(R.id.calcular);
        line = (TextView) findViewById(R.id.line);
        line2 = (TextView) findViewById(R.id.line2);
        line3 = (TextView) findViewById(R.id.line3);
        line4 = (TextView) findViewById(R.id.line4);

        // Define o valor de isDone
        isDone = false;

        // Define um listener para o botão calcular
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                solve();
            }
        });
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
}
