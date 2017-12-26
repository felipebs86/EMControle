package br.com.fbscorp.emcontrole;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import br.com.fbscorp.emcontrole.dao.LinksDAO;
import br.com.fbscorp.emcontrole.model.Link;

public class ActivityLinks extends AppCompatActivity {

    private ListView listaLinks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_links);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listaLinks = (ListView) findViewById(R.id.lista_links);

        listaLinks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View ite, int posicao, long id) {
                Link link = (Link) listaLinks.getItemAtPosition(posicao);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link.getUrl()));
                startActivity(intent);
            }
        });

    }

    private void carregaLista() {
        LinksDAO dao = new LinksDAO(this);
        List<Link> links = dao.getLinks();
        dao.close();

        ArrayAdapter<Link> adapter = new ArrayAdapter<Link>(this, android.R.layout.simple_list_item_1, links);
        listaLinks.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }
}
