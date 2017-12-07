package br.com.fbscorp.emcontrole;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import br.com.fbscorp.emcontrole.dao.DiarioDAO;
import br.com.fbscorp.emcontrole.dao.LinksDAO;
import br.com.fbscorp.emcontrole.model.Diario;
import br.com.fbscorp.emcontrole.model.Link;

public class ActivityDiario extends AppCompatActivity {

    private ListView listaDiarios;
    private Diario diario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityDiario.this, ActivityConteudoDiario.class);
                startActivity(intent);
            }
        });

        listaDiarios = (ListView) findViewById(R.id.lista_diario);

        registerForContextMenu(listaDiarios);

        listaDiarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int posicao, long id) {
                Diario diario = (Diario) listaDiarios.getItemAtPosition(posicao);
                Intent intent = new Intent(ActivityDiario.this, ActivityConteudoDiario.class);
                intent.putExtra("diario", diario);
                startActivity(intent);
            }
        });
    }

    private void carregaLista() {
        DiarioDAO dao = new DiarioDAO(this);
        List<Diario> diarios = dao.getDiarios();
        dao.close();

        ArrayAdapter<Diario> adapter = new ArrayAdapter<Diario>(this, android.R.layout.simple_list_item_1, diarios);
        listaDiarios.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem excluir = menu.add("Excluir");
        excluir.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Diario diario = (Diario) listaDiarios.getItemAtPosition(info.position);

                LinksDAO dao = new LinksDAO(ActivityDiario.this);
                dao.exclui(diario);
                dao.close();

                carregaLista();
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

}
