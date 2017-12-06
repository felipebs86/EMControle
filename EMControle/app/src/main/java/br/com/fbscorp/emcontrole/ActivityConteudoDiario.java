package br.com.fbscorp.emcontrole;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import br.com.fbscorp.emcontrole.model.Cadastro;
import br.com.fbscorp.emcontrole.model.Diario;

public class ActivityConteudoDiario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conteudo_diario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        Bundle dados = intent.getExtras();

        Diario diario = (Diario) dados.get("diario");

        TextView txtCabecalho = (TextView) findViewById(R.id.cabecalho_diario);
        TextView txtTexto = (TextView) findViewById(R.id.conteudo_diario);


        //txtCabecalho.setText(diario.getData() + "--" + diario.getHora());
        //txtTexto.setText(diario.getTexto());

    }

}
