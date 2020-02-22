package com.EducacaoApps.InstantFormulas.ItensLibrary;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.ContentValues;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.EducacaoApps.InstantFormulas.Models.FavoritesHelper;
import com.EducacaoApps.InstantFormulas.Formulas;
import com.EducacaoApps.InstantFormulas.StartFormula;
import com.EducacaoApps.InstantFormulas.formulas.R;
import java.util.List;

import static androidx.core.content.res.ResourcesCompat.getDrawable;

//Procurar o erro nos metodos expand e desExpand
//Descobrir uma forma de mudar o tema do botão desta CheckBox
//Um item expandível que apresenta a formula e inicia a sua respectiva Activity
public class CheckItem extends LinearLayout implements View.OnClickListener{
    //O Botão que define os favoritos
    private Button Favorit;
    //O botão que expande a view
    private Button TitleEx;
    //O textView que armezena o nome da fórmula
    private TextView Formula;
    //O textview que define uma breve descrição da fórmula
    private TextView Description;
    //Variável que verifica se o item está expandido
    public boolean isExpanded, isFavorite;
    //Layout que armazena o botão calcular
    private LinearLayout bottom;
    //Botão que direciona para a Activity
    private Button Calcular;
    //Variável que define a duração da  animação de todas as views
    private final int DURATION = 300;
    // Define que activity sera iniciada
    private Formulas formulas;
    // Define a activity atual
    private AppCompatActivity activity;
    // Referências para os textos utilizados na activity
    private int tit, form, desc;

    // Construtor que inicializa todos os itns de uma vez
    public CheckItem(AppCompatActivity app, Formulas fm, int title, int formula,
                     int description){
        super(app);

        // Declara a variável activity e formulas
        activity = app;
        formulas = fm;

        //Define os dados do item
        tit = title;
        form = formula;
        desc = description;

        //Inicia a classe
        init();
    }

    // Construtor que inicializa o des uma vez, mas sem a formula
    public CheckItem(AppCompatActivity app, Formulas fm, int title, int description){
        super(app);

        // Declara a variável activity e formulas
        activity = app;
        formulas = fm;

        //Define os dados do item
        tit = title;
        desc = description;

        //Inicia a classe
        init();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void init(){
        //Layout inflater
        LayoutInflater inflater;
        //O layout que contem todos os itens
        LinearLayout Itens;

        //Define a orientação deste layout
        setOrientation(VERTICAL);
        //Define o padding no layout
        setPadding(2, 2, 2, 2);

        //Define o valor da variáveis isExpanded e isFavorite
        isExpanded = false;

        //Define o layout inflater
        inflater = LayoutInflater.from(getContext());
        //Define a classe itens
        Itens = (LinearLayout) inflater.inflate(R.layout.check_item, this, false);

        //Definição dos parametros do botão título
        Favorit = (Button) Itens.findViewById(R.id.Favorit);
        Favorit.setOnClickListener(this);

        //Definição so parametros do textview título
        TitleEx = (Button) Itens.findViewById(R.id.TitleEx);
        //OnClick separado porque o outro não é executado
        TitleEx.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isExpanded)
                {
                    expand();
                }
                else
                {
                    desExpand();
                }
            }
        });

        //Definição dos parametros da formula
        Formula = (TextView) Itens.findViewById(R.id.Formula);
        Formula.setAlpha(0.0f);

        //Definição dos parametros da descrição
        Description = (TextView) Itens.findViewById(R.id.Description);
        Description.setAlpha(0.0f);

        // Preenche os dados de acordo com os textos
        setTitleEx(getResources().getString(tit));

        try{
            setFormula(getResources().getString(form));
        }
        catch (Exception e){
            Log.d("CheckItem", e.getMessage());
        }

        setDescription(getResources().getString(desc));

        // Verifica no banco de dados se o item é favorito ou não, então o define de acordo com o
        // resultado

        // Instancia do banco de dados
        FavoritesHelper fh = new FavoritesHelper(getContext());

        // Lista definida de acordo com o resultados do banco de dados
        List<ContentValues> lista = fh.pesquisar(this.getTitleEx());

        if (!lista.isEmpty())
            setChecked(true);
        else
            setChecked(false);

        //Definição dos parametros do botão
        Calcular = (Button) Itens.findViewById(R.id.Calcular);
        Calcular.setOnClickListener(this);

        //Define o layout que armazena o botão calcular
        bottom = (LinearLayout) Itens.findViewById(R.id.bottom);
        bottom.setAlpha(0.0f);

        //Adiciona dentro do layout
        addView(Itens);
    }

    /*
    Estas funções são usadas para definir os textos e conteúdos de cada View
     */
    //Um método para definir o título da formula para cada instancia
    public void setFormula(CharSequence name){
        Formula.setText(name);
    }

    public void setFormula(int name){
        Formula.setText(getResources().getText(name));
    }

    public String getTitleEx(){
        return TitleEx.getText().toString();
    }

    //Um método para definir o título para cada instancia
    public void setTitleEx(CharSequence title){
        TitleEx.setText(title);
    }

    //Um método para definir a descrição para cada instancia
    public void setDescription(CharSequence description){
        Description.setText(description);
    }

    public void setDescription(int description){
        Description.setText(getResources().getText(description));
    }

    //Define o comprimento dos layouts
    public void setWidth(float width){
        this.setWidth(width);
    }

    //Define a altura
    public void setHeight(float height){
        this.setHeight(height);
    }

    //Define se o item está checado como sendo favorito
    public void setChecked(boolean Check){
        if (Check){
            isFavorite = true;

            try{
                Favorit.setBackground(getDrawable(getResources(), R.drawable.ic_star, null));
            }
            catch (NullPointerException n){
                Log.d("CheckItem", "isFavorite equal to true");
            }
        }
        else{
            isFavorite = false;

            try{
                Favorit.setBackground(getDrawable(getResources(), R.drawable.ic_star_border, null));
            }
            catch (NullPointerException n){
                Log.d("CheckItem", "isFavorite equal to false");
            }
        }
    }

    @Override
    public void onClick(View v){
        //Toma duas direções diferentes de acordo com a view pressionada
        if (v == Favorit){
            //Toast chamado quando o favorito é adicionado
            Toast FavoriteOn = Toast.makeText(getContext(), R.string.FavoriteToast_on,
                    Toast.LENGTH_SHORT);
            //Toast chamado quando o favorito é desligado
            Toast FavoriteOff = Toast.makeText(getContext(), R.string.FavoriteToast_off,
                    Toast.LENGTH_SHORT);

            //Verifica e faz as ações necessárias se o item for favorito
            if (!isFavorite){
                //Define o favorito como verdadeiro
                isFavorite = true;

                //Muda o desenho do botão
                try{
                    Favorit.setBackground(getDrawable(getResources(), R.drawable.ic_star, null));
                }
                catch (NullPointerException n){
                    Log.d("CheckItem", n.getMessage());
                }

                //Executa o toast
                FavoriteOn.show();

                // Cria um contentValue
                ContentValues cv = new ContentValues();
                cv.put("titulo", TitleEx.getText().toString());
                cv.put("isfavorite", 1);

                // Insere o item no banco de dados
                FavoritesHelper fh = new FavoritesHelper(getContext());
                fh.inserir(cv);
            }
            else{
                //Define o toast ccomo falso
                isFavorite = false;

                //Muda o desenho do botão
                try{
                    Favorit.setBackground(getDrawable(getResources(), R.drawable.ic_star_border, null));
                }
                catch (NullPointerException n){
                    Log.d("CheckItem", n.getMessage());
                }

                //Executa o toast
                FavoriteOff.show();

                // Deleta o item do banco de dados
                FavoritesHelper fh = new FavoritesHelper(getContext());
                fh.deletar(TitleEx.getText().toString());
            }
        }
        else if (v == Calcular){
            Do(formulas);
        }
    }

    //A animação deve afetar um item atras do outro
    //Método que expande os itens
    public void expand(){
        //Define o estado do item
        isExpanded = true;

        if (Formula.getText() != ""){
            Formula.animate().alpha(1)
                    .setDuration(DURATION)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            super.onAnimationStart(animation);

                            Formula.setVisibility(VISIBLE);
                        }
                    });
        }

        if (Description.getText() != ""){
            Description.animate().alpha(1)
                    .setDuration(DURATION)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            super.onAnimationStart(animation);
                            Description.setVisibility(VISIBLE);
                        }
                    });
        }

        bottom.animate().alpha(1).setDuration(DURATION)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);

                        bottom.setVisibility(VISIBLE);
                    }
                });
    }

    //Método que contrai os itens
    public void desExpand(){
        //Define o estado do item
        isExpanded = false;

        if (Formula.getText() != ""){
            Formula.animate().alpha(0)
                    .setDuration(DURATION)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            super.onAnimationStart(animation);

                            Formula.setVisibility(GONE);
                        }
                    });
        }

        if (Description.getText() != ""){
            Description.animate().alpha(0)
                    .setDuration(DURATION)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            super.onAnimationStart(animation);
                            Description.setVisibility(GONE);
                        }
                    });
        }

        bottom.animate().alpha(0).setDuration(DURATION)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);

                        bottom.setVisibility(GONE);
                    }
                });
    }

    // Função Do que é iniciada a activity de acordo com o parâmetro recebido no contrutor
    public void Do(Formulas fm){
        StartFormula st = new StartFormula(activity, fm);
        st.StartActivity();
    }
}
