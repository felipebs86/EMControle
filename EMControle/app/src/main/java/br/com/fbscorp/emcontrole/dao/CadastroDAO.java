package br.com.fbscorp.emcontrole.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import br.com.fbscorp.emcontrole.model.Cadastro;

public class CadastroDAO extends SQLiteOpenHelper{

    public CadastroDAO(Context context) {
        super(context, "emcontrole", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("EMControle", "Criando tabela de cadastro");
        String sql = "create table cadastro (id integer primary key, nome text not null, email text, medicamento integer, data text, horario text, lembrete text, local integer);";
        Log.d("EMControle", "Criando tabela de medicamentos");
        String sql1 = "create table medicamentos (id integer primary key, nome text not null, locais integer, frequencia integer);";
        String sql2 = "insert into medicamentos (nome, locais, frequencia) values ('Avonex', 4, 7)";
        String sql3 = "insert into medicamentos (nome, locais, frequencia) values ('Copaxone', 30, 1)";
        Log.d("EMControle", "Inserindo medicamentos no banco");
        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.execSQL(sql1);
        sqLiteDatabase.execSQL(sql2);
        sqLiteDatabase.execSQL(sql3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "drop table if exists cadastro";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    public void insere(Cadastro cadastro) {
        Log.d("EMControle", "Inserindo cadastro no banco");
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
        db.close();
        String sql = "select * from cadastro where id=1";
        Cursor c = db.rawQuery(sql, null);
        Log.d("EMControle", "Cursor salvo: " + String.valueOf(c.getCount()));

    }

    public void atualiza(Cadastro cadastro) {
        Log.d("EMControle", "Atualizando cadastro no banco");
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
        db.close();
    }

    public Boolean existeCadastro(){
        Log.d("EMControle", "Verificando se existe cadastro salvo");
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select count(*) from cadastro where id=1";
        Cursor c = db.rawQuery(sql, null);
        Log.d("EMControle", c.toString());
        if (c.getCount() == 0) {
            c.moveToFirst();
            if (c.getInt(0) == 0) {
                return false;
            }
        }
        return true;
    }

    public Cadastro buscaCadastro(){
        Log.d("EMControle", "Buscando cadastro no banco");
        SQLiteDatabase db = getWritableDatabase();
        Cadastro cad = new Cadastro();
        String sql = "select * from cadastro where id=1";
        Cursor c = db.rawQuery(sql, null);
        Log.d("EMControle", "Cursor: " + String.valueOf(c.getCount()));
        if (c.getCount() != 0) {
            while (c.moveToNext()) {
                cad.setNome(c.getString(c.getColumnIndex("nome")));
                Log.d("EMControle", cad.getNome());
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
