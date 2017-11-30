package br.com.fbscorp.emcontrole;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcelable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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

        //CadastroDAO dao = new CadastroDAO(this);
        //cadastro = dao.buscaCadastro();
        Log.d("EMControle", "Iniciando aplicação");
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                vaiParaActivity(cadastro);
            }
        }, 2000);

    }

    private void vaiParaActivity(Cadastro cadastro) {
        if (cadastro != null){
            Log.d("EMControle", "Existe cadastro no banco");
            Intent intent = new Intent(ActivitySplashScreen.this, ActivityInicial.class);
            intent.putExtra("cadastro", cadastro);
            startActivity(intent);
        } else {
            Log.d("EMControle", "Nao existe cadastro no banco");
            Intent intent = new Intent(ActivitySplashScreen.this, ActivityCadastro.class);
            startActivity(intent);
        }
        finish();
    }

}
