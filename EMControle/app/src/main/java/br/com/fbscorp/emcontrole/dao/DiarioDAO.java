package br.com.fbscorp.emcontrole.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DiarioDAO extends SQLiteOpenHelper{
    public DiarioDAO(Context context) {
        super(context, "emcontrole", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table diario (id integer primary key, data text not null, relato text);";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "drop table diario";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }
}
