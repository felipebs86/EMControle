package br.com.fbscorp.emcontrole.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.fbscorp.emcontrole.model.Medicamento;

public class MedicamentoDAO extends SQLiteOpenHelper{
    public MedicamentoDAO(Context context) {
        super(context, "emcontrole", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql1 = "create table medicamentos (id integer primary key, nome text not null, locais integer, frequencia integer);";
        String sql2 = "insert into medicamentos (nome, locais, frequencia) values ('Avonex', 4, 7)";
        String sql3 = "insert into medicamentos (nome, locais, frequencia) values ('Copaxone', 30, 1)";
        sqLiteDatabase.execSQL(sql1);
        sqLiteDatabase.execSQL(sql2);
        sqLiteDatabase.execSQL(sql3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "drop table medicamentos";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    public List<Medicamento> getMedicamentos() {
        String sql = "select * from medicamentos";
        SQLiteDatabase db = getReadableDatabase();
        List<Medicamento> medicamentos = new ArrayList<Medicamento>();
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()){
            Medicamento m = new Medicamento();
            m.setId(cursor.getInt(cursor.getColumnIndex("id")));
            m.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            m.setFrequencia(cursor.getInt(cursor.getColumnIndex("frequencia")));
            m.setLocais(cursor.getInt(cursor.getColumnIndex("locais")));
            medicamentos.add(m);
        }

        cursor.close();
        return medicamentos;

    }
}
