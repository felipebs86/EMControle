package br.com.fbscorp.emcontrole.helper;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.fbscorp.emcontrole.ActivityCadastro;
import br.com.fbscorp.emcontrole.R;
import br.com.fbscorp.emcontrole.model.Cadastro;

public class CadastroHelper {
    private final EditText nome;
    private final EditText email;
    private final Spinner medicamento;
    //private final Button btnData;
    private final EditText txtData;
    //private final Button btnAlarme;
    private final EditText txtHora;
    private final Switch lembrete;
    private final Spinner idLocal;

    public CadastroHelper(ActivityCadastro activity){
        nome = (EditText) activity.findViewById(R.id.cad_nome);
        email = (EditText) activity.findViewById(R.id.cad_email);
        medicamento = (Spinner) activity.findViewById(R.id.cad_medicamento);
        //btnData = (Button) activity.findViewById(R.id.cad_btn_data_inicio);
        txtData = (EditText) activity.findViewById(R.id.cad_txt_data);
        //btnAlarme = (Button) activity.findViewById(R.id.cad_btn_alarme);
        txtHora = (EditText) activity.findViewById(R.id.cad_txt_hora);
        lembrete = (Switch) activity.findViewById(R.id.cad_lembrete);
        idLocal = (Spinner) activity.findViewById(R.id.cad_id_local);
    }

    public Cadastro pegaCadastro() {
        Cadastro cadastro = new Cadastro();
        cadastro.setNome(nome.getText().toString());
        cadastro.setEmail(email.getText().toString());
        cadastro.setMedicamento(medicamento.getDropDownVerticalOffset());
        cadastro.setData(txtData.getText().toString());
        cadastro.setHora(txtHora.getText().toString());
        cadastro.setLembrete(lembrete.isChecked());//capturar booleano dizendo true ouf false
        cadastro.setIdLocal(idLocal.getDropDownVerticalOffset());
        return cadastro;

    }
}
