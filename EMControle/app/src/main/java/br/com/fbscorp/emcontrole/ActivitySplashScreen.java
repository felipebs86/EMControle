package br.com.fbscorp.emcontrole;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import java.util.List;

import br.com.fbscorp.emcontrole.dao.CadastroDAO;
import br.com.fbscorp.emcontrole.dao.MedicamentoDAO;
import br.com.fbscorp.emcontrole.model.Cadastro;
import br.com.fbscorp.emcontrole.model.Medicamento;


public class ActivitySplashScreen extends AppCompatActivity {

    private Cadastro cadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        MedicamentoDAO medicamentoDAO = new MedicamentoDAO(this);
        final List<Medicamento> medicamentos = medicamentoDAO.getMedicamentos();
        CadastroDAO dao = new CadastroDAO(this);
        cadastro = dao.buscaCadastro();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                vaiParaActivity(cadastro, medicamentos);
            }
        }, 2000);

    }

    private void vaiParaActivity(Cadastro cadastro, List<Medicamento> medicamentos) {
        if (cadastro != null){
            Intent intent = new Intent(ActivitySplashScreen.this, ActivityInicial.class);
            intent.putExtra("cadastro", cadastro);
            intent.putExtra("medicamentos", (Parcelable) medicamentos);
            startActivity(intent);
        } else {
            Intent intent = new Intent(ActivitySplashScreen.this, ActivityCadastro.class);
            startActivity(intent);
        }
        finish();
    }

}
