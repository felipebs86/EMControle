package br.com.fbscorp.emcontrole;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import br.com.fbscorp.emcontrole.dao.CadastroDAO;
import br.com.fbscorp.emcontrole.model.Cadastro;

/**
 * Created by felipebahiense on 04/12/17.
 */

public class BroadcastReceiverAux extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("EMControle", "Alarme recebido");

        gerarNotificacao(context, new Intent(context, ActivitySplashScreen.class), "Alarme medicamento!", "EMControle", "Prepare-se para a aplicação do medicamento");
    }

    public void gerarNotificacao(Context context, Intent intent, CharSequence ticker, CharSequence titulo, CharSequence descricao){

        Log.d("EMControle", "Gerando notificaçao para tela");

        CadastroDAO dao = new CadastroDAO(context);
        Cadastro cadastro = dao.buscaCadastro();
        dao.close();

        if(cadastro.isLembrete().equalsIgnoreCase("true")){
            Log.d("EMControle", "Gerando nofiticação na tela!");

            NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            PendingIntent p = PendingIntent.getActivity(context, 0, intent, 0);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "123");
            builder.setTicker(ticker);
            builder.setContentTitle(titulo);
            builder.setContentText(descricao);
            builder.setAutoCancel(false);
            builder.setVisibility(Notification.VISIBILITY_PUBLIC);
            builder.setSmallIcon(R.drawable.emcontrole_logo);
            builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.emcontrole_logo));
            builder.setContentIntent(p);

            Notification n = builder.build();
            n.vibrate = new long[]{500};
            n.flags = Notification.FLAG_NO_CLEAR;
            nm.notify(123, n);
        }
    }
}
