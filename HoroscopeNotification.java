package hr.horoskop.horoskop.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Zoran on 24.7.2015..
 */
public class HoroscopeNotification extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent arg1) {

            showNotification(context);



    }

    private void showNotification(Context context) {
        Intent service1 = new Intent(context, AlarmService.class);
        context.startService(service1);
    }
}
