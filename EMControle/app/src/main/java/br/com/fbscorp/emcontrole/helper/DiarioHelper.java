package br.com.fbscorp.emcontrole.helper;

import android.widget.EditText;
import android.widget.TextView;

import br.com.fbscorp.emcontrole.ActivityConteudoDiario;
import br.com.fbscorp.emcontrole.R;
import br.com.fbscorp.emcontrole.model.Diario;

/**
 * Created by felipe on 06/12/17.
 */

public class DiarioHelper {

    private final TextView cabecalho;
    private final EditText texto;

    public DiarioHelper(ActivityConteudoDiario activity) {
        cabecalho = (TextView) activity.findViewById(R.id.cabecalho_diario);
        texto = (EditText) activity.findViewById(R.id.conteudo_diario);
    }

    public Diario pegaDiario(){
        Diario diario = new Diario();
        diario.setData((String) cabecalho.getText());
        diario.setTexto(texto.getText().toString());
        return diario;
    }
}
