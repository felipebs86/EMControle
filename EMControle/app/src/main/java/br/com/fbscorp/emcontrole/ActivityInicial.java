package br.com.fbscorp.emcontrole;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Calendar;

import br.com.fbscorp.emcontrole.dao.CadastroDAO;
import br.com.fbscorp.emcontrole.helper.CadastroHelper;
import br.com.fbscorp.emcontrole.model.Cadastro;
import br.com.fbscorp.emcontrole.model.Medicamento;

public class ActivityInicial extends AppCompatActivity {

    private TextView txtData, txtHora;
    private ImageView imgLocal;
    private String medicamento = "person";
    private Cadastro cadastro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.d("EMControle " + this.getLocalClassName(), "Iniciando activity inicial");

        txtData = (TextView) findViewById(R.id.init_txt_data);
        imgLocal = (ImageView) findViewById(R.id.init_img);
        txtHora = (TextView) findViewById(R.id.init_txt_hora);
        Button bt = (Button) findViewById(R.id.teste);
        Button btnDiario = (Button) findViewById(R.id.btn_lista_diario);
        Button btnLinks = (Button) findViewById(R.id.btn_lista_links);


        Intent intent = getIntent();
        Bundle dados = intent.getExtras();

        cadastro = (Cadastro) dados.get("cadastro");

        Log.d("EMControle", cadastro.getNome());

        if (cadastro.isLembrete().equalsIgnoreCase("true")) {

            boolean alarmeAtivo = (PendingIntent.getBroadcast(this, 0, new Intent("ALARME_DISPARADO"), PendingIntent.FLAG_NO_CREATE) == null);

            if(alarmeAtivo){
                Log.i("EMControle", "Novo alarme");

                Intent intent1 = new Intent("ALARME_DISPARADO");
                PendingIntent p = PendingIntent.getBroadcast(this, 0, intent1, 0);

                Calendar c = Calendar.getInstance();
                c.setTimeInMillis(System.currentTimeMillis());
                c.add(Calendar.SECOND, 3);

                AlarmManager alarme = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarme.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 5000, p);
            }
            else{
                Log.i("EMControle", "Alarme ja ativo");
            }
        }



        //CadastroDAO dao = new CadastroDAO(this);
        //Cadastro cadastro = dao.buscaCadastro();

        txtData.setText(cadastro.getData());
        txtHora.setText(cadastro.getHora());

        //medicamento = cadastro.getMedicamento() //- gravar nome do medicamento no banco para essa busca

        if (cadastro.getMedicamento() == 0){
            imgLocal.setImageResource(R.drawable.aplicacao_avx);
        } else if (cadastro.getMedicamento() == 1){
            imgLocal.setImageResource(R.drawable.aplicacao_cpx);
        }

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ActivityInicial.this, ActivityCadastro.class);
                intent1.putExtra("cad", cadastro);
                startActivity(intent1);
            }
        });

        btnDiario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ActivityInicial.this, ActivityDiario.class);
                startActivity(intent1);
            }
        });

        btnLinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ActivityInicial.this, ActivityLinks.class);
                startActivity(intent1);
            }
        });

    }

}
