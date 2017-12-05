package br.com.fbscorp.emcontrole;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.fbscorp.emcontrole.dao.CadastroDAO;
import br.com.fbscorp.emcontrole.dao.MedicamentoDAO;
import br.com.fbscorp.emcontrole.helper.CadastroHelper;
import br.com.fbscorp.emcontrole.model.Cadastro;
import br.com.fbscorp.emcontrole.model.Medicamento;

public class ActivityCadastro extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener{

    private Cadastro cadastro;
    private CadastroHelper helper;
    private TextView txtData, txtHora;
    private Button btnData;
    private Button btnHora;
    private Button btnImagem;
    private int ano, mes, dia, hora, minuto;
    private String data;
    private Spinner spnMedicamentos;
    private Medicamento medicamentoSelecionado;
    private Spinner spnLocais;
    private Switch alarme;
    private String  imgMedicamento = "Avonex";
    private boolean isCadastrado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("EMControle", "Iniciando activity cadastro");
        setContentView(R.layout.activity_cadastro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        helper = new CadastroHelper(this);

        txtData = (TextView) findViewById(R.id.cad_txt_data);
        txtHora = (TextView) findViewById(R.id.cad_txt_hora);
        btnData = (Button) findViewById(R.id.cad_btn_data);
        btnHora = (Button) findViewById(R.id.cad_btn_alarme);
        btnImagem = (Button) findViewById(R.id.cad_btn_locais);
        spnMedicamentos = (Spinner) findViewById(R.id.cad_medicamento);
        spnLocais = (Spinner) findViewById(R.id.cad_id_local);
        alarme = (Switch) findViewById(R.id.cad_lembrete);

        MedicamentoDAO dao = new MedicamentoDAO(this);
        List<Medicamento> medicamentos = dao.getMedicamentos();
        ArrayAdapter<Medicamento> spinnerArrayAdapter = new ArrayAdapter<Medicamento>(this, R.layout.emcontrole_spinner_dropdown_item, medicamentos);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.emcontrole_spinner_item);
        spnMedicamentos.setAdapter(spinnerArrayAdapter);
        spnMedicamentos.setOnItemSelectedListener(this);

        final Calendar cal = Calendar.getInstance();
        ano = cal.get(Calendar.YEAR);
        mes = cal.get(Calendar.MONTH);
        dia = cal.get(Calendar.DAY_OF_MONTH);
        hora = cal.get(Calendar.HOUR);
        minuto = cal.get(Calendar.MINUTE);

        txtData.setInputType(InputType.TYPE_NULL);
        btnData.setOnClickListener(this);
        txtHora.setInputType(InputType.TYPE_NULL);
        btnHora.setOnClickListener(this);
        btnImagem.setOnClickListener(this);


        CadastroDAO cadastroDAO = new CadastroDAO(this);
        isCadastrado = cadastroDAO.existeCadastro();
        if (isCadastrado == true) {
            //Log.d("EMControle", "Veio dados na intent: " + dados.toString());
            //cadastro = (Cadastro) dados.get("cad");
            //Log.d("EMControle", String.valueOf(cadastro));
            cadastro = cadastroDAO.buscaCadastro();
            helper.populaCadastro(this);
        }
    }

    private List<Integer> populaLocais(int locais) {
        int x = locais;
        ArrayList<Integer> lista = new ArrayList<>();
        for (int i = 1 ; i <= locais; i++){
            lista.add(i);
        }
        return lista;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =  getMenuInflater();
        inflater.inflate(R.menu.menu_activity_cadastro, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_cadastro_salvar:
                Cadastro cadastro = helper.pegaCadastro();
                CadastroDAO dao = new CadastroDAO(this);

                Log.d("EMControle", "Botao salvar selecionado");
                Log.d("EMControle", dao.existeCadastro().toString());
                //dao.insere(cadastro);

                if (dao.existeCadastro()){
                    Log.d("EMControle", "Existe cadastro no banco");
                    dao.atualiza(cadastro);
                } else{
                    Log.d("EMControle", "Nao existe cadastro no banco");
                    dao.insere(cadastro);
                }

                dao.close();




                Toast.makeText(ActivityCadastro.this, cadastro.getNome() + ", seu cadastro foi salvo!", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(ActivityCadastro.this, ActivityInicial.class);
                intent1.putExtra("cadastro", cadastro);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if (view == btnData){
            final Calendar c = Calendar.getInstance();
            ano = c.get(Calendar.YEAR);
            mes = c.get(Calendar.MONTH);
            dia = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            txtData.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        }
                    }, ano, mes, dia);

            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
            datePickerDialog.show();
        }

        if (view == btnHora) {

            final Calendar c = Calendar.getInstance();
            hora = c.get(Calendar.HOUR_OF_DAY);
            minuto = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            txtHora.setText(hourOfDay + ":" + minute);
                        }
                    }, hora, minuto, false);

            timePickerDialog.show();
        }

        if(view == btnImagem){

            final Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.imagem_local);
            dialog.setTitle("Locais de aplicação");

            ImageView image = (ImageView) dialog.findViewById(R.id.cad_imagem_local);
            if(medicamentoSelecionado.getNome().equalsIgnoreCase("copaxone")){
                image.setImageResource(R.drawable.aplicacao_cpx);
            } else if (medicamentoSelecionado.getNome().equalsIgnoreCase("avonex")){
                image.setImageResource(R.drawable.aplicacao_avx);
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
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()){
            case R.id.cad_medicamento:
                medicamentoSelecionado = (Medicamento) spnMedicamentos.getSelectedItem();
                List<Integer> locais = populaLocais(medicamentoSelecionado.getLocais());
                ArrayAdapter<Integer> spinnerLocaisArrayAdapter = new ArrayAdapter<Integer>(this, R.layout.emcontrole_spinner_dropdown_item, locais);
                spinnerLocaisArrayAdapter.setDropDownViewResource(R.layout.emcontrole_spinner_item);
                spnLocais.setAdapter(spinnerLocaisArrayAdapter);

                if (isCadastrado == true) {
                    if (cadastro.getIdLocal() <= 3 ) {
                        Log.d("EMControle", String.valueOf(spnMedicamentos.getSelectedItemPosition()));
                        spnLocais.setSelection(cadastro.getIdLocal());
                    } else if (spnMedicamentos.getSelectedItemPosition() == 1) {
                        Log.d("EMControle", String.valueOf(spnMedicamentos.getSelectedItemPosition()));
                        spnLocais.setSelection(cadastro.getIdLocal());
                    }
                }

                break;

            case R.id.cad_id_local:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}