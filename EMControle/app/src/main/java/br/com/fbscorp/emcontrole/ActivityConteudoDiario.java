package br.com.fbscorp.emcontrole;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import br.com.fbscorp.emcontrole.dao.CadastroDAO;
import br.com.fbscorp.emcontrole.dao.DiarioDAO;
import br.com.fbscorp.emcontrole.helper.DiarioHelper;
import br.com.fbscorp.emcontrole.model.Cadastro;
import br.com.fbscorp.emcontrole.model.Diario;

public class ActivityConteudoDiario extends AppCompatActivity {

    private DiarioHelper helper;
    private TextView txtCabecalho;
    private EditText txtTexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conteudo_diario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        Bundle dados = intent.getExtras();

        txtCabecalho = (TextView) findViewById(R.id.cabecalho_diario);
        txtTexto = (EditText) findViewById(R.id.conteudo_diario);

        helper = new DiarioHelper(this);



        if (dados != null) {
            Diario diario = (Diario) dados.get("diario");

            txtCabecalho.setText(diario.getData());
            txtTexto.setText(diario.getTexto());
        }

        Calendar c  = Calendar.getInstance();


        txtCabecalho.setText( "Diario do dia "
                + formata(c.get(c.DAY_OF_MONTH))
                + "/" + formata(c.get(c.MONTH)+1)
                + "/" + formata(c.get(c.YEAR))
                + " as "
                + formata(c.get(c.HOUR_OF_DAY))
                + ":"
                + formata(c.get(c.MINUTE)));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =  getMenuInflater();
        inflater.inflate(R.menu.menu_activity_diario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_diario_salvar:
                DiarioDAO dao = new DiarioDAO(ActivityConteudoDiario.this);
                Diario diario = helper.pegaDiario();

                if (dao.existeDiario(diario)) {
                    dao.atualiza(diario);
                } else{
                    dao.insere(diario);
                }
                finish();
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    private String formata(int numero) {
        String numeroFormatado = String.valueOf(numero);
        if (numero < 10) {
            numeroFormatado =  '0' + numeroFormatado;
        }
        return numeroFormatado;
    }
}
