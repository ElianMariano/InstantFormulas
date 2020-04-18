package com.EducacaoApps.InstantFormulas;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.EducacaoApps.InstantFormulas.ItensLibrary.ExpandableListViewAdapter;
import com.EducacaoApps.InstantFormulas.formulas.R;

import com.EducacaoApps.InstantFormulas.ItensLibrary.CheckItem;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class form_choose extends AppCompatActivity {
    private ExpandableListView ExpandList;
    private ExpandableListViewAdapter expandableListViewAdapter;
    private List<String> listDataGroup;
    private ScrollView content;
    private HashMap<String, List<String>> listDataChild;
    private AdView adview;
    private boolean isAdRemoved;
    private boolean isAdShown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_choose);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        // Cria o botão voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        content = findViewById(R.id.content);

        // Incializa a ExpandList
        ExpandList = findViewById(R.id.ExpandList);

        // Inicializa os listeners
        initListeners();

        // Prepara os dados da lista
        initData();

        isAdShown = false;

        final SharedPreferences shared = getPreferences(Context.MODE_PRIVATE);
        isAdRemoved = shared.getBoolean("isAdRemoved", false);

        // Define um número aleátorio para perguntar se deseja remover os anúncios
        Random random = new Random();
        int num = random.nextInt(9);
        Log.e("FormChoose", String.format("Random: %d", num));

        if (!isAdRemoved && num == 1){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.remove_ads);
            builder.setNegativeButton(R.string.Nao, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    initializeAds();
                }
            });
            builder.setPositiveButton(R.string.Sim, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    // TODO Criar a lógica de compra da remoção de anúncios
                    SharedPreferences.Editor editor = shared.edit();
                    editor.putBoolean("isAdRemoved", true);
                    editor.apply();

                    isAdRemoved = true;
                }
            });

            AlertDialog alert = builder.create();
            alert.show();
        }

        if (!isAdRemoved && num != 1)
            initializeAds();
    }

    void initializeAds(){
        // Inicializa os anúncios
        MobileAds.initialize(getApplicationContext());

        // Carrega o anúncio do bottom
        adview = findViewById(R.id.adview);
        final AdRequest adRequest = new AdRequest.Builder().build();
        adview.loadAd(adRequest);

        adview.setAdListener(new AdListener(){
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();

                if (!isAdShown){
                    // Ajusta o layout para mostrar o anúncio
                    content.setLayoutParams(
                            new LinearLayout.LayoutParams(
                                    ViewGroup.LayoutParams.MATCH_PARENT,
                                    0, 0.9f));

                    adview.setLayoutParams(
                            new LinearLayout.LayoutParams(
                                    ViewGroup.LayoutParams.MATCH_PARENT,
                                    0, 0.1f));

                    // Define isAdShown como true
                    isAdShown = true;
                }
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);

                if (isAdShown){
                    // Redefine o layout
                    content.setLayoutParams(
                            new LinearLayout.LayoutParams(
                                    ViewGroup.LayoutParams.MATCH_PARENT,
                                    0, 1f));

                    // Redefine o adview
                    adview.setLayoutParams(
                            new LinearLayout.LayoutParams(
                                    ViewGroup.LayoutParams.WRAP_CONTENT,
                                    0, 0f));

                    isAdShown = false;
                }

                // Carrega um novo anúncio
                final AdRequest request= new AdRequest.Builder().build();
                adview.loadAd(request);
            }
        });
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

        ExpandList.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int i) {
                float density = getResources().getDisplayMetrics().density;
                int height = 0;

                for (int j = 0; j < listDataGroup.size();j++){
                    if (ExpandList.isGroupExpanded(j)){
                        try{
                            height += (listDataChild.get(listDataGroup.get(j)).size()*164)*((int) density);
                        }
                        catch(Exception e){
                            height += 0;
                        }
                    }
                }

                ViewGroup.LayoutParams params = ExpandList.getLayoutParams();
                params.height = height;
                ExpandList.setLayoutParams(params);
            }
        });

        ExpandList.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int i) {
                float density = getResources().getDisplayMetrics().density;
                int height = 0;

                for (int j = 0; j < listDataGroup.size();j++){
                    if (ExpandList.isGroupExpanded(j)){
                        try{
                            height += (listDataChild.get(listDataGroup.get(j)).size()*164)*((int) density);
                        }
                        catch(Exception e){
                            height += 0;
                        }
                    }
                }

                ViewGroup.LayoutParams params = ExpandList.getLayoutParams();
                if (height == 0)
                    params.height = (listDataGroup.size()*54)*((int) density);
                else
                    params.height = height;
                ExpandList.setLayoutParams(params);
            }
        });
    }

    private void calculateHeight(){
        // Calcula a altura do ExpandableListView
        float density = getResources().getDisplayMetrics().density;
        int height = (listDataGroup.size()*54)*((int) density);

        // Define o tamanho do ExpandableListView
        ViewGroup.LayoutParams params = ExpandList.getLayoutParams();
        params.height = height;
        ExpandList.setLayoutParams(params);
    }

    void initData(){
        // Inicializa o lista de grupo
        listDataGroup = new ArrayList<>();

        // Inicializa a lista de Child
        listDataChild = new HashMap<>();

        // Adiciona os dados do grupo
        listDataGroup.add(getString(R.string.mat));
        listDataGroup.add(getString(R.string.qui));
        listDataGroup.add(getString(R.string.Fis));

        /*
            Grupos sobre matemática
        */
        List<String> matematica = new ArrayList<>();
        matematica.add(ConvertStringtoData.StringToDataString(
                new String[]{getString(R.string.regra_de_tres), "",
                        getString(R.string.regra_de_tresDes) }));

        matematica.add(ConvertStringtoData.StringToDataString(
                new String[]{getString(R.string.area_q), getString(R.string.area_qFor),
                        getString(R.string.area_qDes)}));

        matematica.add(ConvertStringtoData.StringToDataString(
                new String[]{getString(R.string.area_t), getString(R.string.area_tFor),
                        getString(R.string.area_tDes)}));

        matematica.add(ConvertStringtoData.StringToDataString(
                new String[]{getString(R.string.comp_circulo), getString(R.string.comp_circuloFor),
                        getString(R.string.comp_circuloDes)}));

        matematica.add(ConvertStringtoData.StringToDataString(
                new String[]{getString(R.string.pitagoras), getString(R.string.pitagorasFor),
                        getString(R.string.pitagorasDes)}));

        /*
            Grupos sobre química
        */
        List<String> quimica = new ArrayList<>();
        quimica.add(ConvertStringtoData.StringToDataString(
                new String[]{getString(R.string.densidade), getString(R.string.densidadeFor),
                        getString(R.string.densidadeDes)}));

        quimica.add(ConvertStringtoData.StringToDataString(
                new String[]{getString(R.string.velocidade_media),
                        getString(R.string.velocidade_mediaFor),
                        getString(R.string.velo_mediaDes)}));

        quimica.add(ConvertStringtoData.StringToDataString(
                new String[]{getString(R.string.variacao_entalpia),
                        getString(R.string.variacao_entalpiaFor),
                        getString(R.string.variacao_entalpiaDes)}));

        quimica.add(ConvertStringtoData.StringToDataString(
                new String[]{getString(R.string.energia_ativacao),
                        getString(R.string.energia_ativacaoFor),
                        getString(R.string.energia_ativacaoDes)}));

        /*
            Grupos sobre física
        */
        List<String> fisica = new ArrayList<>();
        fisica.add(ConvertStringtoData.StringToDataString(
                new String[]{getString(R.string.dilat_linear), getString(R.string.dilat_linearFor),
                        getString(R.string.dilat_linearDes)}));

        fisica.add(ConvertStringtoData.StringToDataString(
                new String[]{getString(R.string.velo_media),
                        getString(R.string.velo_mediaFor),
                        getString(R.string.velo_mediaDes)}));

        fisica.add(ConvertStringtoData.StringToDataString(
                new String[]{getString(R.string.acel_media),
                        getString(R.string.acel_mediaFor),
                        getString(R.string.acel_mediaDes)}));

        fisica.add(ConvertStringtoData.StringToDataString(
                new String[]{getString(R.string.equa_torricelli),
                        getString(R.string.equa_torricelliFor),
                        getString(R.string.equa_torricelliDes)}));

        fisica.add(ConvertStringtoData.StringToDataString(
                new String[]{getString(R.string.equa_calorimetria),
                        getString(R.string.equa_calorimetriaFor),
                        getString(R.string.equa_calorimetriaDes)}));

        fisica.add(ConvertStringtoData.StringToDataString(
                new String[]{getString(R.string.clapeyron),
                        getString(R.string.clapeyronFor),
                        getString(R.string.clapeyronDes)}));

        // Adiciona aos grupos
        listDataChild.put(listDataGroup.get(0), matematica);
        listDataChild.put(listDataGroup.get(1), quimica);
        listDataChild.put(listDataGroup.get(2), fisica);

        // Calcula o tamanho do ExpandableListView
        calculateHeight();

        // initializing the adapter object
        expandableListViewAdapter = new ExpandableListViewAdapter(this, listDataGroup,
                listDataChild, this);

        // setting list adapter
        ExpandList.setAdapter(expandableListViewAdapter);
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