package br.com.fbscorp.emcontrole.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.fbscorp.emcontrole.model.Cadastro;

public class CadastroDAO extends SQLiteOpenHelper{

    public CadastroDAO(Context context) {
        super(context, "emcontrole", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table cadastro (id integer primary key, nome text not null, email text, medicamento integer, data text, horario text, lembrete boolean, local integer);";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "drop table if exists cadastro";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    public void insere(Cadastro cadastro) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();
        dados.put("nome", cadastro.getNome());
        dados.put("email", cadastro.getEmail());
        dados.put("medicamento", cadastro.getMedicamento());
        dados.put("data", cadastro.getData());
        dados.put("horario", cadastro.getHora());
        dados.put("lembrete", cadastro.isLembrete());
        dados.put("local", cadastro.getIdLocal());

        db.insert("cadastro", null, dados);
    }
}
