package hr.horoskop.horoskop.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Date;

/**
 * Created by zoran on 19.08.2015..
 */
public class CacheManager {

    private static String PREFS_NAME = "HOROSKOP_ASTRO_TAROT";
    private static int MODE_PRIVATE = 0;
    private static SharedPreferences.Editor mEditor;
    private static SharedPreferences mPrefs;

    public static void setDailyHoroscopeFeed(Context context, String horoscopeFeed){
        mEditor = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        mEditor.putString(AppConstants.HOROSCOPE_DAY, horoscopeFeed);
        mEditor.commit();
    }

    public static String getDailyHoroscopeFeed(Context context){
        mPrefs = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String horoscopeFeed = mPrefs.getString(AppConstants.HOROSCOPE_DAY, "");
        return horoscopeFeed;
    }


    public static void setDayHoroscopeDate(Context context, String date){
        mEditor = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        mEditor.putString(AppConstants.HOROSCOPE_DAY_DATE, date);
        mEditor.commit();
    }

    public static String getDayHoroscopeDate(Context context){
        mPrefs = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String date = mPrefs.getString(AppConstants.HOROSCOPE_DAY_DATE, null);
        return date;
    }

    public static void saveAppOpen(Context context, int openNumber){
        mEditor = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        mEditor.putInt(AppConstants.HOROSCOPE_LAUNCH, openNumber);
        mEditor.commit();
    }

    public static int getAppOpens(Context context){
        mPrefs = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int appOpens = mPrefs.getInt(AppConstants.HOROSCOPE_LAUNCH, 0);
        return appOpens;
    }

    public static void saveLoveHoroscope(Context context, Date date){
        mEditor = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        mEditor.putString(AppConstants.FORTUMO_LOVE, String.valueOf(date));
        mEditor.commit();
    }

    public static String getLoveHoroscope(Context context){
        mPrefs = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String love = mPrefs.getString(AppConstants.FORTUMO_LOVE, null);
        return love;
    }

    public static void saveMonthHoroscope(Context context, String date){
        mEditor = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        mEditor.putString(AppConstants.FORTUMO_MONTH, date);
        mEditor.commit();
    }

    public static String getMonthHoroscope(Context context){
        mPrefs = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String love = mPrefs.getString(AppConstants.FORTUMO_MONTH, null);
        return love;
    }

    public static void saveWeekHoroscope(Context context, Date date){
        mEditor = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        mEditor.putString(AppConstants.FORTUMO_WEEK, String.valueOf(date));
        mEditor.commit();
    }

    public static String getWeekHoroscope(Context context){
        mPrefs = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String love = mPrefs.getString(AppConstants.FORTUMO_WEEK, null);
        return love;
    }

    public static void saveYearHoroscope(Context context, String date){
        mEditor = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        mEditor.putString(AppConstants.FORTUMO_YEAR, String.valueOf(date));
        mEditor.commit();
    }

    public static String getYearHoroscope(Context context){
        mPrefs = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String year = mPrefs.getString(AppConstants.FORTUMO_YEAR, null);
        return year;
    }


}
