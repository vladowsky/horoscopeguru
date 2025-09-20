package hr.horoskop.horoskop.controlor;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import hr.horoskop.horoskop.model.PlanetFeed;
import hr.horoskop.horoskop.model.PlanetItem;
import hr.horoskop.horoskop.utils.AppController;
import hr.horoskop.horoskop.interfaces.PlanetLoaded;

/**
 * Created by Zoran on 24.7.2015..
 */
public class PlanetParser {

    private PlanetFeed planetFeed = new PlanetFeed();
    public static PlanetLoaded planetListener;

    public void setListener(PlanetLoaded planetListener) {
        this.planetListener = planetListener;
    }

    public void getPlanets(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://vladowsky.com/horoHR/current-planets/index.php?json=1", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("TAG", response);
                try {
                    JSONArray root = new JSONArray(response);
                    for(int i=0;i<root.length();i++){
                        JSONObject planetObj = root.getJSONObject(i);
                        String planet = planetObj.getString("planet_name");
                        String planet_position = planetObj.getString("planet_position");
                        String sign = planetObj.getString("sign");
                        PlanetItem planetItem = new PlanetItem(planet, planet_position, sign);
                        planetFeed.addItem(planetItem);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                planetListener.onPlanetLoaded(planetFeed);

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
