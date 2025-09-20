package hr.horoskop.horoskop.controlor;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import hr.horoskop.horoskop.interfaces.DayHoroscopeLoaded;
import hr.horoskop.horoskop.interfaces.GeneralHoroscopeLoaded;
import hr.horoskop.horoskop.interfaces.LoveHoroscopeLoaded;
import hr.horoskop.horoskop.interfaces.MonthHoroscopeLoaded;
import hr.horoskop.horoskop.interfaces.WeekHoroscopeLoaded;
import hr.horoskop.horoskop.interfaces.YearHoroscopeLoaded;
import hr.horoskop.horoskop.model.HoroscopeFeed;
import hr.horoskop.horoskop.model.HoroscopeItem;
import hr.horoskop.horoskop.utils.AppConstants;
import hr.horoskop.horoskop.utils.AppController;

/**
 * Created by ZoPa on 23.4.2015..
 */
public class HoroscopeParser {

    private static HoroscopeFeed dayHoroscope = new HoroscopeFeed();
    private static HoroscopeFeed weekHoroscope = new HoroscopeFeed();
    private static HoroscopeFeed monthHoroscope = new HoroscopeFeed();
    private static HoroscopeFeed loveHoroscope = new HoroscopeFeed();
    private static HoroscopeFeed generalHoroscope = new HoroscopeFeed();
    private static HoroscopeFeed yearHoroscope = new HoroscopeFeed();


    public static DayHoroscopeLoaded dayListener;
    public static WeekHoroscopeLoaded weekListener;
    public static MonthHoroscopeLoaded monthListener;
    public static LoveHoroscopeLoaded loveListener;
    public static GeneralHoroscopeLoaded generalListener;
    public static YearHoroscopeLoaded yearHoroscopeListener;


    public void setListener(DayHoroscopeLoaded dayListener) {
        this.dayListener = dayListener;
    }
    public void setListener(WeekHoroscopeLoaded weekListener) {
        this.weekListener = weekListener;
    }
    public void setListener(MonthHoroscopeLoaded monthListener) { this.monthListener = monthListener;}
    public void setListener(LoveHoroscopeLoaded loveListener) { this.loveListener = loveListener;}
    public void setListener(GeneralHoroscopeLoaded generalListener) { this.generalListener = generalListener;}
    public void setListener(YearHoroscopeLoaded yearHoroscopeListener){ this.yearHoroscopeListener = yearHoroscopeListener;}


    public void getDayHoroscope() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://vladowsky.com/horoHR/?key=1504h&day=1", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("TAG", response);
                try {
                    JSONObject rootJsonObj = new JSONObject(response);
                    JSONArray jsonArray = rootJsonObj.getJSONArray(AppConstants.HOROSCOPE_DAY);
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String id = jsonObject.getString(AppConstants.KEY_ID);
                        String scopeId = jsonObject.getString(AppConstants.KEY_SCOPE_ID);
                        String horoscope = jsonObject.getString(AppConstants.KEY_HOROSCOPE);
                        String sign = jsonObject.getString(AppConstants.KEY_SIGN);

                        HoroscopeItem day = new HoroscopeItem(id, scopeId, horoscope, sign);
                        dayHoroscope.addItem(day);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            dayListener.onDayHoroscopeLoaded(dayHoroscope);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(stringRequest);
    }

    public HoroscopeFeed getDayHoroscopeForNotification() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://vladowsky.com/horoHR/?key=1504h&day=1", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("TAG", response);
                try {
                    JSONObject rootJsonObj = new JSONObject(response);
                    JSONArray jsonArray = rootJsonObj.getJSONArray(AppConstants.HOROSCOPE_DAY);
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String id = jsonObject.getString(AppConstants.KEY_ID);
                        String scopeId = jsonObject.getString(AppConstants.KEY_SCOPE_ID);
                        String horoscope = jsonObject.getString(AppConstants.KEY_HOROSCOPE);
                        String sign = jsonObject.getString(AppConstants.KEY_SIGN);

                        HoroscopeItem day = new HoroscopeItem(id, scopeId, horoscope, sign);
                        dayHoroscope.addItem(day);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                dayListener.onDayHoroscopeLoaded(dayHoroscope);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(stringRequest);

        return dayHoroscope;
    }

    public void getWeekHoroscope() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://vladowsky.com/horoHR/?key=1504h&week=1", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("TAG", response);
                try {
                    JSONObject rootJsonObj = new JSONObject(response);
                    JSONArray jsonArray = rootJsonObj.getJSONArray(AppConstants.HOROSCOPE_WEEK);
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String id = jsonObject.getString(AppConstants.KEY_ID);
                        String scopeId = jsonObject.getString(AppConstants.KEY_SCOPE_ID);
                        String horoscope = jsonObject.getString(AppConstants.KEY_HOROSCOPE);
                        String sign = jsonObject.getString(AppConstants.KEY_SIGN);

                        HoroscopeItem week = new HoroscopeItem(id, scopeId, horoscope, sign);
                        weekHoroscope.addItem(week);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                weekListener.onWeekHoroscopeLoaded(weekHoroscope);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(stringRequest);
    }

    public void getMonthHoroscope() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://vladowsky.com/horoHR/?key=1504h&month=1", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("TAG", response);
                try {
                    JSONObject rootJsonObj = new JSONObject(response);
                    JSONArray jsonArray = rootJsonObj.getJSONArray(AppConstants.HOROSCOPE_MONTH);
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String id = jsonObject.getString(AppConstants.KEY_ID);
                        String scopeId = jsonObject.getString(AppConstants.KEY_SCOPE_ID);
                        String horoscope = jsonObject.getString(AppConstants.KEY_HOROSCOPE);
                        String sign = jsonObject.getString(AppConstants.KEY_SIGN);

                        HoroscopeItem month = new HoroscopeItem(id, scopeId, horoscope, sign);
                        monthHoroscope.addItem(month);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                monthListener.onMonthHoroscopeLoaded(monthHoroscope);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(stringRequest);
    }

    public void getLoveHoroscope() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://vladowsky.com/horoHR/?key=1504h&love=1", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("TAG", response);
                try {
                    JSONObject rootJsonObj = new JSONObject(response);
                    JSONArray jsonArray = rootJsonObj.getJSONArray(AppConstants.HOROSCOPE_LOVE);
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String id = jsonObject.getString(AppConstants.KEY_ID);
                        String scopeId = jsonObject.getString(AppConstants.KEY_SCOPE_ID);
                        String horoscope = jsonObject.getString(AppConstants.KEY_HOROSCOPE);
                        String sign = jsonObject.getString(AppConstants.KEY_SIGN);

                        HoroscopeItem love = new HoroscopeItem(id, scopeId, horoscope, sign);
                        loveHoroscope.addItem(love);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                loveListener.onLoveHoroscopeLoaded(loveHoroscope);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(stringRequest);
    }

    public void getGeneralHoroscope() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://vladowsky.com/horoHR/?key=1504h&general=1", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("TAG", response);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray(AppConstants.HOROSCOPE_GENERAL);
                    for(int i=0; i<jsonArray.length();i++){

                        JSONObject jsonObj = jsonArray.getJSONObject(i);
                        String sign = jsonObj.getString(AppConstants.KEY_SIGN);
                        String txt = jsonObj.getString(AppConstants.KEY_TXT);
                        String startDate = jsonObj.getString(AppConstants.KEY_START_DATE);
                        String endDate = jsonObj.getString(AppConstants.KEY_END_DATE);
                        String featured = jsonObj.getString(AppConstants.KEY_FEATURED);

                        HoroscopeItem general = new HoroscopeItem(sign, txt, startDate, endDate, featured);
                        generalHoroscope.addItem(general);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                generalListener.onGeneralHoroscopeLoaded(generalHoroscope);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(stringRequest);
    }

    public void getYearHoroscope() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://vladowsky.com/horoHR/?key=1504h&year=1", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("TAG", response);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray(AppConstants.HOROSCOPE_YEAR);
                    for(int i=0; i<jsonArray.length();i++){

                        JSONObject jsonObj = jsonArray.getJSONObject(i);
                        String sign = jsonObj.getString(AppConstants.KEY_SIGN);
                        String horoscope = jsonObj.getString("horoscope");
                        //String txt = jsonObj.getString(AppConstants.KEY_TXT);
                       // String startDate = jsonObj.getString(AppConstants.KEY_START_DATE);
                       // String endDate = jsonObj.getString(AppConstants.KEY_END_DATE);
                       // String featured = jsonObj.getString(AppConstants.KEY_FEATURED);

                        HoroscopeItem year = new HoroscopeItem(horoscope, sign);
                        yearHoroscope.addItem(year);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                yearHoroscopeListener.onYearHoroscopeLoaded(yearHoroscope);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(stringRequest);
    }


}
