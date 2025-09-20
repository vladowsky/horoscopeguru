package hr.horoskop.horoskop.utils;

import hr.horoskop.horoskop.R;

/**
 * Created by ZoPa on 5.4.2015..
 */
public class AppConstants {

    //Shared prefrences stuff
    public static final String KEY_PREFS_NAME = "hr.horoskop.horoskop";
    public static final String KEY_DAY_DATE = "day_date";
    public static final String KEY_LOVE_DATE = "love_date";
    public static final String KEY_WEEK_DATE = "week_date";
    public static final String KEY_MONTH_DATE = "month_date";
    public static final String KEY_DAY_VALUES = "day_values";
    public static final String KEY_LOVE_VALUES = "love_values";
    public static final String KEY_WEEK_VALUES = "week_values";
    public static final String KEY_MONTH_VALUES = "month_values";


    public static final String KEY_ID = "id";
    public static final String KEY_SCOPE_ID = "scopeid";
    public static final String KEY_HOROSCOPE = "horoscope";
    public static final String KEY_SIGN = "sign";
    public static final String KEY_TXT = "txt";
    public static final String KEY_START_DATE = "start_date";
    public static final String KEY_END_DATE = "end_date";
    public static final String KEY_FEATURED = "featured";

    //TYPES
    public static final String HOROSCOPE_DAY = "day";
    public static final String HOROSCOPE_WEEK = "week";
    public static final String HOROSCOPE_MONTH = "month";
    public static final String HOROSCOPE_LOVE = "love";
    public static final String HOROSCOPE_GENERAL = "general";
    public static final String HOROSCOPE_YEAR = "year";
    public static final String HOROSCOPE_DAY_DATE = "day_date";

    //Images
    public static final Integer[] IMAGES = { R.drawable.ic_ovan, R.drawable.ic_bik, R.drawable.ic_blizanci, R.drawable.ic_rak, R.drawable.ic_lav, R.drawable.ic_djevica,  R.drawable.ic_vaga, R.drawable.ic_skorpija, R.drawable.ic_strelac,  R.drawable.ic_jarac, R.drawable.ic_vodolija, R.drawable.ic_ribe};

    //Fortumo
    public static String SERVICE_ID = "1bb6a931e5974f1c60556879ccb976d8";
    public static String APP_SECRET = "692d6ae7806f894be835f1ed6e9e26b1";

    //Moon
    public static final String MOON_ID = "sign_id";
    public static String MOON_SIGN = "sign";
    public static final String MOON_DESC = "description";

    //Cache for Fortumo buy
    public static final String FORTUMO_LOVE = "fortumo_love";
    public static final String FORTUMO_WEEK = "fortumo_week";
    public static final String FORTUMO_MONTH = "fortumo_month";
    public static final String FORTUMO_YEAR = "fortumo_year";

    //Launch counter
    public static final String HOROSCOPE_LAUNCH = "launch_counter";
    public static final int ALARM_HOUR = 15;
    public static final int ALARM_MINUTES = 23;


    //Constants for horoscope type
    public static final int TYPE_DAY = 1;
    public static final int TYPE_LOVE = 2;
    public static final int TYPE_WEEK = 3;
    public static final int TYPE_MONTH = 4;
    public static final int TYPE_YEAR = 5;
    public static final int TYPE_GENERAL = 6;

    //LoveCalculator table contatnts
    public static final String LOVE_ID = "id";
    public static final String LOVE_CATEGORY = "category";
    public static final String LOVE_TEXT = "text";
    public static final String LOVE_TABLE = "love";

    public static final String DATABASE_NAME = "HoroskopGuruDB";
    public static final int DATABASE_VERSION = 1;


    //SHORT NAMES
    public static final String SHORT_ARIES = "ari";
    public static final String SHORT_TAURUS = "tau";
    public static final String SHORT_GEMINI= "gem";
    public static final String SHORT_CANCER = "can";
    public static final String SHORT_LEO = "leo";
    public static final String SHORT_VIRGO = "vir";
    public static final String SHORT_LIBRA = "lib";
    public static final String SHORT_SCORPIO = "sco";
    public static final String SHORT_SAGITTARIUS = "sag";
    public static final String SHORT_CAPRICORN = "cap";
    public static final String SHORT_AQUARIUS = "aqu";
    public static final String SHORT_PISCES = "pis";


}
