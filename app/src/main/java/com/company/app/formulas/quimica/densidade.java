package com.company.app.formulas.quimica;

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

import static android.view.View.GONE;

public class densidade extends AppCompatActivity {
    private EditText densidade;
    private EditText massa;
    private EditText volume;
    private Button calcular;
    private TextView line1;
    private TextView line2;
    private TextView line3;
    private TextView line4;
    // Variável que define o estado da formula
    private boolean isDone;
    private boolean hasIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_densidade);

        // Referencia os itens utilizados
        densidade = findViewById(R.id.densidade);
        massa = findViewById(R.id.massa);
        volume = findViewById(R.id.volume);
        calcular = findViewById(R.id.calcular);
        line1 = findViewById(R.id.line1);
        line2 = findViewById(R.id.line2);
        line3 = findViewById(R.id.line3);
        line4 = findViewById(R.id.line4);

        // Define o valor de isDone para false
        isDone = false;

        // Define hasIntent como false
        hasIntent = false;

        // Define um listener para o botão calcular
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                solve();
            }
        });
    }

    private void solve(){
        // Cria variáveis que armazenam os dados envidados pelo usuário
        Double d_densidade, d_massa, d_volume;

        // Prevem que ocorra possíveis erros
        try{
            d_densidade = Double.parseDouble(densidade.getText().toString());
        }
        catch (Exception e){
            d_densidade = null;
        }

        try {
            d_massa = Double.parseDouble(massa.getText().toString());
        }
        catch (Exception e){
            d_massa = null;
        }

        try{
            d_volume = Double.parseDouble(volume.getText().toString());
        }
        catch (Exception e){
            d_volume = null;
        }

        if (!isDone){
            if (d_densidade == null && d_massa != null && d_volume != null){
                // Define isDone como true
                isDone = true;

                // Define a visibilidade das linhas como visivel
                line1.setVisibility(View.VISIBLE);
                line2.setVisibility(View.VISIBLE);

                line1.setText("d = " + String.valueOf(d_massa) + "/" + String.valueOf(d_volume));
                line2.setText("d = " + String.format("%.2f", d_massa / d_volume));

                // Define o estilo do botão
                calcular.setBackgroundColor(ContextCompat.getColor(this, R.color.clearButton));
                calcular.setText(R.string.limpar);

                if (!hasIntent){
                    // Variáveis que obtem os dados
                    Double[] dn = new Double[3];
                    dn[0] = null;
                    dn[1] = d_massa;
                    dn[2] = d_volume;

                    // String que armazena o dado convertido
                    String data = ConvertStringtoData.DataToString(dn);

                    // Cria um ContentValues
                    ContentValues cv = new ContentValues();
                    cv.put("titulo", getResources().getString(R.string.densidade));
                    cv.put("data", data);

                    // Cria uma instância do banco de dados
                    HistoricoHelper hh = new HistoricoHelper(this);
                    hh.inserir(cv);
                }
            }
            else if (d_densidade != null && d_massa == null && d_volume != null){
                // Define isDone como true
                isDone = true;

                // Define a visibilidade das linhas como visivel
                line1.setVisibility(View.VISIBLE);
                line2.setVisibility(View.VISIBLE);
                line3.setVisibility(View.VISIBLE);

                line1.setText(String.valueOf(d_densidade) + " = m /" + String.valueOf(d_volume));
                line2.setText("m = " + String.valueOf(d_volume) + " * " + String.valueOf(d_densidade));
                line3.setText("m = " + String.format("%.2f", d_volume * d_densidade));

                // Define o estilo do botão
                calcular.setBackgroundColor(ContextCompat.getColor(this, R.color.clearButton));
                calcular.setText(R.string.limpar);

                if (!hasIntent){
                    // Variáveis que obtem os dados
                    Double[] dn = new Double[3];
                    dn[0] = d_densidade;
                    dn[1] = null;
                    dn[2] = d_volume;

                    // String que armazena o dado convertido
                    String data = ConvertStringtoData.DataToString(dn);

                    // Cria um ContentValues
                    ContentValues cv = new ContentValues();
                    cv.put("titulo", getResources().getString(R.string.densidade));
                    cv.put("data", data);

                    // Cria uma instância do banco de dados
                    HistoricoHelper hh = new HistoricoHelper(this);
                    hh.inserir(cv);
                }
            }
            else if (d_densidade != null && d_massa != null && d_volume == null){
                // Define isDone como true
                isDone = true;

                // Define a visibilidade das linhas como visivel
                line1.setVisibility(View.VISIBLE);
                line2.setVisibility(View.VISIBLE);
                line3.setVisibility(View.VISIBLE);
                line4.setVisibility(View.VISIBLE);

                line1.setText(String.valueOf(d_densidade) + " = " + String.valueOf(d_massa) + "/ v");
                line2.setText(String.valueOf(d_densidade) + " * v = " + String.valueOf(d_massa));
                line3.setText("v = " + String.valueOf(d_massa) + "/" + String.valueOf(d_densidade));
                line4.setText("v = " + String.format("%.2f", d_massa / d_densidade));

                // Define o estilo do botão
                calcular.setBackgroundColor(ContextCompat.getColor(this, R.color.clearButton));
                calcular.setText(R.string.limpar);

                if (!hasIntent){
                    // Variáveis que obtem os dados
                    Double[] dn = new Double[3];
                    dn[0] = d_densidade;
                    dn[1] = d_massa;
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
            else if (d_densidade != null && d_massa != null && d_volume != null){
                TudoPreenchido td = new TudoPreenchido();
                td.show(getFragmentManager(), null);
            }
            else {
                EmptyFragment em = new EmptyFragment();
                em.show(getSupportFragmentManager(), null);
            }
        }
        else{
            // Define isDone como false
            isDone = false;

            // Faz que os campos estejam vazios
            densidade.setText("");
            massa.setText("");
            volume.setText("");

            // Define as linhas como gone
            line1.setVisibility(GONE);
            line2.setVisibility(GONE);
            line3.setVisibility(GONE);
            line4.setVisibility(GONE);

            // Define o estilo padrão do botão calcular
            calcular.setBackgroundColor(ContextCompat.getColor(this, R.color.AppThemeBlue));
            calcular.setText(R.string.Calc);
        }
    }
}
