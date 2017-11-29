package br.com.fbscorp.emcontrole;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import br.com.fbscorp.emcontrole.dao.CadastroDAO;
import br.com.fbscorp.emcontrole.helper.CadastroHelper;
import br.com.fbscorp.emcontrole.model.Cadastro;
import br.com.fbscorp.emcontrole.model.Medicamento;

public class ActivityInicial extends AppCompatActivity {

    private TextView txtData, txtHora;
    private ImageView imgLocal;
    private String medicamento = "person";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtData = (TextView) findViewById(R.id.init_txt_data);
        imgLocal = (ImageView) findViewById(R.id.init_img);
        txtHora = (TextView) findViewById(R.id.init_txt_hora);

        //CadastroDAO dao = new CadastroDAO(this);
        //Cadastro cadastro = dao.buscaCadastro();

        //txtData.setText(cadastro.getData());
        //txtHora.setText(cadastro.getHora());

        //medicamento = cadastro.getMedicamento() - gravar nome do medicamento no banco para essa busca

        if (medicamento.equalsIgnoreCase("avonex")){
            imgLocal.setImageResource(R.drawable.aplicacao_avx);
        } else if (medicamento.equalsIgnoreCase("copaxone")){
            imgLocal.setImageResource(R.drawable.aplicacao_cpx);
        }




    }

}
