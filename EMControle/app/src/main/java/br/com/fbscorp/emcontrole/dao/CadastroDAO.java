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
        String sql = "create table cadastro (id integer primary key, nome text not null, email text, medicamento integer, dia text, mes text, ano text, hora text, minuto text, lembrete text, local integer);";
        Log.d("EMControle", "Criando tabela de medicamentos");
        String sql1 = "create table medicamentos (id integer primary key, nome text not null, locais integer, frequencia integer);";
        Log.d("EMControle", "Inserindo medicamentos no banco");
        String sql2 = "insert into medicamentos (nome, locais, frequencia) values ('Avonex', 4, 604800000)";
        String sql3 = "insert into medicamentos (nome, locais, frequencia) values ('Copaxone', 30, 86400000)";
        String sql4 = "insert into medicamentos (nome, locais, frequencia) values ('Rebif', 30, 172800000)";
        String sql5 = "insert into medicamentos (nome, locais, frequencia) values ('Betaferon', 30, 172800000)";
        String sql6 = "insert into medicamentos (nome, locais, frequencia) values ('Tysabri', 1, 2629800000)";
        String sql7 = "insert into medicamentos (nome, locais, frequencia) values ('Gilenya', 1, 86400000)";
        String sql8 = "insert into medicamentos (nome, locais, frequencia) values ('Aubagio', 1, 86400000)";
        String sql9 = "insert into medicamentos (nome, locais, frequencia) values ('Tecfidera', 1, 43200000)";
        Log.d("EMControle", "Criando tabela diario");
        String sql10 = "create table diario (id integer primary key, data text not null, texto text);";
        Log.d("EMControle", "Criando tabela links");
        String sql11 = "create table links (id integer primary key, titulo text not null, url text);";
        Log.d("EMControle", "Inserindo links no banco");
        String sql12 = "insert into links (titulo, url) values ('Amigos Multiplos (AME)', 'http://www.amigosmultiplos.org.br/')";
        String sql13 = "insert into links (titulo, url) values ('Esclerose Multipla e Eu', 'http://esclerosemultiplaeeu.blogspot.com.br/')";
        String sql14 = "insert into links (titulo, url) values ('APEMERJ', 'http://apemerj.org.br/site/')";
        String sql15 = "insert into links (titulo, url) values ('AGAPEM', 'http://www.agapem.org.br/portal/')";
        String sql16 = "insert into links (titulo, url) values ('Esclerose Multipla', 'http://esclerosemultipla.com.br')";
        String sql17 = "insert into links (titulo, url) values ('Esclarecimento Multiplo', 'https://www.esclarecimentomultiplo.com.br')";
        String sql18 = "insert into links (titulo, url) values ('Bula Avonex', 'https://consultaremedios.com.br/avonex/bula')";
        String sql19 = "insert into links (titulo, url) values ('Bula Copaxone', 'https://consultaremedios.com.br/copaxone/bula')";
        String sql20 = "insert into links (titulo, url) values ('Bula Rebif', 'https://consultaremedios.com.br/rebif/bula')";
        String sql21 = "insert into links (titulo, url) values ('Bula Betaferon', 'https://consultaremedios.com.br/betaferon/bula')";
        String sql22 = "insert into links (titulo, url) values ('Bula Tysabri', 'https://consultaremedios.com.br/tysabri/bula')";
        String sql23 = "insert into links (titulo, url) values ('Bula Gilenya', 'https://consultaremedios.com.br/gilenya/bula')";
        String sql24 = "insert into links (titulo, url) values ('Bula Aubagio', 'https://consultaremedios.com.br/aubagio/bula')";
        String sql25 = "insert into links (titulo, url) values ('Bula Tecfidera', 'https://consultaremedios.com.br/tecfidera/bula')";
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
        sqLiteDatabase.execSQL(sql12);
        sqLiteDatabase.execSQL(sql13);
        sqLiteDatabase.execSQL(sql14);
        sqLiteDatabase.execSQL(sql15);
        sqLiteDatabase.execSQL(sql16);
        sqLiteDatabase.execSQL(sql17);
        sqLiteDatabase.execSQL(sql18);
        sqLiteDatabase.execSQL(sql19);
        sqLiteDatabase.execSQL(sql20);
        sqLiteDatabase.execSQL(sql21);
        sqLiteDatabase.execSQL(sql22);
        sqLiteDatabase.execSQL(sql23);
        sqLiteDatabase.execSQL(sql24);
        sqLiteDatabase.execSQL(sql25);
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
        dados.put("dia", cadastro.getDia());
        dados.put("mes", cadastro.getMes());
        dados.put("ano", cadastro.getAno());
        dados.put("hora", cadastro.getHora());
        dados.put("minuto", cadastro.getMinuto());
        dados.put("lembrete", cadastro.isLembrete());
        dados.put("local", cadastro.getIdLocal());

        db.insert("cadastro", null, dados);

        String sql = "select * from cadastro where id=1";
        Cursor c = db.rawQuery(sql, null);
        Log.d("EMControle", "Cursor salvo: " + String.valueOf(c.getCount()));
        db.close();
    }

    public void     atualiza(Cadastro cadastro) {
        Log.d("EMControle", "Atualizando cadastro no banco");
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();

        dados.put("nome", cadastro.getNome());
        dados.put("email", cadastro.getEmail());
        dados.put("medicamento", cadastro.getMedicamento());
        dados.put("dia", cadastro.getDia());
        dados.put("mes", cadastro.getMes());
        dados.put("ano", cadastro.getAno());
        dados.put("hora", cadastro.getHora());
        dados.put("minuto", cadastro.getMinuto());
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
                cad.setDia(c.getString(c.getColumnIndex("dia")));
                cad.setMes(c.getString(c.getColumnIndex("mes")));
                cad.setAno(c.getString(c.getColumnIndex("ano")));
                cad.setHora(c.getString(c.getColumnIndex("hora")));
                cad.setMinuto(c.getString(c.getColumnIndex("minuto")));
                cad.setLembrete(c.getString(c.getColumnIndex("lembrete")));
                cad.setIdLocal(c.getInt(c.getColumnIndex("local")));
            }
            return cad;
        }
        return null;
    }
}
