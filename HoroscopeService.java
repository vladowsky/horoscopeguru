package hr.horoskop.horoskop.utils;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;
import android.text.format.DateFormat;
import android.util.Log;

import com.google.gson.Gson;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import hr.horoskop.horoskop.R;
import hr.horoskop.horoskop.activities.MainActivity;
import hr.horoskop.horoskop.controlor.HoroscopeParser;
import hr.horoskop.horoskop.interfaces.DayHoroscopeLoaded;
import hr.horoskop.horoskop.model.HoroscopeFeed;

/**
 * Created by zoran on 31.08.2015..
 */
public class HoroscopeService extends Service implements DayHoroscopeLoaded {

    private static final String ACTION_DOWNLOAD    = "actionDownload";

    private static final int    ID_HOROSCOPE_DOWNLOAD     = 1;
    private static final int    ID_HOROSCOPE_NOTIFICATION = 2;
    private HoroscopeFeed dayHoroscopeFeed;
    private int startId;




    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        this.startId = startId;
        getHoroscope();
        finishServiceAndReschedule(startId);

        return START_NOT_STICKY;
    }


    private void getHoroscope(){
        if(Utils.isConnected(getApplicationContext())){
            HoroscopeParser parser = new HoroscopeParser();
            parser.getDayHoroscope();
            parser.setListener(this);
        } else{
            showUserWaitingHoroscopeNotification(getApplicationContext());
        }
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void finishServiceAndReschedule(int startId)
    {
        enableAlarmManager(this);
        stopSelf(startId);
    }


    /**
     * Show user waiting horoscope notification to user
     *
     */
    private void showUserWaitingHoroscopeNotification(Context context)
    {


        Intent mIntent = new Intent(this, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                new Intent(mIntent),PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_ONE_SHOT);

        Notification notification = new NotificationCompat.Builder(HoroscopeService.this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("Horoskop")
                .setContentText("Pročitajte današnji horoskop")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();

        NotificationManagerCompat manager = NotificationManagerCompat.from(this);
        manager.notify(ID_HOROSCOPE_NOTIFICATION, notification);
    }


    /**
     * Returns future time in milliseconds when event will happen (based on currentTimeMillis).
     *
     */
    private static long calculateFutureTime(Context context)
    {
        //long now = System.currentTimeMillis();
        Calendar now = Calendar.getInstance();


        Calendar calendar = Calendar.getInstance();
        //calendar.setTimeInMillis(now);
        calendar.set(Calendar.HOUR_OF_DAY, 9);
        calendar.set(Calendar.MINUTE,      0);
        calendar.set(Calendar.SECOND,      0);
        calendar.set(Calendar.MILLISECOND, 0);
        if(calendar.getTime().after(now.getTime()))
        {
            return calendar.getTimeInMillis();
        } else{
            calendar.add(Calendar.MILLISECOND, (int) TimeUnit.HOURS.toMillis(24));
            return calendar.getTimeInMillis();
        }


    }

    /**
     * Does the system hold a PendingIntent for our event?
     *
     */
    public static boolean isAlarmSet(Context context)
    {
        return (getHoroscopeIntent(context, PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_ONE_SHOT) != null);
    }

    /**
     * Setup Alarm with AlarmManager for next event.
     *
     */
    public static void enableAlarmManager(Context context){


    AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);


            long futureTime = calculateFutureTime(context);
            PendingIntent pendingIntent = getHoroscopeIntent(context, PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_ONE_SHOT);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                alarmManager.setExact(AlarmManager.RTC, futureTime, pendingIntent);
            } else {
                alarmManager.set(AlarmManager.RTC, futureTime, pendingIntent);
            }
            Log.e("TAG", "enableAlarmManager: " +
                    DateFormat.getDateFormat(context).format(futureTime) + " " +
                    DateFormat.getTimeFormat(context).format(futureTime));


    }

    /**
     * Returns a PendingIntent prepared by the specified parameters.
     *
     */
    @Nullable
    public static PendingIntent getHoroscopeIntent(Context context, int pendingIntentFlags)
    {
        Intent intent = new Intent(context, HoroscopeService.class).setAction("notification1");
        return PendingIntent.getService(context, ID_HOROSCOPE_NOTIFICATION,	intent, pendingIntentFlags);
    }


    /**
     * Cancels waiting Alarm held by AlarmManager
     *
     */
    public static void cancelAlarmManager(Context context)
    {
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = getHoroscopeIntent(context,
                PendingIntent.FLAG_NO_CREATE);
        if(pendingIntent != null){
            alarmManager.cancel(pendingIntent);
            pendingIntent.cancel();
        }
    }

    @Override
    public void onDayHoroscopeLoaded(HoroscopeFeed dayHoroscopeFeed) {
        this.dayHoroscopeFeed = dayHoroscopeFeed;
        Gson gson = new Gson();
        String horoscope = gson.toJson(dayHoroscopeFeed);
        CacheManager.setDailyHoroscopeFeed(getApplicationContext(), horoscope);
        CacheManager.setDayHoroscopeDate(getApplicationContext(), DateManager.getCurrentDate());

        showUserWaitingHoroscopeNotification(getApplicationContext());
    }


    /**
     * Receives Intent once the device starts up.
     *
     * If daily updates are enabled we reset our AlarmManager notice for the next event.
     *
     */
    public static class BootReceiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            if(intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)){
                    HoroscopeService.enableAlarmManager(context);
            }
        }
    }
}
