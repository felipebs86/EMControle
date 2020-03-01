package br.com.fbscorp.emcontrole;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import br.com.fbscorp.emcontrole.dao.CadastroDAO;
import br.com.fbscorp.emcontrole.model.Cadastro;


public class ActivitySplashScreen extends AppCompatActivity {

    private Cadastro cadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        CadastroDAO dao = new CadastroDAO(this);
        cadastro = dao.buscaCadastro();
        Log.d("EMControle", "Iniciando aplicação");
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                vaiParaActivity(cadastro);
            }
        }, 500);

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
