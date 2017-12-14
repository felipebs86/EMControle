package br.com.fbscorp.emcontrole.helper;

import android.content.Context;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.fbscorp.emcontrole.ActivityCadastro;
import br.com.fbscorp.emcontrole.R;
import br.com.fbscorp.emcontrole.dao.CadastroDAO;
import br.com.fbscorp.emcontrole.model.Cadastro;
import br.com.fbscorp.emcontrole.model.Medicamento;

public class CadastroHelper {
    private final EditText nome;
    private final EditText email;
    private final Spinner medicamento;
    //private final Button btnData;
    private final TextView txtData;
    //private final Button btnAlarme;
    private final TextView txtHora;
    private final Switch lembrete;
    private final Spinner idLocal;

    public CadastroHelper(ActivityCadastro activity){
        nome = (EditText) activity.findViewById(R.id.cad_nome);
        email = (EditText) activity.findViewById(R.id.cad_email);
        medicamento = (Spinner) activity.findViewById(R.id.cad_medicamento);
        //btnData = (Button) activity.findViewById(R.id.cad_btn_data_inicio);
        txtData = (TextView) activity.findViewById(R.id.cad_txt_data);
        //btnAlarme = (Button) activity.findViewById(R.id.cad_btn_alarme);
        txtHora = (TextView) activity.findViewById(R.id.cad_txt_hora);
        lembrete = (Switch) activity.findViewById(R.id.cad_lembrete);
        idLocal = (Spinner) activity.findViewById(R.id.cad_id_local);
    }

    public Cadastro pegaCadastro() {
        Cadastro cadastro = new Cadastro();
        cadastro.setNome(nome.getText().toString());
        cadastro.setEmail(email.getText().toString());
        Medicamento med  = (Medicamento) medicamento.getSelectedItem();
        Log.d("EMControle - " + this.getClass(), med.getNome());
        cadastro.setMedicamento(medicamento.getSelectedItemPosition());
        Log.d("EMControle - " + this.getClass(), String.valueOf(medicamento.getSelectedItemPosition()));
        cadastro.setData(txtData.getText().toString());
        cadastro.setHora(txtHora.getText().toString());
        if (lembrete.isChecked()){
            cadastro.setLembrete("true");
        } else{
            cadastro.setLembrete("false");
        }
        cadastro.setIdLocal(idLocal.getSelectedItemPosition());
        Log.d("EMControle - " + this.getClass(), String.valueOf(idLocal.getSelectedItemPosition()));
        return cadastro;

    }

    public void populaCadastro(Context context){
        CadastroDAO dao = new CadastroDAO(context);
        Cadastro cadastro = dao.buscaCadastro();
        nome.setText(cadastro.getNome());
        email.setText(cadastro.getEmail());
        medicamento.setSelection(cadastro.getMedicamento());
        txtData.setText(cadastro.getData());
        txtHora.setText(cadastro.getHora());
        if (cadastro.isLembrete().equalsIgnoreCase("true")){
            lembrete.setChecked(true);
        }
        idLocal.setSelection(cadastro.getIdLocal());
    }

    public boolean existeCadastro(Context context){
        CadastroDAO dao = new CadastroDAO(context);
        return dao.existeCadastro();
    }
}
