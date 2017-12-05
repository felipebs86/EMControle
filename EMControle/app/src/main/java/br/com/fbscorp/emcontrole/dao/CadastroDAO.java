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
        Log.d("EMControle", "Inserindo medicamentos no banco");
        String sql2 = "insert into medicamentos (nome, locais, frequencia) values ('Avonex', 4, 7)";
        String sql3 = "insert into medicamentos (nome, locais, frequencia) values ('Copaxone', 30, 1)";
        Log.d("EMControle", "Criando tabela diario");
        String sql4 = "create table diario (id integer primary key, data text not null, hora text, texto text);";
        Log.d("EMControle", "Criando tabela links");
        String sql5 = "create table links (id integer primary key, titulo text not null, url text);";
        Log.d("EMControle", "Inserindo links no banco");
        String sql6 = "insert into links (titulo, url) values ('Amigos Multiplos (AME)', 'http://www.amigosmultiplos.org.br/')";
        String sql7 = "insert into links (titulo, url) values ('Esclerose Mulipla e Eu', 'http://esclerosemultiplaeeu.blogspot.com.br/')";
        String sql8 = "insert into links (titulo, url) values ('APEMERJ', 'http://apemerj.org.br/site/')";
        String sql9 = "insert into links (titulo, url) values ('AGAPEM', 'http://www.agapem.org.br/portal/')";
        String sql10 = "insert into links (titulo, url) values ('Esclerose Multipla', 'http://esclerosemultipla.com.br')";
        String sql11 = "insert into links (titulo, url) values ('Esclarecimento Multiplo', 'https://www.esclarecimentomultiplo.com.br')";
        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.execSQL(sql1);
        sqLiteDatabase.execSQL(sql2);
        sqLiteDatabase.execSQL(sql3);
        sqLiteDatabase.execSQL(sql4);
        sqLiteDatabase.execSQL(sql5);
        sqLiteDatabase.execSQL(sql6);
        sqLiteDatabase.execSQL(sql7);
        sqLiteDatabase.execSQL(sql8);
        sqLiteDatabase.execSQL(sql9);
        sqLiteDatabase.execSQL(sql10);
        sqLiteDatabase.execSQL(sql11);
        String sqlteste = "insert into diario (data, hora, texto) values ('01/01', '12:00', 'TEXTO EXEMPLO TEXTO EXEMPLO TEXTO EXEMPLO TEXTO EXEMPLO TEXTO EXEMPLO TEXTO EXEMPLO TEXTO EXEMPLO TEXTO EXEMPLO TEXTO EXEMPLO TEXTO EXEMPLO TEXTO EXEMPLO TEXTO EXEMPLO TEXTO EXEMPLO TEXTO EXEMPLO TEXTO EXEMPLO TEXTO EXEMPLO TEXTO EXEMPLO TEXTO EXEMPLO TEXTO EXEMPLO TEXTO EXEMPLO ')";
        sqLiteDatabase.execSQL(sqlteste);

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

        String sql = "select * from cadastro where id=1";
        Cursor c = db.rawQuery(sql, null);
        Log.d("EMControle", "Cursor salvo: " + String.valueOf(c.getCount()));
        db.close();
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
        String sql = "select * from cadastro where id=1";
        Cursor c = db.rawQuery(sql, null);
        Log.d("EMControle - " + this.getClass(), String .valueOf(c.getCount()));
        if (c.getCount() != 0) {
            while (c.moveToNext()) {
                Log.d("EMControle", String.valueOf(c.getColumnIndex("id")));
                Log.d("EMControle", String.valueOf(c.getColumnIndex("nome")));
            }
        }
        if (c.getCount() == 0) {
            return false;
        }
        return true;
    }

    public Cadastro buscaCadastro(){
        Log.d("EMControle", "Buscando cadastro no banco");
        SQLiteDatabase db = getWritableDatabase();
        Cadastro cad = new Cadastro();
        String sql = "select * from cadastro";
        Cursor c = db.rawQuery(sql, null);
        Log.d("EMControle", "Cursor: " + String.valueOf(c.getCount()));
        if (c.getCount() != 0) {
            while (c.moveToNext()) {
                cad.setId(c.getInt(c.getColumnIndex("id")));
                Log.d("EMControle", String.valueOf(cad.getId()));
                cad.setNome(c.getString(c.getColumnIndex("nome")));
                Log.d("EMControle", cad.getNome());
                cad.setEmail(c.getString(c.getColumnIndex("email")));
                cad.setMedicamento(c.getInt(c.getColumnIndex("medicamento")));
                cad.setData(c.getString(c.getColumnIndex("data")));
                cad.setHora(c.getString(c.getColumnIndex("horario")));
                cad.setLembrete(c.getString(c.getColumnIndex("lembrete")));
                cad.setIdLocal(c.getInt(c.getColumnIndex("local")));
            }
            return cad;
        }
        return null;
    }
}
