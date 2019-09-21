package com.company.app;

import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TabHost;
import android.view.Menu;
import android.widget.TextView;

import com.company.app.ItensLibrary.FavoriteItem;
import com.company.app.ItensLibrary.HistoricoItem;
import com.example.company.formulas.R;

import com.company.app.ItensLibrary.BackAlertFragment;

import java.util.List;

import static android.view.Gravity.CENTER;
import static android.view.Gravity.TOP;
import static android.view.View.GONE;

//Criar uma classe que extenda a classe TabHost
// TODO Verificar a açao das activities de formula quando o botão voltar for pressionado
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Gerencia as guias da Activity
        TabHostMain = (TabHost) findViewById(R.id.TabHostMain);
        TabHostMain.setup();

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
        txt_R = (TextView) findViewById(R.id.txt_R);
        txt_F = (TextView) findViewById(R.id.txt_F);

        // Referencia os layouts que armazenam os itens
        r_cont = (LinearLayout) findViewById(R.id.r_cont);
        f_cont = (LinearLayout) findViewById(R.id.f_cont);

        // Referencia os spaces
        r_space = (Space) findViewById(R.id.r_space);
        f_space = (Space) findViewById(R.id.f_space);

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

        // Variável que armazena o index
        int i = 1;

        // Adiciona os itens no layout
        for (ContentValues cv: h_lista){
            // Obtem o titulo
            String titulo = cv.getAsString("titulo");
            // Obtem os dados
            String data = cv.getAsString("data");

            // Cria o item
            HistoricoItem historicoItem = new HistoricoItem(this, titulo, data, i);

            // Adiciona o item no layout
            r_cont.addView(historicoItem);

            // Incrementa um ao index
            i++;
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
        switch (item.getItemId()){
            case R.id.config:
                Intent in = new Intent(MainActivity.this, configuracoes.class);
                startActivity(in);
                finish();
                return true;
            case R.id.form:
                Intent in2 = new Intent(MainActivity.this, form_choose.class);
                startActivity(in2);
                finish();
                return true;
            default:
                super.onOptionsItemSelected(item);
        }
        return true;
    }
}
