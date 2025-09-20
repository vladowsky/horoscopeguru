package hr.horoskop.horoskop.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;

import java.util.Random;

/**
 * Created by Zoran on 22.5.2015..
 */
public class Utils {

    //SharedPreferences stuff
    private static SharedPreferences mPrefs;
    private static SharedPreferences.Editor editor;



    public static int randInt(long date, int pos, int signNumber, int type) {
        Random rand = new Random(date+pos+signNumber+type);
        int randomNum = rand.nextInt((100));
        return randomNum;
    }

    public static int generalRandInt(int pos, int signNumber) {
        Random rand = new Random(pos + signNumber);
        int randomNum = rand.nextInt((100));
        return randomNum;
    }

    public static int randIntForLoveCalculator(int percentage) {

        int min = 0;
        int max = 100;

        if(percentage <= 20){
            min = 0;
        } else if(percentage >= 80){
            max = 100;
        } else{
            min = percentage - 20;
            max = percentage + 20;
        }

        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }


}
