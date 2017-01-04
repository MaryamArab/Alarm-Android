package odu.alarm;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.security.acl.NotOwnerException;
import java.util.GregorianCalendar;

public class Main extends ActionBarActivity {
    Button btnshow, btnStop, btnSet;
    NotificationManager notificationManager;

    Boolean isNotified = false;
    int notifiId = 33;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnshow = (Button)findViewById(R.id.btnStart);
        btnStop = (Button)findViewById(R.id.btnStop);
        btnSet = (Button)findViewById(R.id.btnSet);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void SetAlarm(View view) {
        //Long alertTime = new GregorianCalendar().getTimeInMillis()+5*1000;

        Long alertTime = System.currentTimeMillis()+5*1000;
        System.out.print("Current Time in milliseconds = ");
        System.out.println(System.currentTimeMillis());
        Intent alertIntent = new Intent(this, AlertReceiver.class);
        AlarmManager alarmManager =(AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP,alertTime, PendingIntent.getBroadcast(this,1,alertIntent,PendingIntent.FLAG_UPDATE_CURRENT ));
    }

    public void StopNotification(View view) {
        if(isNotified)
        {
            notificationManager.cancel(notifiId);
        }
    }

    public void StartNotification(View view) {
        NotificationCompat.Builder notification = new NotificationCompat.Builder(this).setContentTitle("message").setContentText("notified").setTicker("Alert new message");
        Intent moreInfoIntent = new Intent(this, MoreInfoNotification.class);

        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);
        taskStackBuilder.addParentStack(MoreInfoNotification.class);
        taskStackBuilder.addNextIntent(moreInfoIntent);
        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(0,PendingIntent.FLAG_CANCEL_CURRENT);

        notification.setContentIntent(pendingIntent);
        notificationManager =(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notifiId, notification.build());
        isNotified = true;

    }
}
