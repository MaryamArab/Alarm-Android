package odu.alarm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Created by marab on 10/9/2015.
 */
public class AlertReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        createNotification(context, "Times up","text", "alert");
    }

    private void createNotification(Context context, String msg, String msgText, String msgAlert)
    {
        PendingIntent notificIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, Main.class),0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context).setContentTitle(msg)
                .setTicker(msgAlert)
                .setContentText(msgText);

        builder.setContentIntent(notificIntent);
        builder.setDefaults(NotificationCompat.DEFAULT_SOUND);
        builder.setAutoCancel(true);
        NotificationManager mNotificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, builder.build());

    }
}
