package com.company.app.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class HistoricoHelper extends SQLiteOpenHelper {
    private static final String NOME = "historico";
    private static final int VERSION = 1;
    private final String CREATE_TABLE = "CREATE TABLE " + NOME + "(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "titulo TEXT, data TEXT)";

    public HistoricoHelper(Context context){
        super(context, NOME, null, VERSION, null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS historico");
        onCreate(sqLiteDatabase);
    }

    // Insere um item no banco de dados
    public long inserir(ContentValues cv){
        SQLiteDatabase sql = this.getWritableDatabase();

        // Insere um item e obtêm o id do mesmo
        long id = sql.insert(NOME, null, cv);

        // Fecha o banco de dados
        sql.close();

        return id;
    }

    // Deleta um item do banco de dados
    public long deletar(Long id_item){
        // Obtem o banco de dados
        SQLiteDatabase sql = this.getWritableDatabase();

        // Deleta o item
        long id = sql.delete(NOME, "id LIKE ?", new String[]{"%" +
                String.valueOf(id_item) + "%"});

        // Fecha o banco de dados
        sql.close();

        return id;
    }

    // Pesquisa todos os itens
    public List<ContentValues> pesquisarTodos(){
        // Instrução sql para obter todos os itens
        final String INS_SQL = "SELECT * FROM historico ORDER BY id";

        // Cria uma lista com as ocorrencias
        List<ContentValues> lista = new ArrayList<>();

        // Cria uma instância do banco de dados e uma cursor
        SQLiteDatabase sql = this.getReadableDatabase();
        Cursor c = sql.rawQuery(INS_SQL, null);

        if(c.moveToFirst()){
            do{
                // Cria um content values
                ContentValues cv = new ContentValues();
                // Adiciona o id
                cv.put("id", c.getInt(c.getColumnIndex("id")));
                // Adiciona o título
                cv.put("titulo", c.getString(c.getColumnIndex("titulo")));
                // Adiciona os dados
                cv.put("data", c.getString(c.getColumnIndex("data")));

                // Adiciona o ContentValues para a lista
                lista.add(cv);
            }
            while(c.moveToNext());
        }

        return lista;
    }
}
