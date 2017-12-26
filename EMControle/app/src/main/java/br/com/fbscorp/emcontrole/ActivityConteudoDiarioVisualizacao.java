package br.com.fbscorp.emcontrole;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import br.com.fbscorp.emcontrole.model.Diario;

public class ActivityConteudoDiarioVisualizacao extends AppCompatActivity {
    private TextView txtCabecalho;
    private TextView txtTexto;
    private Diario diario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conteudo_diario_visualizacao);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        Bundle dados = intent.getExtras();

        txtCabecalho = (TextView) findViewById(R.id.cabecalho_diario_visualizacao);
        txtTexto = (TextView) findViewById(R.id.conteudo_diario_visualizacao);

        if (dados != null) {
            diario = (Diario) dados.get("diario");

            txtCabecalho.setText(diario.getData());
            txtTexto.setText(diario.getTexto());
        }

        Log.d("EMControle", diario.getData());
        Log.d("EMControle", diario.getTexto());

    }

}
