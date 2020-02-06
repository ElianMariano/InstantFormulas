package com.EducacaoApps.InstantFormulas.ItensLibrary;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.EducacaoApps.InstantFormulas.Models.FavoritesHelper;
import com.EducacaoApps.InstantFormulas.StartFormulaByString;
import com.example.company.formulas.R;

/*
 Este item sera usado na tela principal dentro da guia favoritos, ele irá inicializar com o botão
 favoritos ativado que, quando o botão for pressionado a visibilidade do item será definida como
 GONE e será marcado no banco de dados como se este item não existisse, estão quando o aplicativo
 for reiniciado ele irá adicionar os itens marcados no banco de dados
*/
public class FavoriteItem extends FrameLayout {
    private LayoutInflater layoutInflater;
    private LinearLayout layout_holder;
    private Button favorito;
    private TextView titulo;
    private AppCompatActivity activity;
    private float _xDelta;

    public FavoriteItem(AppCompatActivity act){
        super(act);

        activity = act;

        init();
    }

    @SuppressLint("ClickableViewAccessibility")
    public void init(){
        // Define o valor do layout inflater
        layoutInflater = LayoutInflater.from(getContext());

        // Define o valor de layout holder
        layout_holder = (LinearLayout) layoutInflater.inflate(R.layout.favorite_item, null,
                false);
        // Define o valor do botão favorito
        favorito = (Button) layout_holder.findViewById(R.id.favorito);
        // Define o valor do titulo
        titulo = (TextView) layout_holder.findViewById(R.id.titulo);

        // Define um listener para o botão favorito
        favorito.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                FavoritePressed();
            }
        });

        // Faz com que o item não seja clicavel para funcionar o onTouchListener
        titulo.setClickable(false);

        // Define um listener para o layoutholder
        layout_holder.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
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
                        if (view.getX() == 0.0f)
                            tituloPressed();

                        if (view.getX() > 350 || view.getX() < -350){
                            FavoritePressed();
                        }
                        else {
                            view.animate()
                                    .x(0)
                                    .setDuration(125)
                                    .alpha(1.0f)
                                    .start();;
                        }
                        break;
                }

                return true;
            }
        });

        // Adiciona o item para o layout principal
        addView(layout_holder);
    }

    // Função que abre a activity de acordo com o respectivo item
    public void tituloPressed(){
        // Variável que armazena o texto atual do item
        String atual = titulo.getText().toString();

        // Chama o método estático Start
        StartFormulaByString.Start(activity, atual);
    }

    // Função chamada quando o botão favorito for pressionado
    public void FavoritePressed(){
        // Remove o item do banco de dados a partir do titulo
        FavoritesHelper fh = new FavoritesHelper(getContext());
        fh.deletar(titulo.getText().toString());

        // Cria e mostra um toast
        Toast tst = Toast.makeText(getContext(), R.string.FavoriteToast_off, Toast.LENGTH_SHORT);
        tst.show();

        // Remove a view do layout principal
        ViewGroup layout_holder = (ViewGroup) this.getParent();
        layout_holder.removeView(this);
    }

    // Define texto do item
    public void setTitle(String text){
        titulo.setText(text);
    }

    // Obtem o texto do item
    public String getTitle(){
        return titulo.getText().toString();
    }
}
