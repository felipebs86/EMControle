package br.com.fbscorp.emcontrole.helper;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import br.com.fbscorp.emcontrole.ActivityCadastro;
import br.com.fbscorp.emcontrole.R;
import br.com.fbscorp.emcontrole.dao.CadastroDAO;
import br.com.fbscorp.emcontrole.model.Cadastro;
import br.com.fbscorp.emcontrole.model.Medicamento;

public class CadastroHelper {
    private final EditText nome;
    private final EditText email;
    private final Spinner medicamento;
    private final TextView txtData;
    private final TextView txtHora;
    private final Switch lembrete;
    private final Spinner idLocal;

    public CadastroHelper(ActivityCadastro activity){
        nome = (EditText) activity.findViewById(R.id.cad_nome);
        email = (EditText) activity.findViewById(R.id.cad_email);
        medicamento = (Spinner) activity.findViewById(R.id.cad_medicamento);
        txtData = (TextView) activity.findViewById(R.id.cad_txt_data);
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
        cadastro.setDia(txtData.getText().toString().split("/")[0]);
        cadastro.setMes(txtData.getText().toString().split("/")[1]);
        cadastro.setAno(txtData.getText().toString().split("/")[2]);
        cadastro.setHora(txtHora.getText().toString().split(":")[0]);
        cadastro.setMinuto(txtHora.getText().toString().split(":")[1]);
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
        dao.close();
        nome.setText(cadastro.getNome());
        email.setText(cadastro.getEmail());
        medicamento.setSelection(cadastro.getMedicamento());
        txtData.setText(String.format("%s/%s/%s", cadastro.getDia(), cadastro.getMes(), cadastro.getAno()));
        txtHora.setText(String.format("%s:%s", cadastro.getHora(), cadastro.getMinuto()));
        if (cadastro.isLembrete().equalsIgnoreCase("true")){
            lembrete.setChecked(true);
        }
        idLocal.setSelection(cadastro.getIdLocal());
    }
}
