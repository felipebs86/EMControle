package br.com.fbscorp.emcontrole.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.fbscorp.emcontrole.model.Cadastro;

public class CadastroDAO extends SQLiteOpenHelper{

    public CadastroDAO(Context context) {
        super(context, "emcontrole", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table cadastro (id integer primary key, nome text, email text, medicamento integer, data text, horario text, lembrete text, local integer);";
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

        dados.put("id", 1);
        dados.put("nome", cadastro.getNome());
        dados.put("email", cadastro.getEmail());
        dados.put("medicamento", cadastro.getMedicamento());
        dados.put("data", cadastro.getData());
        dados.put("horario", cadastro.getHora());
        dados.put("lembrete", cadastro.isLembrete());
        dados.put("local", cadastro.getIdLocal());

        db.insert("cadastro", null, dados);
    }

    public void atualiza(Cadastro cadastro) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();

        dados.put("nome", cadastro.getNome());
        dados.put("email", cadastro.getEmail());
        dados.put("medicamento", cadastro.getMedicamento());
        dados.put("data", cadastro.getData());
        dados.put("horario", cadastro.getHora());
        dados.put("lembrete", cadastro.isLembrete());
        dados.put("local", cadastro.getIdLocal());

        db.update("cadastro", dados, "id = 1", null);
    }

    public Boolean existeCadastro(){
        String sql = "select count(*) from cadastro where id=1";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);
        if (c != null) {
            c.moveToFirst();
            if (c.getInt(0) == 0) {
                return false;
            }
        }
        return true;
    }

    public Cadastro buscaCadastro(){
        Cadastro cad = new Cadastro();
        String sql = "select * from cadastro where id=1";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);
        if (c.getCount() != 0) {
            while (c.moveToNext()) {
                cad.setNome(c.getString(c.getColumnIndex("nome")));
                cad.setEmail(c.getString(c.getColumnIndex("email")));
                cad.setMedicamento(c.getInt(c.getColumnIndex("medicamento")));
                cad.setData(c.getString(c.getColumnIndex("data")));
                cad.setHora(c.getString(c.getColumnIndex("hora")));
                cad.setLembrete(c.getString(c.getColumnIndex("lembrete")));
                cad.setIdLocal(c.getInt(c.getColumnIndex("local")));
            }
            return cad;
        }
        return null;
    }
}
