package br.com.fbscorp.emcontrole;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.fbscorp.emcontrole.dao.CadastroDAO;
import br.com.fbscorp.emcontrole.helper.CadastroHelper;
import br.com.fbscorp.emcontrole.model.Cadastro;
import br.com.fbscorp.emcontrole.model.Medicamento;

public class ActivityInicial extends AppCompatActivity {

    private TextView txtData, txtHora, txtLocal;
    private ImageView imgLocal;
    private String medicamento = "person";
    private Cadastro cadastro;
    private TextView txtSaudacao;
    private Calendar c;
    private AlertDialog alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //final Calendar c = Calendar.getInstance();
        Log.d("EMControle " + this.getLocalClassName(), "Iniciando activity inicial");

        NotificationManager nMgr = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        nMgr.cancelAll();

        txtData = (TextView) findViewById(R.id.init_txt_data);
        imgLocal = (ImageView) findViewById(R.id.init_img);
        txtHora = (TextView) findViewById(R.id.init_txt_hora);
        txtLocal = (TextView) findViewById(R.id.init_txt_local);
        txtSaudacao = (TextView) findViewById(R.id.ini_saudacao);
        Button bt = (Button) findViewById(R.id.teste);
        Button btnDiario = (Button) findViewById(R.id.btn_lista_diario);
        Button btnLinks = (Button) findViewById(R.id.btn_lista_links);
        Button btnRegistrar = (Button) findViewById(R.id.registrar);


        Intent intent = getIntent();
        Bundle dados = intent.getExtras();

        cadastro = (Cadastro) dados.get("cadastro");



        Log.d("EMControle", cadastro.getNome());

        if (cadastro.isLembrete().equalsIgnoreCase("true")) {

            //boolean alarmeAtivo = (PendingIntent.getBroadcast(this, 0, new Intent("ALARME_DISPARADO"), PendingIntent.FLAG_NO_CREATE) == null);

            //if(alarmeAtivo){
                Log.i("EMControle", "Novo alarme");

                Intent intent1 = new Intent("ALARME_DISPARADO").putExtra("cadastro", cadastro);
                PendingIntent p = PendingIntent.getBroadcast(this, 0, intent1, 0);

                c = Calendar.getInstance();
                c.setTimeInMillis(System.currentTimeMillis());
                c.add(Calendar.SECOND, 3);

                AlarmManager alarme = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarme.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 5000, p);
            }
            else{
                Log.i("EMControle", "Alarme ja ativo");
            }
        //}



        if (cadastro.getMedicamento() == 0){
            imgLocal.setImageResource(R.drawable.aplicacao_avx);
        } else if (cadastro.getMedicamento() == 1){
            imgLocal.setImageResource(R.drawable.aplicacao_cpx);
        }

        imgLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(ActivityInicial.this);
                dialog.setContentView(R.layout.imagem_local);
                dialog.setTitle("Locais de aplicação");

                ImageView image = (ImageView) dialog.findViewById(R.id.cad_imagem_local);
                if(cadastro.getMedicamento() == 0){
                    image.setImageResource(R.drawable.aplicacao_avx);
                } else if (cadastro.getMedicamento() == 1){
                    image.setImageResource(R.drawable.aplicacao_cpx);
                }

                FloatingActionButton dialogButton = (FloatingActionButton) dialog.findViewById(R.id.cad_imagem_fechar);
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ActivityInicial.this, ActivityCadastro.class);
                intent1.putExtra("cad", cadastro);
                startActivity(intent1);
                finish();
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

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = cadastro.getData();
                String dia = data.split("/")[0];
                //String mes = data.split("/")[1];
                //String ano = data.split("/")[2];
                String horario = cadastro.getHora();
                String hora = horario.split(":")[0];
                String min = horario.split(":")[1];

                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityInicial.this);
                builder.setTitle("Registrar aplicação do medicamento");
                builder.setMessage("Confirma aplicação da medicação?");
                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        realizaAplicacao();
                        NotificationManager nMgr = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
                        nMgr.cancelAll();
                    }
                });
                builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) { }
                });
                alerta = builder.create();
                alerta.show();

                /*
                //TODO Futura feature para alteração automatica de alarme quando aplicado fora do horario
                else if (dia.equals(String.valueOf(c.get(Calendar.DAY_OF_MONTH))) && !hora.equals(String.valueOf(c.get(Calendar.HOUR_OF_DAY)))) {
                    final String novoHorario = Calendar.HOUR_OF_DAY + ":" + Calendar.MINUTE;
                    AlertDialog.Builder builder = new AlertDialog.Builder(ActivityInicial.this);
                    builder.setTitle("Registrar aplicação");
                    builder.setMessage("Confirma aplicação da medicação e alteração ho horario para" + novoHorario + "?");
                    builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            realizaAplicacao(novoHorario);
                        }
                    });
                    builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) { }
                    });
                    alerta = builder.create();
                    alerta.show();
                } else if (!dia.equals(String.valueOf(c.get(Calendar.DAY_OF_MONTH))) && !hora.equals(String.valueOf(c.get(Calendar.HOUR_OF_DAY)))) {
                    final String novoHorario = Calendar.HOUR_OF_DAY + ":" + Calendar.MINUTE;
                    final String novaData = Calendar.DAY_OF_MONTH + "/" + Calendar.MONTH + Calendar.YEAR;
                    AlertDialog.Builder builder = new AlertDialog.Builder(ActivityInicial.this);
                    builder.setTitle("Registrar aplicação");
                    builder.setMessage("Confirma aplicação da medicação e alteração da data para "+novaData+ " e o horario para " + novoHorario + "?");
                    builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            realizaAplicacao(novoHorario, novaData);
                        }
                    });
                    builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) { }
                    });
                    alerta = builder.create();
                    alerta.show();
                }*/

            }


        });

    }

    private void carregaDados() {
        txtSaudacao.setText("Olá " + cadastro.getNome() + "!");
        txtData.setText(cadastro.getData());
        txtHora.setText(cadastro.getHora());
        txtLocal.setText(String.valueOf(cadastro.getIdLocal() + 1));
    }

    /*
    //TODO Futura feature
    private void realizaAplicacao(String novoHorario, String novaData) {
        cadastro.setData(novaData);
        String novaDataAtualizada = cadastro.getData();

        if (cadastro.getMedicamento() == 0) {
            c.add(Calendar.DAY_OF_MONTH, 7);
            novaDataAtualizada = formata(c.get(Calendar.DAY_OF_MONTH)) + "/" + formata(c.get(Calendar.MONTH) + 1) + "/" + String.valueOf(c.get(Calendar.YEAR));
            if (cadastro.getIdLocal() < 4) {
                cadastro.setIdLocal(cadastro.getIdLocal() + 1);
            } else {
                cadastro.setIdLocal(1);
            }
        } else if (cadastro.getMedicamento() == 1) {
            c.add(Calendar.DAY_OF_MONTH, 1);
            novaData = formata(c.get(Calendar.DAY_OF_MONTH)) + "/" + formata(c.get(Calendar.MONTH) + 1) + "/" + String.valueOf(c.get(Calendar.YEAR));
            if (cadastro.getIdLocal() < 30) {
                cadastro.setIdLocal(cadastro.getIdLocal() + 1);
            } else {
                cadastro.setIdLocal(1);
            }
        }

        cadastro.setData(novaDataAtualizada);
        cadastro.setHora(novoHorario);

        CadastroDAO dao = new CadastroDAO(ActivityInicial.this);

        dao.atualiza(cadastro);

        Log.d("EMControle", "Nova Data: " + novaDataAtualizada);
        Log.d("EMControle", "Novo Local: " + cadastro.getIdLocal());
    }

    private void realizaAplicacao(String novoHorario) {
        String novaData = cadastro.getData();

        if (cadastro.getMedicamento() == 0) {
            c.add(Calendar.DAY_OF_MONTH, 7);
            novaData = formata(c.get(Calendar.DAY_OF_MONTH)) + "/" + formata(c.get(Calendar.MONTH) + 1) + "/" + String.valueOf(c.get(Calendar.YEAR));
            if (cadastro.getIdLocal() < 4) {
                cadastro.setIdLocal(cadastro.getIdLocal() + 1);
            } else {
                cadastro.setIdLocal(1);
            }
        } else if (cadastro.getMedicamento() == 1) {
            c.add(Calendar.DAY_OF_MONTH, 1);
            novaData = formata(c.get(Calendar.DAY_OF_MONTH)) + "/" + formata(c.get(Calendar.MONTH) + 1) + "/" + String.valueOf(c.get(Calendar.YEAR));
            if (cadastro.getIdLocal() < 30) {
                cadastro.setIdLocal(cadastro.getIdLocal() + 1);
            } else {
                cadastro.setIdLocal(1);
            }
        }

        cadastro.setData(novaData);
        cadastro.setHora(novoHorario);

        CadastroDAO dao = new CadastroDAO(ActivityInicial.this);

        dao.atualiza(cadastro);

        Log.d("EMControle", "Nova Data: " + novaData);
        Log.d("EMControle", "Novo Local: " + cadastro.getIdLocal());

    }
    */
    private void realizaAplicacao() {
        String novaData = cadastro.getData();

        if (cadastro.getMedicamento() == 0) {
            c.add(Calendar.DAY_OF_MONTH, 7);
            novaData = formata(c.get(Calendar.DAY_OF_MONTH)) + "/" + formata(c.get(Calendar.MONTH) + 1) + "/" + String.valueOf(c.get(Calendar.YEAR));
            if (cadastro.getIdLocal() < 4) {
                cadastro.setIdLocal(cadastro.getIdLocal() + 1);
            } else {
                cadastro.setIdLocal(1);
            }
        } else if (cadastro.getMedicamento() == 1) {
            c.add(Calendar.DAY_OF_MONTH, 1);
            novaData = formata(c.get(Calendar.DAY_OF_MONTH)) + "/" + formata(c.get(Calendar.MONTH) + 1) + "/" + String.valueOf(c.get(Calendar.YEAR));
            if (cadastro.getIdLocal() < 30) {
                cadastro.setIdLocal(cadastro.getIdLocal() + 1);
            } else {
                cadastro.setIdLocal(1);
            }
        }

        cadastro.setData(novaData);

        CadastroDAO dao = new CadastroDAO(ActivityInicial.this);

        dao.atualiza(cadastro);

        Log.d("EMControle", "Nova Data: " + novaData);
        Log.d("EMControle", "Novo Local: " + cadastro.getIdLocal());
    }

    private String formata(int numero) {
        String numeroFormatado = String.valueOf(numero);
        if (numero < 10) {
            numeroFormatado =  '0' + numeroFormatado;
        }
        return numeroFormatado;
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaDados();
    }
}
