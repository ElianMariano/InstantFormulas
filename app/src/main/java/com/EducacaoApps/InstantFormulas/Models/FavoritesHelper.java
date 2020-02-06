package com.EducacaoApps.InstantFormulas.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/*
Este banco de dados será atualizado pela classe CheckItem quando o botão favorito for clicado e
removido pelo mesmo. Depois será inserido o item correspondente na tela principal, sendo que este
item será parecido com o item histórico.
 */

// O campo isfavorite se for marcado como 1, será equivalente à true e 0 equivalente à false
public class FavoritesHelper extends SQLiteOpenHelper {

    private static final String NAME = "favoritos";
    private static final int VERSION = 1;
    private final String CREATE_TABLE =
            "CREATE TABLE favoritos("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "titulo TEXT, isfavorite INTEGER)";

    public FavoritesHelper(Context context){
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS favoritos");
        onCreate(sqLiteDatabase);
    }

    // Insere os dados dentro do banco de dados
    public long inserir(ContentValues cv){
        SQLiteDatabase db = this.getWritableDatabase();
        long id = db.insert(NAME, null, cv);

        // Fecha o banco de dados
        db.close();
        return id;
    }

    // Pesquisa os itens
    public List<ContentValues> pesquisar(String titulo){
        // Cria uma instrução sql
        String sql = "SELECT * FROM favoritos WHERE titulo LIKE ?";
        // Cria uma string where
        String where[] = new String[]{"%"+titulo+"%"};

        // Cria uma lista que será retornada
        List<ContentValues> lista = new ArrayList<>();

        // Cria uma base de dados e um cursor
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(sql, where);

        if(c.moveToFirst()){
            do{
                // Cria um contentValue
                ContentValues cv = new ContentValues();
                // Adiciona o id
                cv.put("id", c.getInt(c.getColumnIndex("id")));
                // Adiciona o titulo
                cv.put("titulo", c.getString(c.getColumnIndex("titulo")));
                cv.put("isFavorite", c.getInt(c.getColumnIndex("isfavorite")));

                //Adiciona os itens para a lista
                lista.add(cv);
            }
            while(c.moveToNext());
        }
        // Fecha o banco de dados
        db.close();

        return lista;
    }

    // Pesquisa todos os itens
    public List<ContentValues> pequisarTodos(){
        // Cria uma instrução sql
        String sql = "SELECT * FROM favoritos ORDER BY id";

        // Cria uma lista
        List<ContentValues> lista = new ArrayList<>();

        // Cria uma base de dados e um cursor
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        if(c.moveToFirst()){
            do{
                // Cria um contentValue
                ContentValues cv = new ContentValues();
                // Adiciona o id
                cv.put("id", c.getInt(c.getColumnIndex("id")));
                // Adiciona o titulo
                cv.put("titulo", c.getString(c.getColumnIndex("titulo")));
                cv.put("isFavorite", c.getInt(c.getColumnIndex("isfavorite")));

                //Adiciona os itens para a lista
                lista.add(cv);
            }
            while(c.moveToNext());
        }
        // Fecha o banco de dados
        db.close();

        return lista;
    }

    // Deleta o dado da base de dados atraves do titulo
    public long deletar(String titulo){
        SQLiteDatabase db = this.getWritableDatabase();
        //long id = db.delete(NAME, "titulo="+titulo, null);
        long id = db.delete(NAME, "titulo LIKE ?", new String[]{"%"+titulo+"%"});

        // Fecha o banco de dados
        db.close();

        return id;
    }
}
