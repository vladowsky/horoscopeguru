package hr.horoskop.horoskop.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Zoran on 8.6.2015..
 */
public class DateManager {

    public static String getCurrentDate(){
        Calendar c = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("dd MM yyyy");
        String formattedDate = df.format(c.getTime());
        return formattedDate;
    }

    public static String getCurrentYear() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        String formattedDate = df.format(c.getTime());
        return formattedDate;
    }

    public static String getCurrentWeek(){
        Calendar calen = Calendar.getInstance();
        int week = calen.get(Calendar.WEEK_OF_YEAR);
        int year = calen.get(Calendar.YEAR);
        calen.set(Calendar.YEAR, year);
        calen.set(Calendar.WEEK_OF_YEAR, week);
        calen.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calen.setFirstDayOfWeek(Calendar.MONDAY);

        SimpleDateFormat df = new SimpleDateFormat("dd MM");
        String firstDayOfWeek = df.format(calen.getTime());

        int currentDay = calen.get(Calendar.DAY_OF_WEEK);
        int leftDays= Calendar.SUNDAY - currentDay;
        calen.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);


        String lastDayOfWeek = df.format(calen.getTime());

        String finalWeek = firstDayOfWeek + " - "+lastDayOfWeek;

        return finalWeek;
    }

    public static String getCurrentMonth(){
        Calendar c = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("MM yyyy");
        String month = df.format(c.getTime());


        return month;
    }

    public static Date getLastDayOfWeek(){
        Calendar cal = Calendar.getInstance();
        int week = cal.get(Calendar.WEEK_OF_YEAR);
        int year = cal.get(Calendar.YEAR);
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR, week);
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        Date date = cal.getTime();
        return date;
    }

    public static Date getTodayEndDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        Date date = calendar.getTime();
        return date;
    }

    public static long getTodayDateForSliders(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("ddMMyyyy");
        String date = df.format(c.getTime());
        return Long.parseLong(date);
    }

    public static long getLastDayOfWeekForSliders(){
        Calendar c = Calendar.getInstance();
        int week = c.get(Calendar.WEEK_OF_YEAR);
        int year = c.get(Calendar.YEAR);
        return Long.parseLong(String.valueOf(week+year-30));
    }

    public static long getMonthForSliders(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("MMyyyy");
        String date = df.format(c.getTime());
        return Long.parseLong(date);
    }

    public static long getYearForSliders(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        String date = df.format(c.getTime());
        return Long.parseLong(date);
    }
}

