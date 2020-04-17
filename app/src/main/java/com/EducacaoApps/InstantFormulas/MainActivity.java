package com.EducacaoApps.InstantFormulas;

import android.content.ContentValues;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;

import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TabHost;
import android.view.Menu;
import android.widget.TextView;
import com.EducacaoApps.InstantFormulas.ItensLibrary.FavoriteItem;
import com.EducacaoApps.InstantFormulas.ItensLibrary.HistoricoItem;
import com.EducacaoApps.InstantFormulas.Models.FavoritesHelper;
import com.EducacaoApps.InstantFormulas.Models.HistoricoHelper;
import com.EducacaoApps.InstantFormulas.formulas.R;
import com.EducacaoApps.InstantFormulas.ItensLibrary.BackAlertFragment;
import java.util.List;
import static android.view.View.GONE;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

//Criar uma classe que extenda a classe TabHost
public class MainActivity extends AppCompatActivity{
    private TabHost TabHostMain;
    // Referencia dos TextView
    // TextView da guia recentes txt_R
    private TextView txt_R;
    // TextView da guia favoritos txt_F
    private TextView txt_F;
    // LinarLayouts utilizados para armazenar os itens historico e favoritos
    private LinearLayout r_cont;
    private LinearLayout f_cont;
    // Spaces utilizados para organizar os TextViews
    private Space r_space;
    private Space f_space;
    // AdView da activity
    private AdView adview;
    // Layout de conteudo
    private LinearLayout content;
    // Define se o anúncio aparece
    private boolean isAdShown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        // Referencia o layout content
        content = findViewById(R.id.content);

        //Gerencia as guias da Activity
        TabHostMain = findViewById(R.id.TabHostMain);
        TabHostMain.setup();

        // Define o isAdShown como true
        isAdShown = true;

        //Criando a guia recentes
        TabHost.TabSpec spec = TabHostMain.newTabSpec("Recentes");
        //Define o texto da guia
        spec.setContent(R.id.Recentes);
        //Define o indicador
        spec.setIndicator(getResources().getString(R.string.GuiaUM));
        //Adiciona a guis ao TabHost
        TabHostMain.addTab(spec);

        //Cria a gui favoritos
        spec = TabHostMain.newTabSpec("Favoritos");
        //Define o conteúdo
        spec.setContent(R.id.Favoritos);
        //Define o indicador
        spec.setIndicator(getResources().getString(R.string.GuiaDois));
        //Adiciona a guis ao TabHost
        TabHostMain.addTab(spec);

        // Referencia dos TextViews
        txt_R = findViewById(R.id.txt_R);
        txt_F = findViewById(R.id.txt_F);

        // Referencia os layouts que armazenam os itens
        r_cont = findViewById(R.id.r_cont);
        f_cont = findViewById(R.id.f_cont);

        // Referencia os spaces
        r_space = findViewById(R.id.r_space);
        f_space = findViewById(R.id.f_space);

        /*
         Cria os itens de recentes
        */

        HistoricoHelper hh = new HistoricoHelper(this);

        // Cria uma lista para os resultados
        List<ContentValues> h_lista = hh.pesquisarTodos();

        if (!h_lista.isEmpty()){
            // Define as view padrões como GONE
            txt_R.setVisibility(GONE);
            r_space.setVisibility(GONE);
        }

        // Adiciona os itens no layout
        for (ContentValues cv: h_lista){
            // Obtem o titulo
            String titulo = cv.getAsString("titulo");
            // Obtem os dados
            String data = cv.getAsString("data");

            // Cria o item
            HistoricoItem historicoItem = new HistoricoItem(this, titulo, data,
                    cv.getAsLong("id"));

            // Adiciona o item no layout
            r_cont.addView(historicoItem);
        }

        // Verifica cada mudança ocorrida no layout
        r_cont.setOnHierarchyChangeListener(new ViewGroup.OnHierarchyChangeListener() {
            @Override
            public void onChildViewAdded(View view, View view1) {
                // Faz nada
            }

            @Override
            public void onChildViewRemoved(View view, View view1) {
                if (r_cont.getChildCount() == 2){
                    // Redefine a visibilidade dos itens
                    txt_R.setVisibility(View.VISIBLE);
                    r_space.setVisibility(View.VISIBLE);
                }
            }
        });

        /*
         Cria os itens de favorito na tela principal
        */
        // Cria item da base de dados dos favoritos
        FavoritesHelper fh = new FavoritesHelper(this);

        // Cria a variável lista que armazena o item
        List<ContentValues> lista = fh.pequisarTodos();

        if (!lista.isEmpty()){
            // Define a gravidade do layout e o texto como gone
            txt_F.setVisibility(GONE);
            // Define a visibilidade do space como GONE
            f_space.setVisibility(GONE);
        }

        // Adiciona o item que está armazenado no banco de dados
        for(ContentValues cv: lista){
            // Cria um item favorito
            FavoriteItem fi = new FavoriteItem(this);
            // Define o titulo do item
            fi.setTitle(cv.getAsString("titulo"));

            // Adiciona o item no layout
            f_cont.addView(fi);
        }

        // Verifica a quantidade de itens no layout, e então restitui o modo padrão de visualização
        f_cont.setOnHierarchyChangeListener(new ViewGroup.OnHierarchyChangeListener() {
            @Override
            public void onChildViewAdded(View view, View view1) {
            }

            @Override
            public void onChildViewRemoved(View view, View view1) {
                if (f_cont.getChildCount() == 2){
                    // Define a visibilidade dos itens padrões
                    txt_F.setVisibility(View.VISIBLE);
                    f_space.setVisibility(View.VISIBLE);
                }
            }
        });

        // Inicializa os anúncios
        MobileAds.initialize(this);

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

    /*
    Faz com que se o botão voltar for pressionado, o usuário escolha se quer permanecer ou continuar
    usando o aplicativo
    */
    @Override
    public void onBackPressed(){
        //Cria um AlertDialog personalizado
        DialogFragment alrt = new BackAlertFragment();
        alrt.show(getSupportFragmentManager(), "");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbarmain, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == R.id.form){
            Intent in = new Intent(MainActivity.this, form_choose.class);
            startActivity(in);
            finish();
        }
        else{
            super.onOptionsItemSelected(item);
        }
        return true;
    }
}
