package com.EducacaoApps.InstantFormulas;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.EducacaoApps.InstantFormulas.ItensLibrary.ExpandableListViewAdapter;
import com.EducacaoApps.InstantFormulas.formulas.R;

import com.EducacaoApps.InstantFormulas.ItensLibrary.CheckItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// TODO Aumentar o desenpenho desta actvity
public class form_choose extends AppCompatActivity {
    private ExpandableListView ExpandList;
    private ExpandableListViewAdapter expandableListViewAdapter;
    private List<String> listDataGroup;
    private HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_choose);

        // Cria o botão voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // Incializa a ExpandList
        ExpandList = findViewById(R.id.ExpandList);

        // Inicializa os listeners
        initListeners();

        // Prepara os dados da lista
        initData();
    }

    void initListeners(){
        ExpandList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView,
                                        View view, int i, long l) {
                if (expandableListView.isGroupExpanded(i))
                    expandableListView.expandGroup(i);
                else
                    expandableListView.collapseGroup(i);

                return false;
            }
        });

        ExpandList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view,
                                        int i, int i1, long l) {
                Toast.makeText(
                        getApplicationContext(),
                        listDataGroup.get(i)
                                + " : "
                                + listDataChild.get(
                                listDataGroup.get(i)).get(
                                i1), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });
    }

    private void calculateHeight(){
        // Calcula a altura do ExpandableListView
        float density = getResources().getDisplayMetrics().density;
        int height = (listDataGroup.size()*54)*((int) density);

        for (int i = 0; i < listDataGroup.size();i++){
            try{
                height += (listDataChild.get(listDataGroup.get(i)).size()*150)*((int) density);
            }
            catch(Exception e){
                height += 0;
            }
        }

        // Define o tamanho do ExpandableListView
        ViewGroup.LayoutParams params = ExpandList.getLayoutParams();
        params.height = height;
        ExpandList.setLayoutParams(params);
    }

    void initData(){
        // initializing the list of groups
        listDataGroup = new ArrayList<>();

        // initializing the list of child
        listDataChild = new HashMap<>();

        // Adiciona os dados do grupo
        listDataGroup.add(getString(R.string.mat));
        listDataGroup.add(getString(R.string.qui));
        listDataGroup.add(getString(R.string.Fis));

        List<String> matematica = new ArrayList<>();
        matematica.add(ConvertStringtoData.StringToDataString(
                new String[]{getString(R.string.regra_de_tres), "",
                        getString(R.string.regra_de_tresDes) }));

        listDataChild.put(listDataGroup.get(0), matematica);

        // Calcula o tamanho do ExpandableListView
        calculateHeight();

        // initializing the adapter object
        expandableListViewAdapter = new ExpandableListViewAdapter(this, listDataGroup,
                listDataChild);

        // setting list adapter
        ExpandList.setAdapter(expandableListViewAdapter);
    }

    //Não definir coprimento para os itens
    private void build(){
        //Adiciona os itens
        /*
        --------------------------------------
                    GRUPO MATEMÁTICA
        --------------------------------------
         */
        /*
        final CheckItem regra_tres = new CheckItem(this, Formulas.REGRA_TRES,
                R.string.regra_de_tres, R.string.regra_de_tresDes);
        ex.addMat(regra_tres);


        final CheckItem area_q = new CheckItem(this, Formulas.AREA_QUADRADO, R.string.area_q,
                R.string.area_qFor, R.string.area_qDes);
        ex.addMat(area_q);

        final CheckItem area_t = new CheckItem(this, Formulas.AREA_TRIANGULO, R.string.area_t,
                R.string.area_tFor, R.string.area_tDes);
        ex.addMat(area_t);

        final CheckItem comp_circulo = new CheckItem(this, Formulas.COMPRIMENTO_CIRCULO,
                R.string.comp_circulo, R.string.comp_circuloFor, R.string.comp_circuloDes);
        ex.addMat(comp_circulo);

        final CheckItem pitagoras = new CheckItem(this, Formulas.PITAGORAS, R.string.pitagoras,
                R.string.pitagorasFor, R.string.pitagorasDes);
        ex.addMat(pitagoras);
         */

        /*
        ------------------------------------------
                      GRUPO QUÍMICA
        ------------------------------------------
        */

        /*
        final CheckItem densidade = new CheckItem(this, Formulas.DENSIDADE, R.string.densidade,
                R.string.densidadeFor, R.string.densidadeDes);
        ex.addQui(densidade);

        final CheckItem velocidade_media = new CheckItem(this,
                Formulas.VELOCIADADE_MEDIA_REACAO, R.string.velocidade_media,
                R.string.velocidade_mediaFor, R.string.velocidade_mediaDes);
        ex.addQui(velocidade_media);

        final CheckItem variacao_entalpia = new CheckItem(this, Formulas.VARIACAO_ENTALPIA,
                R.string.variacao_entalpia, R.string.variacao_entalpiaFor,
                R.string.variacao_entalpiaDes);
        ex.addQui(variacao_entalpia);

        final CheckItem energia_ativacao = new CheckItem(this, Formulas.ENERGIA_ATIVACAO,
                R.string.energia_ativacao, R.string.energia_ativacaoFor,
                R.string.energia_ativacaoDes);
        ex.addQui(energia_ativacao);
         */

        /*
        ------------------------------------------
                      GRUPO FÍSICA
        ------------------------------------------
        */
        /*
        final CheckItem dilat_linear = new CheckItem(this, Formulas.DILATACAO_LINEAR,
                R.string.dilat_linear, R.string.dilat_linearFor, R.string.dilat_linearDes);
        ex.addFis(dilat_linear);

        final CheckItem velo_media = new CheckItem(this, Formulas.VELOCIADADE_MEDIA,
                R.string.velo_media, R.string.velo_mediaFor, R.string.velo_mediaDes);
        ex.addFis(velo_media);

        final CheckItem acel_media = new CheckItem(this, Formulas.ACELERACAO_MEDIA,
                R.string.acel_media, R.string.acel_mediaFor, R.string.acel_mediaDes);
        ex.addFis(acel_media);

        final CheckItem equa_torricelli = new CheckItem(this, Formulas.EQUACAO_TORRICELI,
                R.string.equa_torricelli, R.string.equa_torricelliFor, R.string.equa_torricelliDes);
        ex.addFis(equa_torricelli);

        final CheckItem equa_calorimetria = new CheckItem(this, Formulas.EQUACAO_CALORIMETRIA,
                R.string.equa_calorimetria, R.string.equa_calorimetriaFor,
                R.string.equa_calorimetriaDes);
        ex.addFis(equa_calorimetria);

        final CheckItem clapeyron = new CheckItem(this, Formulas.CLAPEYRON, R.string.clapeyron,
                 R.string.clapeyronFor, R.string.clapeyronDes);
        ex.addFis(clapeyron);
         */
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        startActivity(new Intent(form_choose.this, MainActivity.class));
        return true;
    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(form_choose.this, MainActivity.class));
    }
}