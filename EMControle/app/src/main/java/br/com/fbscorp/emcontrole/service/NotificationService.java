package br.com.fbscorp.emcontrole.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;

import br.com.fbscorp.emcontrole.model.Cadastro;

import static android.content.Context.ALARM_SERVICE;

public class NotificationService {
    public void configuraAlarme(Cadastro cadastro, Context context) {

        if (cadastro.isLembrete().equalsIgnoreCase("true")) {

            AlarmManager alarme = (AlarmManager) context.getSystemService(ALARM_SERVICE);

            boolean alarmeInativo = (PendingIntent.getBroadcast(context, 0, new Intent("ALARME_DISPARADO"), PendingIntent.FLAG_NO_CREATE) == null);

            if (!alarmeInativo) {
                Log.i("EMControle", "Ja existe alarme ativo, desativando o anterior");
                alarme.cancel(PendingIntent.getBroadcast(context, 0, new Intent("ALARME_DISPARADO"), PendingIntent.FLAG_NO_CREATE));
            }

            Log.i("EMControle", "Configurando novo alarme");

            Intent intent1 = new Intent("ALARME_DISPARADO").putExtra("cadastro", cadastro);
            PendingIntent p = PendingIntent.getBroadcast(context, 0, intent1, 0);

            Calendar c = Calendar.getInstance();

            c.set(Integer.parseInt(cadastro.getAno()),
                    Integer.parseInt(cadastro.getMes()) - 1,
                    Integer.parseInt(cadastro.getDia()),
                    Integer.parseInt(cadastro.getHora()),
                    Integer.parseInt(cadastro.getMinuto()));

            c.clear(Calendar.SECOND);

            alarme.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), p);
            Log.i("EMControle", "Novo alarme configurado - " + c.getTimeInMillis());
        }
    }
}
