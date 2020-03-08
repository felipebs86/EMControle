package br.com.fbscorp.emcontrole;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;

import br.com.fbscorp.emcontrole.dao.CadastroDAO;
import br.com.fbscorp.emcontrole.model.Cadastro;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by felipebahiense on 08/12/17.
 */

public class ReceptorBoot extends BroadcastReceiver {
    private Calendar c;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("EMControle", "Novo alarme");

        CadastroDAO dao = new CadastroDAO(context);
        Cadastro cadastro = dao.buscaCadastro();

        Intent intent1 = new Intent("ALARME_DISPARADO").putExtra("cadastro", cadastro);;
        PendingIntent p = PendingIntent.getBroadcast(context, 0, intent1, 0);

        Calendar c = Calendar.getInstance();

        c.set(Integer.parseInt(cadastro.getAno()),
                Integer.parseInt(cadastro.getMes()) - 1,
                Integer.parseInt(cadastro.getDia()),
                Integer.parseInt(cadastro.getHora()),
                Integer.parseInt(cadastro.getMinuto()));

        c.clear(Calendar.SECOND);

        AlarmManager alarme = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        alarme.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), p);
    }
}
