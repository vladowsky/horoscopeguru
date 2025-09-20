package hr.horoskop.horoskop.controlor;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import hr.horoskop.horoskop.model.Moon;
import hr.horoskop.horoskop.utils.AppConstants;
import hr.horoskop.horoskop.utils.AppController;
import hr.horoskop.horoskop.interfaces.MoonLoaded;

/**
 * Created by Zoran on 24.7.2015..
 */
public class MoonParser {

    private Moon moon;
    private MoonLoaded moonListener;

    public void setListener(MoonLoaded moonListener) {
        this.moonListener = moonListener;
    }


    public void getMoon(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://vladowsky.com/horoHR/moon-forecast/index.php?json=1", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("TAG", response);
                try {
                    JSONObject rootJsonObj = new JSONObject(response);
                    String signId = rootJsonObj.getString(AppConstants.MOON_ID);
                    String sign = rootJsonObj.getString(AppConstants.MOON_SIGN);
                    String desc = rootJsonObj.getString(AppConstants.MOON_DESC);

                    moon = new Moon(Integer.parseInt(signId), sign, desc);

                    moonListener.onMoonLoaded(moon);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


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
