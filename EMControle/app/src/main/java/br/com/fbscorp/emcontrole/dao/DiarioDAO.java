package br.com.fbscorp.emcontrole.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.fbscorp.emcontrole.model.Diario;
import br.com.fbscorp.emcontrole.model.Link;

public class DiarioDAO extends SQLiteOpenHelper{
    public DiarioDAO(Context context) {
        super(context, "emcontrole", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public List<Diario> getDiarios(){
        List<Diario> diarios = new ArrayList<Diario>();

        Log.d("EMControle", "Buscando diarios no banco");
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select * from diario;";

        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()){
            Diario diario = new Diario();
            diario.setId(cursor.getInt(cursor.getColumnIndex("id")));
            diario.setData(cursor.getString(cursor.getColumnIndex("data")));
            diario.setHora(cursor.getString(cursor.getColumnIndex("hora")));
            diario.setTexto(cursor.getString(cursor.getColumnIndex("texto")));
            diarios.add(diario);
        }
        cursor.close();

        return diarios;
    }

    public void insere(Diario diario){
        Log.d("EMControle", "Inserindo diario no banco");
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();

        dados.put("data", diario.getData());
        dados.put("hora", diario.getHora());
        dados.put("texto", diario.getTexto());

        db.insert("diario", null, dados);

        db.close();
    }
}
