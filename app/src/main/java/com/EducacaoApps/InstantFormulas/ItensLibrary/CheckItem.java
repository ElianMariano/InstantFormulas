package com.EducacaoApps.InstantFormulas.ItensLibrary;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import android.util.AttributeSet;
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
import com.EducacaoApps.InstantFormulas.StartFormulaByString;
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
    private TextView TitleEx;
    //O textView que armezena o nome da fórmula
    private TextView Formula;
    //O textview que define uma breve descrição da fórmula
    private TextView Description;
    //Variável que verifica se o item está expandido
    public boolean isExpanded, isFavorite;
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
    // Verifica se houve inicialização com programaticamente ou através do layout XML
    private boolean isXML;
    // Os textos utilizados no CheckItem [TitleEx, formula, description]
    private String title_ex, formula, description;

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

        // Define isXML como false
        isXML = false;

        //Inicia a classe
        init();
    }

    public CheckItem(AppCompatActivity app){
        super(app);

        // Declara a variável activity e formulas
        activity = app;

        //Define os dados do item
        title_ex = "";
        formula = "";
        description = "";

        // Define isXML como false
        isXML = false;

        //Inicia a classe
        init();
    }

    public CheckItem(Context context){
        super(context);

        // Define isXML como false
        isXML = false;

        title_ex = "";
        formula = "";
        description = "";

        //Inicia a classe
        init();
    }

    public CheckItem(AppCompatActivity app, int title, int formula,
                     int description){
        super(app);

        // Declara a variável activity e formulas
        activity = app;

        //Define os dados do item
        tit = title;
        form = formula;
        desc = description;

        // Define isXML como false
        isXML = false;

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

        // Define isXML como false
        isXML = false;

        //Inicia a classe
        init();
    }

    // Construtor que inicializa através dos atributos XML
    public CheckItem(Context context, AttributeSet attrs){
        super(context, attrs);

        // Define isXML como true
        isXML = true;

        // Define os atributos de textos do layout
        title_ex = attrs.getAttributeValue("http://www.EducacaoApps.com", "TitleEx");
        formula = attrs.getAttributeValue("http://www.EducacaoApps.com", "Formula");
        description = attrs.getAttributeValue("http://www.EducacaoApps.com", "Description");

        // Verifica se os textos obtidos vêm de uma string resource
        if (title_ex.contains("@"))
            title_ex = getResources().getString(Integer.parseInt(title_ex.substring(1)));

        if (formula.contains("@"))
            formula = getResources().getString(Integer.parseInt(formula.substring(1)));

        if (description.contains("@"))
            description = getResources().getString(Integer.parseInt(description.substring(1)));

        // Inicia a classe
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
        Favorit = Itens.findViewById(R.id.Favorit);
        Favorit.setOnClickListener(this);

        //Definição so parametros do textview título
        TitleEx = Itens.findViewById(R.id.TitleEx);

        //Definição dos parametros da formula
        Formula = Itens.findViewById(R.id.Formula);

        //Definição dos parametros da descrição
        Description = Itens.findViewById(R.id.Description);

        if (isXML){
            // Preenche os dados de acordo com os textos
            setTitleEx(getResources().getString(tit));

            try{
                setFormula(getResources().getString(form));
            }
            catch (Exception e){
                Log.d("CheckItem", e.getMessage());
            }

            setDescription(getResources().getString(desc));
        }
        else{
            setTitleEx(title_ex);

            if (formula.isEmpty())
                Formula.setVisibility(GONE);
            else
                setFormula(formula);

            setDescription(description);
        }

        //Definição dos parametros do botão
        Calcular = Itens.findViewById(R.id.Calcular);
        Calcular.setOnClickListener(this);

        //Adiciona dentro do layout
        addView(Itens);
    }

    /*
    Estas funções são usadas para definir os textos e conteúdos de cada View
     */
    //Um método para definir o título da formula para cada instancia
    public void setFormula(CharSequence name){
        if (!name.toString().isEmpty()){
            Formula.setText(name);
            Formula.setVisibility(VISIBLE);
        }
    }

    public void setFormula(int name){
        if (!getResources().getString(name).isEmpty()){
            Formula.setText(getResources().getText(name));
            Formula.setVisibility(VISIBLE);
        }
    }

    public String getTitleEx(){
        return TitleEx.getText().toString();
    }

    //Um método para definir o título para cada instancia
    public void setTitleEx(CharSequence title){
        if (!title.toString().isEmpty()){
            // Instancia do banco de dados
            FavoritesHelper fh = new FavoritesHelper(getContext());

            // Lista definida de acordo com o resultados do banco de dados
            List<ContentValues> lista = fh.pesquisar((String) title);

            if (!lista.isEmpty())
                setChecked(true);
            else
                setChecked(false);
        }

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

        Calcular.animate().alpha(1).setDuration(DURATION)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);

                        Calcular.setVisibility(VISIBLE);
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

        Calcular.animate().alpha(0).setDuration(DURATION)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);

                        Calcular.setVisibility(GONE);
                    }
                });
    }

    // Adiciona a activity que sera iniciada
    public void add_activity(AppCompatActivity act){
        activity = act;
    }

    // Função Do que é iniciada a activity de acordo com o parâmetro recebido no contrutor
    public void Do(Formulas fm){
        StartFormulaByString.Start(activity, getTitleEx());
    }
}
