package br.com.fbscorp.emcontrole.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.fbscorp.emcontrole.model.Diario;
import br.com.fbscorp.emcontrole.model.Link;

public class LinksDAO extends SQLiteOpenHelper {
    public LinksDAO(Context context) {
        super(context, "emcontrole", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public List<Link> getLinks(){
        List<Link> links = new ArrayList<Link>();

        Log.d("EMControle", "Buscando links no banco");
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select * from links;";

        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()){
            Link link = new Link();
            link.setId(cursor.getInt(cursor.getColumnIndex("id")));
            link.setTitulo(cursor.getString(cursor.getColumnIndex("titulo")));
            link.setUrl(cursor.getString(cursor.getColumnIndex("url")));
            links.add(link);
        }
        cursor.close();

        return links;
    }

    public void exclui(Diario diario) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {String.valueOf(diario.getId())};
        db.delete("diario", "id = ?", params);
    }
}
