package com.company.app.formulas.fisica;

import android.content.ContentValues;
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
import com.example.company.formulas.R;

import org.w3c.dom.Text;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class dilatacao_linear extends AppCompatActivity {
    private EditText v_comp;
    private EditText comp_i;
    private EditText alfa;
    private EditText v_tempo;
    private TextView line;
    private TextView line2;
    private TextView line3;
    private TextView line4;
    private Button calcular;
    private boolean isDone;
    private boolean hasIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dilatacao_linear);

        // Referencia os itens utilizados a activity
        v_comp = findViewById(R.id.v_comp);
        comp_i = findViewById(R.id.comp_i);
        alfa = findViewById(R.id.alfa);
        v_tempo = findViewById(R.id.v_tempo);
        line = findViewById(R.id.line);
        line2 = findViewById(R.id.line2);
        line3 = findViewById(R.id.line3);
        line4 = findViewById(R.id.line4);
        calcular = findViewById(R.id.calcular);

        // Define o valor de isDone
        isDone = false;

        // Define hasIntent como false
        hasIntent = false;

        // Cria um listener para o botão calcular
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                solve();
            }
        });
    }

    private void solve(){
        Double d_comp, d_comp_i, d_alfa, dv_tempo;

        // Evita que ocorram erros de conversão
        try{
            d_comp = Double.parseDouble(v_comp.getText().toString());
        }
        catch (Exception e){
            d_comp = null;
        }

        try{
            d_comp_i = Double.parseDouble(comp_i.getText().toString());
        }
        catch (Exception e){
            d_comp_i = null;
        }

        try{
            d_alfa = Double.parseDouble(alfa.getText().toString());
        }
        catch (Exception e){
            d_alfa = null;
        }

        try{
            dv_tempo = Double.parseDouble(v_tempo.getText().toString());
        }
        catch (Exception e){
            dv_tempo = null;
        }

        if (!isDone){
            if (d_comp == null && d_comp_i != null && d_alfa != null && dv_tempo != null){
                // Define o valor de isDone como true
                isDone = true;

                // Define a visibilidade dass linhas como VISIBLE
                line.setVisibility(VISIBLE);
                line2.setVisibility(VISIBLE);
                line3.setVisibility(VISIBLE);

                line.setText("ΔL = " + String.valueOf(d_comp_i) + " * " + String.valueOf(d_alfa) +
                " * " + String.valueOf(dv_tempo));
                line2.setText("ΔL = " + String.format("%.2f", d_comp_i * d_alfa) + " * " +
                String.valueOf(dv_tempo));
                line3.setText("ΔL = " + String.format("%.2f", d_comp_i * d_alfa * dv_tempo));

                // Define o estilo do botaão calcular
                calcular.setText(R.string.limpar);
                calcular.setBackgroundColor(ContextCompat.getColor(this,
                        R.color.clearButton));

                if (!hasIntent){
                    // Variáveis que obtem os dados
                    Double[] dn = new Double[4];
                    dn[0] = null;
                    dn[1] = d_comp_i;
                    dn[2] = d_alfa;
                    dn[3] = dv_tempo;

                    // String que armazena o dado convertido
                    String data = ConvertStringtoData.DataToString(dn);

                    // Cria um ContentValues
                    ContentValues cv = new ContentValues();
                    cv.put("titulo", getResources().getString(R.string.dilat_linear));
                    cv.put("data", data);

                    // Cria uma instância do banco de dados
                    HistoricoHelper hh = new HistoricoHelper(this);
                    hh.inserir(cv);
                }
            }
            else if (d_comp != null && d_comp_i == null && d_alfa != null && dv_tempo != null){
                // Define o valor de isDone como true
                isDone = true;

                // Define a visibilidade dass linhas como VISIBLE
                line.setVisibility(VISIBLE);
                line2.setVisibility(VISIBLE);
                line3.setVisibility(VISIBLE);
                line4.setVisibility(VISIBLE);

                line.setText(String.valueOf(d_comp) + " = Li * " + String.valueOf(d_alfa) + " * " +
                String.valueOf(dv_tempo));
                line2.setText(String.valueOf(d_comp) + " = Li * " +
                        String.format("%.2f", d_alfa * dv_tempo));
                line3.setText("Li = " + String.valueOf(d_comp) + "/" +
                        String.format("%.2f", d_alfa * dv_tempo));
                line4.setText("Li = " + String.format("%.2f", d_comp / (d_alfa * dv_tempo)));

                // Define o estilo do botaão calcular
                calcular.setText(R.string.limpar);
                calcular.setBackgroundColor(ContextCompat.getColor(this,
                        R.color.clearButton));

                if (!hasIntent){
                    // Variáveis que obtem os dados
                    Double[] dn = new Double[4];
                    dn[0] = d_comp;
                    dn[1] = null;
                    dn[2] = d_alfa;
                    dn[3] = dv_tempo;

                    // String que armazena o dado convertido
                    String data = ConvertStringtoData.DataToString(dn);

                    // Cria um ContentValues
                    ContentValues cv = new ContentValues();
                    cv.put("titulo", getResources().getString(R.string.dilat_linear));
                    cv.put("data", data);

                    // Cria uma instância do banco de dados
                    HistoricoHelper hh = new HistoricoHelper(this);
                    hh.inserir(cv);
                }
            }
            else if (d_comp != null && d_comp_i != null && d_alfa == null && dv_tempo != null){
                // Define o valor de isDone como true
                isDone = true;

                // Define a visibilidade dass linhas como VISIBLE
                line.setVisibility(VISIBLE);
                line2.setVisibility(VISIBLE);
                line3.setVisibility(VISIBLE);
                line4.setVisibility(VISIBLE);

                line.setText(String.valueOf(d_comp) + " = " + String.valueOf(d_comp_i) + " * α * "
                + String.valueOf(dv_tempo));
                line2.setText(String.valueOf(d_comp) + " = α * " +
                        String.format("%.2f", d_comp_i * dv_tempo));
                line3.setText("α = " + String.valueOf(d_comp) + "/" +
                        String.format("%.2f", d_comp_i * dv_tempo));
                line4.setText("α = " + String.format("%.2f", d_comp / (d_comp_i * dv_tempo)));

                // Define o estilo do botaão calcular
                calcular.setText(R.string.limpar);
                calcular.setBackgroundColor(ContextCompat.getColor(this,
                        R.color.clearButton));

                if (!hasIntent){
                    // Variáveis que obtem os dados
                    Double[] dn = new Double[4];
                    dn[0] = d_comp;
                    dn[1] = d_comp_i;
                    dn[2] = null;
                    dn[3] = dv_tempo;

                    // String que armazena o dado convertido
                    String data = ConvertStringtoData.DataToString(dn);

                    // Cria um ContentValues
                    ContentValues cv = new ContentValues();
                    cv.put("titulo", getResources().getString(R.string.dilat_linear));
                    cv.put("data", data);

                    // Cria uma instância do banco de dados
                    HistoricoHelper hh = new HistoricoHelper(this);
                    hh.inserir(cv);
                }
            }
            else if (d_comp != null && d_comp_i != null && d_alfa != null && dv_tempo == null){
                // Define o valor de isDone como true
                isDone = true;

                // Define a visibilidade dass linhas como VISIBLE
                line.setVisibility(VISIBLE);
                line2.setVisibility(VISIBLE);
                line3.setVisibility(VISIBLE);
                line4.setVisibility(VISIBLE);

                line.setText(String.valueOf(d_comp) + " = " + String.valueOf(d_comp_i) + " * "
                + String.valueOf(d_alfa) + " * Δt");
                line2.setText(String.valueOf(d_comp) + " = Δt * " +
                        String.format("%.2f", d_comp_i * d_alfa));
                line3.setText("Δt = " + String.valueOf(d_comp) + "/" +
                        String.format("%.2f", d_comp_i * d_alfa));
                line4.setText("Δt = " + String.format("%.2f", d_comp / (d_comp_i * d_alfa)));

                // Define o estilo do botaão calcular
                calcular.setText(R.string.limpar);
                calcular.setBackgroundColor(ContextCompat.getColor(this,
                        R.color.clearButton));

                if (!hasIntent){
                    // Variáveis que obtem os dados
                    Double[] dn = new Double[4];
                    dn[0] = d_comp;
                    dn[1] = d_comp_i;
                    dn[2] = d_alfa;
                    dn[3] = null;

                    // String que armazena o dado convertido
                    String data = ConvertStringtoData.DataToString(dn);

                    // Cria um ContentValues
                    ContentValues cv = new ContentValues();
                    cv.put("titulo", getResources().getString(R.string.dilat_linear));
                    cv.put("data", data);

                    // Cria uma instância do banco de dados
                    HistoricoHelper hh = new HistoricoHelper(this);
                    hh.inserir(cv);
                }
            }
            else if (d_comp != null && d_comp_i != null && d_alfa != null && dv_tempo != null){
                TudoPreenchido td = new TudoPreenchido();
                td.show(getFragmentManager(), null);
            }
            else {
                EmptyFragment em = new EmptyFragment();
                em.show(getSupportFragmentManager(), null);
            }
        }
        else {
            // Define o valor de isDone para false
            isDone = false;

            // Redefine a visibilidade das linhas
            line.setVisibility(GONE);
            line2.setVisibility(GONE);
            line3.setVisibility(GONE);
            line4.setVisibility(GONE);

            // Faz que os itens de texto fiquem vazios
            v_comp.setText("");
            comp_i.setText("");
            alfa.setText("");
            v_tempo.setText("");

            // Redefine o estilo do botao calcular
            calcular.setText(R.string.Calc);
            calcular.setBackgroundColor(ContextCompat.getColor(this, R.color.AppThemeBlue));
        }
    }
}
