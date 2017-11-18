package br.com.fbscorp.emcontrole;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.zip.Inflater;

import br.com.fbscorp.emcontrole.helper.CadastroHelper;
import br.com.fbscorp.emcontrole.model.Cadastro;

public class ActivityCadastro extends AppCompatActivity implements View.OnClickListener{

    private CadastroHelper helper;
    private TextView txtData, txtHora;
    private Button btnData;
    private Button btnHora;
    private Button btnImagem;
    private int ano, mes, dia, hora, minuto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        helper = new CadastroHelper(this);

        txtData = (TextView) findViewById(R.id.cad_txt_data);
        txtHora = (TextView) findViewById(R.id.cad_txt_hora);
        btnData = (Button) findViewById(R.id.cad_btn_data_inicio);
        btnHora = (Button) findViewById(R.id.cad_btn_alarme);
        btnImagem = (Button) findViewById(R.id.cad_btn_locais);

        final Calendar cal = Calendar.getInstance();
        ano = cal.get(Calendar.YEAR);
        mes = cal.get(Calendar.MONTH);
        dia = cal.get(Calendar.DAY_OF_MONTH);
        hora = cal.get(Calendar.HOUR);
        minuto = cal.get(Calendar.MINUTE);

        btnData.setOnClickListener(this);
        btnHora.setOnClickListener(this);
        btnImagem.setOnClickListener(this);

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
                Toast.makeText(ActivityCadastro.this, cadastro.getNome() + ", seu cadastro foi salvo!", Toast.LENGTH_SHORT).show();
                //finish();
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

                            txtData.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

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
//            AlertDialog.Builder ImageDialog = new AlertDialog.Builder(ActivityCadastro.this);
//            ImageDialog.setTitle("Locais de Aplicação");
//            //ImageView showImage = new ImageView(ActivityCadastro.this);
//            LayoutInflater factory = LayoutInflater.from(ActivityCadastro.this);
//            final ImageView v = (ImageView) factory.inflate(R.layout.imagem_local, null);
//            v.setImageResource(R.drawable.person);
//            ImageDialog.setView(v);
//
//            ImageDialog.setNegativeButton("ok", new DialogInterface.OnClickListener()
//            {
//                public void onClick(DialogInterface arg0, int arg1)
//                {
//                }
//            });
//            ImageDialog.show();

            final Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.imagem_local);
            dialog.setTitle("Locais de aplicação");

            // set the custom dialog components - text, image and button
            //TextView text = (TextView) dialog.findViewById(R.id.text);
            //text.setText("Android custom dialog example!");
            ImageView image = (ImageView) dialog.findViewById(R.id.cad_imagem_local);
            image.setImageResource(R.drawable.person);

            FloatingActionButton dialogButton = (FloatingActionButton) dialog.findViewById(R.id.cad_imagem_fechar);
            // if button is clicked, close the custom dialog
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }

    }
}