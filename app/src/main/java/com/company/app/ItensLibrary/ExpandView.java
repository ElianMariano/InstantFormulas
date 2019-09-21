package com.company.app.ItensLibrary;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.company.formulas.R;

/*
Esta classe ja esta pronta para receber os itens. Para adicionar o item devemos criá-lo na função
build, e depois definir sua visibilidade nas funções expand e desExpand.
 */

public class ExpandView extends LinearLayout implements View.OnClickListener{
    //Criação dos botões que recebem os eventos
    private Button mat, qui, fis;
    //Criação dos layouts que definem os grupos
    private LinearLayout FstLine, SndLine, ThrLine;
    //Criação dos itens
    private TextView RegraTres;
    //Variáveis para verificar se o grupo está expandido
    public boolean MIsExpanded, QIsExpanded, FIsExpanded = false;
    //Todos os itens contidos em uma só array do grupo mat
    private CheckItem checkMat;
    //Todos os itens contidos em uma só array do grupo qui
    private CheckItem checkQui;
    //Todos os itens contidos em uma só array do grupo fis
    private CheckItem checkFis;

    public ExpandView(Context context) {
        super(context);
        init();
    }

    public ExpandView(Context context, AttributeSet attrs){
        super(context, attrs);
        init();
    }

    //Função que inicia o direcionamento desta classe
    public void init(){
        //Configuração do layout principal
        this.setOrientation(VERTICAL);
        this.setMinimumWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //Define o padding no layout principal
        setPadding(2, 2, 2, 2);
        MarginLayoutParams margin = new MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        margin.setMargins(4, 4, 4,  4);
        setLayoutParams(new LayoutParams(margin));

        LayoutInflater li = LayoutInflater.from(getContext());

        //Configuração dos botões
        //Botão mat
        mat = (Button) li.inflate(R.layout.button_style, this, false);
        mat.setText(R.string.mat);
        mat.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //Botão qui
        qui = (Button) li.inflate(R.layout.button_style, this, false);
        qui.setText(R.string.qui);
        qui.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //Botão fis
        fis = (Button) li.inflate(R.layout.button_style, this, false);
        fis.setText(R.string.Fis);
        fis.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //Adiciona os listeners
        mat.setOnClickListener(this);
        qui.setOnClickListener(this);
        fis.setOnClickListener(this);

        //Adiciona alguns atributos aos layouts
        FstLine = new LinearLayout(getContext());
        SndLine = new LinearLayout(getContext());
        ThrLine = new LinearLayout(getContext());
        //Define o comprimento
        FstLine.setMinimumWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        SndLine.setMinimumWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        ThrLine.setMinimumWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //Define a orientação (Importante: Senão as views dentro ficam "bugadas")
        FstLine.setOrientation(VERTICAL);
        SndLine.setOrientation(VERTICAL);
        ThrLine.setOrientation(VERTICAL);

        //Adição dos layouts dentro do layout principal
        addView(FstLine);
        addView(SndLine);
        addView(ThrLine);

        //Adição dos botões dentro dos layouts
        FstLine.addView(mat);
        SndLine.addView(qui);
        ThrLine.addView(fis);

    }

    @Override
    public void onClick(View v){
        //Usado caso o usuário clique em um dos botões
        if (v == mat){
            //Mudar o estado do grupo
            if (MIsExpanded == false)
                MIsExpanded = true;
            else
                MIsExpanded = false;

            //Verificação dos estados e execução das instruções para a checkBox RegraTres
            if (MIsExpanded == false){
                desExpand("matematica");
            }
            else{
                expand("matematica");
            }
        }
        else if (v == qui){
            //Mudar o estado do grupo
            if (QIsExpanded == false)
                QIsExpanded = true;
            else
                QIsExpanded = false;

            //Verificação dos estados e execução das instruções para a checkBox RegraTres
            if (QIsExpanded == false){
                desExpand("quimica");
            }
            else{
                expand("quimica");
            }
        }
        else if (v == fis){
            //Mudar o estado do grupo
            if (FIsExpanded == false)
                FIsExpanded = true;
            else
                FIsExpanded = false;

            //Verificação dos estados e execução das instruções para a checkBox RegraTres
            if (FIsExpanded == false){
                desExpand("fisica");
            }
            else{
                expand("fisica");
            }
        }
    }

    //Adiciona um item no grupo matematica
    public void addMat(CheckItem check){
        check.setVisibility(GONE);
        FstLine.addView(check);
    }

    //Adiciona um item no grupo quimica
    public void addQui(CheckItem check){
        check.setVisibility(GONE);
        SndLine.addView(check);
    }

    //Adiciona um item no grupo fisica
    public void addFis(CheckItem check){
        check.setVisibility(GONE);
        ThrLine.addView(check);
    }

    //TODO Definir um método que adiciona views para cada grupo

    //Expande os itens
    public void expand(String group){
        //Variáveis que contam quantos itens existem dentro do layout
        int countMat = FstLine.getChildCount();
        int countQui = SndLine.getChildCount();
        int countFis = ThrLine.getChildCount();

        //Define que grupos derão expandidos
        if (group == "matematica" || group == "Matematica"){
            //Loop inicia em devido o primeiro item ser o botão que expande o grupo
            for (int i=1; i<countMat; i++){
                checkMat = (CheckItem) FstLine.getChildAt(i);

                checkMat.setVisibility(VISIBLE);
            }
        }
        else if (group == "quimica" || group == "Quimica"){
            //Loop inicia em devido o primeiro item ser o botão que expande o grupo
            for (int i=1; i<countQui; i++){
                checkQui = (CheckItem) SndLine.getChildAt(i);

                checkQui.setVisibility(VISIBLE);
            }
        }
        else if (group == "fisica" || group == "Fisica"){
            //Loop inicia em devido o primeiro item ser o botão que expande o grupo
            for (int i=1; i<countFis; i++){
                checkFis = (CheckItem) ThrLine.getChildAt(i);

                checkFis.setVisibility(VISIBLE);
            }
        }
    }

    //Contrai os itens
    public void desExpand(String group){
        //Variáveis que contam quantos itens existem dentro do layout
        int countMat = FstLine.getChildCount();
        int countQui = SndLine.getChildCount();
        int countFis = ThrLine.getChildCount();

        //Define que grupos serão desespandidos
        if (group == "matematica" || group == "Matematica"){
            //Loop inicia em devido o primeiro item ser o botão que expande o grupo
            for (int i=1; i<countMat; i++){
                checkMat = (CheckItem) FstLine.getChildAt(i);

                checkMat.setVisibility(GONE);
            }
        }
        else if (group == "quimica" || group == "Quimica"){
            //Loop inicia em devido o primeiro item ser o botão que expande o grupo
            for (int i=1; i<countQui; i++){
                checkQui = (CheckItem) SndLine.getChildAt(i);

                checkQui.setVisibility(GONE);
            }
        }
        else if (group == "fisica" || group == "Fisica"){
            //Loop inicia em devido o primeiro item ser o botão que expande o grupo
            for (int i=1; i<countFis; i++){
                checkFis = (CheckItem) ThrLine.getChildAt(i);

                checkFis.setVisibility(GONE);
            }
        }
    }
}
