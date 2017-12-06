package br.com.fbscorp.emcontrole;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.fbscorp.emcontrole.dao.CadastroDAO;
import br.com.fbscorp.emcontrole.model.Cadastro;

/**
 * Created by felipebahiense on 04/12/17.
 */

public class BroadcastReceiverAux extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("EMControle", "Alarme");

        gerarNotificacao(context, new Intent(context, ActivitySplashScreen.class), "Alarme medicamento!", "EMControle", "Prepare-se para a aplicação do medicamento");
    }

    public void gerarNotificacao(Context context, Intent intent, CharSequence ticker, CharSequence titulo, CharSequence descricao){

        CadastroDAO dao = new CadastroDAO(context);
        Cadastro cadastro = dao.buscaCadastro();

        if(cadastro.isLembrete().equalsIgnoreCase("true")){
            final Calendar c = Calendar.getInstance();
            int ano = c.get(Calendar.YEAR);
            int mes = c.get(Calendar.MONTH);
            int dia = c.get(Calendar.DAY_OF_MONTH);
            int hora = c.get(Calendar.HOUR_OF_DAY);
            int minuto = c.get(Calendar.MINUTE);

            String data_sistema = String.valueOf(dia) + "/" + String.valueOf(mes + 1) + "/" + String.valueOf(ano);
            String hora_sistema = String.valueOf(hora) + ":" + String.valueOf(minuto);

            if (data_sistema.equals(cadastro.getData()) && hora_sistema.equals(cadastro.getHora())) {
                Log.d("EMControle", "Gerando nofiticação na tela!");

                NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                PendingIntent p = PendingIntent.getActivity(context, 0, intent, 0);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
                builder.setTicker(ticker);
                builder.setContentTitle(titulo);
                builder.setContentText(descricao);
                builder.setSmallIcon(R.drawable.emcontrole_logo);
                builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.emcontrole_logo));
                builder.setContentIntent(p);

                Notification n = builder.build();
                n.vibrate = new long[]{150, 300, 150, 600};
                n.flags = Notification.FLAG_AUTO_CANCEL;
                n.flags = Notification.FLAG_NO_CLEAR;
                nm.notify(R.drawable.emcontrole_logo, n);

                try{
                    Uri som = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                    Ringtone toque = RingtoneManager.getRingtone(context, som);
                    toque.play();
                }
                catch(Exception e){}

                String novaData = cadastro.getData();

                if (cadastro.getMedicamento() == 0) {
                    c.add(Calendar.DAY_OF_MONTH, 7);
                    novaData = String.valueOf(c.get(Calendar.DAY_OF_MONTH)) + "/" + String.valueOf(c.get(Calendar.MONTH) + 1) + "/" + String.valueOf(c.get(Calendar.YEAR));
                    if (cadastro.getIdLocal() < 4) {
                        cadastro.setIdLocal(cadastro.getIdLocal() + 1);
                    } else {
                        cadastro.setIdLocal(1);
                    }
                } else if (cadastro.getMedicamento() == 1) {
                    c.add(Calendar.DAY_OF_MONTH, 1);
                    novaData = String.valueOf(c.get(Calendar.DAY_OF_MONTH)) + "/" + String.valueOf(c.get(Calendar.MONTH) + 1) + "/" + String.valueOf(c.get(Calendar.YEAR));
                    if (cadastro.getIdLocal() < 30) {
                        cadastro.setIdLocal(cadastro.getIdLocal() + 1);
                    } else {
                        cadastro.setIdLocal(1);
                    }
                }

                cadastro.setData(novaData);

                dao.atualiza(cadastro);

                Log.d("EMControle", "Nova Data: " + novaData);
                Log.d("EMControle", "Novo Local: " + cadastro.getIdLocal());

            }
        }
    }
}
