package br.com.fbscorp.emcontrole;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by felipebahiense on 08/12/17.
 */

public class ReceptorBoot extends BroadcastReceiver {
    private Calendar c;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("EMControle", "Novo alarme");

        Intent intent1 = new Intent("ALARME_DISPARADO");
        PendingIntent p = PendingIntent.getBroadcast(context, 0, intent1, 0);

        c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        c.add(Calendar.SECOND, 3);

        AlarmManager alarme = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        alarme.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), p);
    }
}
