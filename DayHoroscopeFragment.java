package hr.horoskop.horoskop.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import hr.horoskop.horoskop.R;
import hr.horoskop.horoskop.controlor.HoroscopeParser;
import hr.horoskop.horoskop.interfaces.DayHoroscopeLoaded;
import hr.horoskop.horoskop.model.HoroscopeFeed;
import hr.horoskop.horoskop.model.HoroscopeItem;
import hr.horoskop.horoskop.utils.AppConstants;
import hr.horoskop.horoskop.utils.CacheManager;
import hr.horoskop.horoskop.utils.CustomProgress;
import hr.horoskop.horoskop.utils.DateManager;
import hr.horoskop.horoskop.utils.Utils;


/**
 * A simple {@link Fragment} subclass.
 */
public class DayHoroscopeFragment extends Fragment implements DayHoroscopeLoaded {


    private int horoscopeId;
    private ArrayList<Integer> randList = new ArrayList<Integer>();
    private static HoroscopeFeed dayHoroscope = new HoroscopeFeed();


    //UI
    private TextView tvDay, tvSign, tvDuration, tvDate;
    private ImageView ivSign;
    private ScrollView root;
    private CustomProgress pbLove, pbJob, pbHappiness, pbHealth;
    private RelativeLayout rlInternet;



    public DayHoroscopeFragment() {
        // Required empty public constructor
    }

    public DayHoroscopeFragment init(int position) {
        Log.d("TAG","on daily horoscpe fragment init");
        Log.d("TAG", String.valueOf(position));

        DayHoroscopeFragment fragment = new DayHoroscopeFragment();
        // Supply val input as an argument.
        Bundle args = new Bundle();
        args.putInt("position", position);
        fragment.setArguments(args);


        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tab_day,container,false);

        initUI(v);

            horoscopeId = getArguments().getInt("position");

            if(CacheManager.getDayHoroscopeDate(getActivity()) != null){

                if(CacheManager.getDayHoroscopeDate(getActivity()).equals(DateManager.getCurrentDate())){
                    getDayHoroscopeFromCache();
                } else{
                   getDayHoroscope();
                }

            } else{
                getDayHoroscope();
            }




        return v;
    }

    private HoroscopeFeed parseSavedHoroscope(String savedHoroscope) {
        try {
            JSONObject rootJsonObj = new JSONObject(savedHoroscope);
            JSONArray jsonArray = rootJsonObj.getJSONArray("_itemlist");
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

        return dayHoroscope;
    }

    private void getDayHoroscopeFromCache(){
        generateRandomValues();
        String savedHoroscope = CacheManager.getDailyHoroscopeFeed(getActivity());
        HoroscopeFeed dayHoroscopeFeed = parseSavedHoroscope(savedHoroscope);
        setHoroscope(dayHoroscopeFeed);
    }

    private void getDayHoroscope(){
        if(Utils.isConnected(getActivity())) {
            generateRandomValues();
            HoroscopeParser day = new HoroscopeParser();
            day.setListener(this);
            day.getDayHoroscope();
        }  else{
            root.setVisibility(View.INVISIBLE);
            rlInternet.setVisibility(View.VISIBLE);
        }
    }

    private void initUI(View v){

        root = (ScrollView) v.findViewById(R.id.sv_day);
        root.setVisibility(View.INVISIBLE);
        rlInternet = (RelativeLayout) v.findViewById(R.id.rl_internet);

        tvDay = (TextView) v.findViewById(R.id.tv_day_horoscope_text);
        tvSign = (TextView) v.findViewById(R.id.tv_day_horoscope_sign);
        tvDate = (TextView) v.findViewById(R.id.tv_day_horoscope_date);
        ivSign = (ImageView) v.findViewById(R.id.iv_sign_details);

        pbLove = (CustomProgress) v.findViewById(R.id.pb_day_love);
        pbLove.setProgressColor(getResources().getColor(R.color.orange));

        pbJob = (CustomProgress) v.findViewById(R.id.pb_day_job);
        pbJob.setProgressColor(getResources().getColor(R.color.purple));

        pbHappiness = (CustomProgress) v.findViewById(R.id.pb_day_happiness);
        pbHappiness.setProgressColor(getResources().getColor(R.color.blue));

        pbHealth = (CustomProgress) v.findViewById(R.id.pb_day_health);
        pbHealth.setProgressColor(getResources().getColor(R.color.green));

    }

    private void generateRandomValues() {
        for(int i=0;i<4;i++){
            randList.add(Utils.randInt(DateManager.getTodayDateForSliders(), i, horoscopeId, AppConstants.TYPE_DAY));
        }

        setValues();
    }

    private void setValues() {
        float love = randList.get(0);
        float job = randList.get(1);
        float happiness = randList.get(2);
        float health = randList.get(3);
        pbLove.setMaximumPercentage(love/100);
        pbJob.setMaximumPercentage(job/100);
        pbHappiness.setMaximumPercentage(happiness/100);
        pbHealth.setMaximumPercentage(health / 100);

    }


    @Override
    public void onDayHoroscopeLoaded(HoroscopeFeed dayHoroscopeFeed) {
        Gson gson = new Gson();
        String horoscope = gson.toJson(dayHoroscopeFeed);
        CacheManager.setDailyHoroscopeFeed(getActivity(), horoscope);
        CacheManager.setDayHoroscopeDate(getActivity(), DateManager.getCurrentDate());

        setHoroscope(dayHoroscopeFeed);

    }

    private void setHoroscope(HoroscopeFeed dayHoroscopeFeed){
        root.setVisibility(View.VISIBLE);
        tvDay.setText(dayHoroscopeFeed.getItem(horoscopeId).getHoroscope());
        tvSign.setText(dayHoroscopeFeed.getItem(horoscopeId).getSign());
        tvSign.setText("Dnevni horoskop - " + DateManager.getCurrentDate());
        tvDate.setText("Ovan");
        ivSign.setImageResource(AppConstants.IMAGES[horoscopeId]);
    }
}
