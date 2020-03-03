package br.com.fbscorp.emcontrole;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.fbscorp.emcontrole.dao.CadastroDAO;
import br.com.fbscorp.emcontrole.dao.MedicamentoDAO;
import br.com.fbscorp.emcontrole.model.Cadastro;
import br.com.fbscorp.emcontrole.model.Medicamento;
import br.com.fbscorp.emcontrole.service.NotificationService;

public class ActivityInicial extends AppCompatActivity {

    private TextView txtData, txtHora, txtLocal, txtMedicamento;
    private TextView txtLabelLocal;
    private ImageView imgLocal;
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
        createNotificationChannel();
        c = Calendar.getInstance();
        Log.d("EMControle " + this.getLocalClassName(), "Iniciando activity inicial");

        txtMedicamento = (TextView) findViewById(R.id.init_txt_medicamento);
        txtData = (TextView) findViewById(R.id.init_txt_data);
        imgLocal = (ImageView) findViewById(R.id.init_img);
        txtHora = (TextView) findViewById(R.id.init_txt_hora);
        txtLocal = (TextView) findViewById(R.id.init_txt_local);
        txtLabelLocal = findViewById(R.id.label_txt_local);
        txtSaudacao = (TextView) findViewById(R.id.ini_saudacao);
        Button bt = (Button) findViewById(R.id.teste);
        Button btnDiario = (Button) findViewById(R.id.btn_lista_diario);
        Button btnLinks = (Button) findViewById(R.id.btn_lista_links);
        Button btnRegistrar = (Button) findViewById(R.id.registrar);

        Intent intent = getIntent();
        Bundle dados = intent.getExtras();

        cadastro = (Cadastro) dados.get("cadastro");

        Log.d("EMControle", cadastro.getNome());

        if (cadastro.getMedicamento() == 0){
            imgLocal.setImageResource(R.drawable.aplicacao_avx);
        } else if (cadastro.getMedicamento() == 1){
            imgLocal.setImageResource(R.drawable.aplicacao_cpx);
        } else if (cadastro.getMedicamento() == 2){
            imgLocal.setImageResource(R.drawable.aplicacao_cpx);
        } else if (cadastro.getMedicamento() == 3){
            imgLocal.setImageResource(R.drawable.aplicacao_cpx);
        } else if (cadastro.getMedicamento() == 4){
            imgLocal.setImageResource(R.drawable.aplicacao_tys);
        } else if (cadastro.getMedicamento() == 5){
            imgLocal.setImageResource(R.drawable.aplicacao_gil);
        } else if (cadastro.getMedicamento() == 6){
            imgLocal.setImageResource(R.drawable.aplicacao_aub);
        } else if (cadastro.getMedicamento() == 7){
            imgLocal.setImageResource(R.drawable.aplicacao_tec);
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
                } else if (cadastro.getMedicamento() == 2){
                    image.setImageResource(R.drawable.aplicacao_cpx);
                } else if (cadastro.getMedicamento() == 3){
                    image.setImageResource(R.drawable.aplicacao_cpx);
                } else if (cadastro.getMedicamento() == 4){
                    image.setImageResource(R.drawable.aplicacao_tys);
                } else if (cadastro.getMedicamento() == 5){
                    image.setImageResource(R.drawable.aplicacao_gil);
                } else if (cadastro.getMedicamento() == 6){
                    image.setImageResource(R.drawable.aplicacao_aub);
                } else if (cadastro.getMedicamento() == 7){
                    image.setImageResource(R.drawable.aplicacao_tec);
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
                Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                long milliseconds = 10;
                vibrator.vibrate(milliseconds);

                Intent intent1 = new Intent(ActivityInicial.this, ActivityCadastro.class);
                intent1.putExtra("cad", cadastro);
                startActivity(intent1);
                finish();
            }
        });

        btnDiario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                long milliseconds = 10;
                vibrator.vibrate(milliseconds);

                Intent intent1 = new Intent(ActivityInicial.this, ActivityDiario.class);
                startActivity(intent1);
            }
        });

        btnLinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                long milliseconds = 10;
                vibrator.vibrate(milliseconds);

                Intent intent1 = new Intent(ActivityInicial.this, ActivityLinks.class);
                startActivity(intent1);
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                long milliseconds = 10;
                vibrator.vibrate(milliseconds);

                String dataCadastro = cadastro.getDia() + "/" + cadastro.getMes() + "/" + cadastro.getAno();
                String dataAtual = formata(c.get(Calendar.DAY_OF_MONTH)) + "/" + formata(c.get(Calendar.MONTH) + 1) + "/" + String.valueOf(c.get(Calendar.YEAR));

                if (!dataAtual.equals(dataCadastro)) {
                    Context context = getApplicationContext();
                    CharSequence text = "Ainda não está na data da sua aplicação! Qualquer alteração, revise suas configurações.";
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    return;
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityInicial.this);
                builder.setTitle("Registrar aplicação do medicamento");
                builder.setMessage("Confirma aplicação da medicação?");
                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        realizaAplicacao();
                        NotificationManager nMgr = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
                        nMgr.cancelAll();
                        carregaDados();
                    }
                });
                builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) { }
                });
                alerta = builder.create();
                alerta.show();
            }
        });
    }

    private void carregaDados() {
        txtSaudacao.setText(String.format("Olá %s!", cadastro.getNome()));
        txtMedicamento.setText(selecionaNomeDoMedicamento(cadastro.getMedicamento()));
        txtData.setText(String.format("%s/%s/%s", cadastro.getDia(), cadastro.getMes(), cadastro.getAno()));
        txtHora.setText(String.format("%s:%s", cadastro.getHora(), cadastro.getMinuto()));
        if ((cadastro.getMedicamento() == 0 && cadastro.getIdLocal() == 4) || (cadastro.getMedicamento() == 1 && cadastro.getIdLocal() == 30)) {
            txtLocal.setText(String.valueOf(1));
        } else {
            List medicamentosSemLocal = new ArrayList();
            medicamentosSemLocal.add(4);
            medicamentosSemLocal.add(5);
            medicamentosSemLocal.add(6);
            medicamentosSemLocal.add(7);

            if (!medicamentosSemLocal.contains(cadastro.getMedicamento())){
                txtLocal.setText(String.valueOf(cadastro.getIdLocal() + 1));
            } else {
                txtLocal.setVisibility(View.INVISIBLE);
                txtLabelLocal.setVisibility(View.INVISIBLE);
            }
        }
    }

    private String selecionaNomeDoMedicamento(int id){
        MedicamentoDAO dao = new MedicamentoDAO(this);
        List<Medicamento> medicamentos = dao.getMedicamentos();
        Medicamento medicamento = medicamentos.get(id);

        return medicamento.getNome();
    }

    private void realizaAplicacao() {
        String novaData = "";
        List<Integer> medicamentos30Posicoes = new ArrayList();
        medicamentos30Posicoes.add(1);
        medicamentos30Posicoes.add(2);
        medicamentos30Posicoes.add(3);

        if (cadastro.getMedicamento() == 0) {
            c.add(Calendar.DAY_OF_MONTH, 7);
            novaData = formata(c.get(Calendar.DAY_OF_MONTH)) + "/" + formata(c.get(Calendar.MONTH) + 1) + "/" + String.valueOf(c.get(Calendar.YEAR));
            if (cadastro.getIdLocal() < 4) {
                cadastro.setIdLocal(cadastro.getIdLocal() + 1);
            } else {
                cadastro.setIdLocal(1);
            }
        } else if (medicamentos30Posicoes.contains(cadastro.getMedicamento())) {
             if (cadastro.getMedicamento() == 1) {
                 c.add(Calendar.DAY_OF_MONTH, 1);
                 novaData = formata(c.get(Calendar.DAY_OF_MONTH)) + "/" + formata(c.get(Calendar.MONTH) + 1) + "/" + String.valueOf(c.get(Calendar.YEAR));
             } else {
                 c.add(Calendar.DAY_OF_MONTH, 2);
                 novaData = formata(c.get(Calendar.DAY_OF_MONTH)) + "/" + formata(c.get(Calendar.MONTH) + 1) + "/" + String.valueOf(c.get(Calendar.YEAR));
             }

            if (cadastro.getIdLocal() < 30) {
                cadastro.setIdLocal(cadastro.getIdLocal() + 1);
            } else {
                cadastro.setIdLocal(1);
            }
        } else {
            if (cadastro.getMedicamento() == 4) {
                c.add(Calendar.DAY_OF_MONTH, 30);
                novaData = formata(c.get(Calendar.DAY_OF_MONTH)) + "/" + formata(c.get(Calendar.MONTH) + 1) + "/" + String.valueOf(c.get(Calendar.YEAR));
            } else if (cadastro.getMedicamento() == 5 || cadastro.getMedicamento() == 6) {
                c.add(Calendar.DAY_OF_MONTH, 1);
                novaData = formata(c.get(Calendar.DAY_OF_MONTH)) + "/" + formata(c.get(Calendar.MONTH) + 1) + "/" + String.valueOf(c.get(Calendar.YEAR));
            } else {
                c.add(Calendar.HOUR, 12);
                novaData = formata(c.get(Calendar.DAY_OF_MONTH)) + "/" + formata(c.get(Calendar.MONTH) + 1) + "/" + String.valueOf(c.get(Calendar.YEAR));
                cadastro.setHora(adiciona12horas(cadastro.getHora()));
            }
            cadastro.setIdLocal(1);
        }

        cadastro.setDia(novaData.split("/")[0]);
        cadastro.setMes(novaData.split("/")[1]);
        cadastro.setAno(novaData.split("/")[2]);

        CadastroDAO dao = new CadastroDAO(ActivityInicial.this);

        dao.atualiza(cadastro);
        dao.close();

        NotificationService notificationService = new NotificationService();
        notificationService.configuraAlarme(cadastro, this);

        Log.d("EMControle", "Nova Data: " + novaData);
        Log.d("EMControle", "Novo Local: " + cadastro.getIdLocal());
    }

    private String adiciona12horas(String hora) {
        int horaNumerica = Integer.parseInt(hora);

        int horaSomada = horaNumerica + 12;

        if (horaSomada > 24){
            int horaAjustada = horaSomada - 24;
            return String.valueOf(horaAjustada);
        }

        return String.valueOf(horaSomada);
    }

    private String formata(int numero) {
        String numeroFormatado = String.valueOf(numero);
        if (numero < 10) {
            numeroFormatado =  '0' + numeroFormatado;
        }
        return numeroFormatado;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = Notification.CATEGORY_ALARM;
            String description = Notification.CATEGORY_ALARM;
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("123", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaDados();
    }
}
