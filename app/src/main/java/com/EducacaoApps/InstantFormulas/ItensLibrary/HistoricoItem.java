package com.EducacaoApps.InstantFormulas.ItensLibrary;

import android.annotation.SuppressLint;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.EducacaoApps.InstantFormulas.Models.HistoricoHelper;
import com.EducacaoApps.InstantFormulas.StartFormulaByString;
import com.EducacaoApps.InstantFormulas.formulas.R;

/*
Este item será utilizado sempre que um novo histórico for adicionado
*/
public class HistoricoItem extends FrameLayout {
    // Itens usados dentro do layout
    private Button historico_b;
    private LayoutInflater inflater;
    private LinearLayout itens;
    private AppCompatActivity activity;
    // Armazena o id para deletar o item
    private long id;
    // Texto do item
    private String text;
    // Localização da view
    private float _xDelta;
    // Dados do item
    private String data;

    public HistoricoItem(AppCompatActivity app, String txt, String dt, long i){
        super(app);

        // Armazena os dados em variáveis globais
        text = txt;
        activity = app;
        data = dt;
        id = i;

        init();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void init(){

        // Faz referência aos itens do layout
        // Obtêm o layout inflater
        inflater = LayoutInflater.from(getContext());
        // Obtêm o layout principal
        itens = (LinearLayout) inflater.inflate(R.layout.historico_item, this,
                false);

        // Obtêm o botão do layout
        historico_b = itens.findViewById(R.id.historico_b);
        // Define o texto do botão
        historico_b.setText(text);
        // Define o botão como não clicável
        historico_b.setClickable(false);

        // Adicona um onTouch listener para o botão
        itens.setOnTouchListener(new OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent){
                // Verifica a ação e arrasta a view
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        _xDelta = view.getX() - motionEvent.getRawX();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        view.animate()
                                .x(motionEvent.getRawX() + _xDelta)
                                .setDuration(0)
                                .start();

                        if (view.getX() > 0.0){
                            if (view.getX() < 10){
                                view.animate()
                                        .alpha((float) (view.getX() / view.getX()))
                                        .start();
                            }
                            else if (view.getX() < 100){
                                view.animate()
                                        .alpha((float) ((view.getX() * 0.8) / view.getX()))
                                        .start();
                            }
                            else if (view.getX() < 150){
                                view.animate()
                                        .alpha((float) (view.getX() * 0.7) / view.getX())
                                        .start();
                            }
                            else if (view.getX() < 200){
                                view.animate()
                                        .alpha((float) (view.getX() * 0.6) / view.getX())
                                        .start();
                            }
                            else if (view.getX() < 300){
                                view.animate()
                                        .alpha((float) (view.getX() * 0.4) / view.getX())
                                        .start();
                            }
                            else if (view.getX() < 400){
                                view.animate()
                                        .alpha((float) (view.getX() * 0.2) / view.getX())
                                        .start();
                            }
                            else if (view.getX() < 500){
                                view.animate()
                                        .alpha((float) (view.getX() * 0.1) / view.getX())
                                        .start();
                            }
                        }
                        else if (view.getX() < 0.0){
                            if (view.getX() > -10){
                                view.animate()
                                        .alpha((float) ((view.getX()) /
                                                (view.getX())))
                                        .start();
                            }
                            else if (view.getX() > -100){
                                view.animate()
                                        .alpha((float) (view.getX() * 0.8) / view.getX())
                                        .start();
                            }
                            else if (view.getX() > -150){
                                view.animate()
                                        .alpha((float) (view.getX() * 0.7) / view.getX())
                                        .start();
                            }
                            else if (view.getX() > -200){
                                view.animate()
                                        .alpha((float) (view.getX() * 0.6) / view.getX())
                                        .start();
                            }
                            else if (view.getX() > -300){
                                view.animate()
                                        .alpha((float) (view.getX() * 0.4) / view.getX())
                                        .start();
                            }
                            else if (view.getX() > -400){
                                view.animate()
                                        .alpha((float) (view.getX() * 0.2) / view.getX())
                                        .start();
                            }
                            else if (view.getX() > -500){
                                view.animate()
                                        .alpha((float) (view.getX() * 0.1) / view.getX())
                                        .start();
                            }
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        if (view.getX() > 350 || view.getX() < -350){
                            item_delete();
                        }
                        else if (view.getX() == 0.0f){
                            init_activity();
                        }
                        else {
                            view.animate()
                                    .x(5)
                                    .setDuration(125)
                                    .alpha(1.0f)
                                    .start();
                        }
                        break;
                }
                return true;
            }
        });

        // Adiciona a view principal ao layout
        addView(itens);
    }

    // Apaga o item do layou
    private void item_delete(){
        // Remove esta view do layout
        ViewGroup group =  (ViewGroup) this.getParent();
        group.removeView(this);

        // Remove este item no banco de dados
        HistoricoHelper hh = new HistoricoHelper(getContext());
        hh.deletar(id);

        // Executa um toast
        Toast tst = Toast.makeText(getContext(), R.string.Recente_off, Toast.LENGTH_SHORT);
        tst.show();
    }

    // Inicia a activity correspondente ao item
    private void init_activity(){
        // Inicia a activity com os parâmetros
        StartFormulaByString.Start(activity, text, data);
    }
}
