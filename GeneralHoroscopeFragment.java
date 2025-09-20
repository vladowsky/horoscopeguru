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

import java.util.ArrayList;

import hr.horoskop.horoskop.R;
import hr.horoskop.horoskop.controlor.HoroscopeParser;
import hr.horoskop.horoskop.model.HoroscopeFeed;
import hr.horoskop.horoskop.utils.AppConstants;
import hr.horoskop.horoskop.utils.CustomProgress;
import hr.horoskop.horoskop.interfaces.GeneralHoroscopeLoaded;
import hr.horoskop.horoskop.utils.Utils;


/**
 * A simple {@link Fragment} subclass.
 */
public class GeneralHoroscopeFragment extends Fragment implements GeneralHoroscopeLoaded {


    private TextView tvGeneral;
    private ImageView ivSign;
    private int horoscopeId;
    private CustomProgress pbLove, pbJob, pbHappiness, pbHealth;
    private RelativeLayout rlInternet;
    private ScrollView root;
    private ArrayList<Integer> list = new ArrayList<Integer>();


    public GeneralHoroscopeFragment() {
        // Required empty public constructor
    }

    public GeneralHoroscopeFragment init(int position) {
        Log.d("TAG", "on month horoscpe fragment init");
        Log.d("TAG", String.valueOf(position));
        horoscopeId = position;


        GeneralHoroscopeFragment fragment = new GeneralHoroscopeFragment();
        // Supply val input as an argument.
        Bundle args = new Bundle();
        args.putInt("position", position);
        fragment.setArguments(args);


        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tab_general,container,false);

        initUI(v);

        horoscopeId  = getArguments().getInt("position");

        if(Utils.isConnected(getActivity())) {
            HoroscopeParser general = new HoroscopeParser();
            general.setListener(this);
            general.getGeneralHoroscope();

            generateRandomValues();
        } else{
            root.setVisibility(View.INVISIBLE);
            rlInternet.setVisibility(View.VISIBLE);
        }

        return v;
    }

    @Override
    public void onGeneralHoroscopeLoaded(HoroscopeFeed generalHoroscopeFeed) {
        root.setVisibility(View.VISIBLE);
        tvGeneral.setText(generalHoroscopeFeed.getItem(horoscopeId).getTxt());
        ivSign.setImageResource(AppConstants.IMAGES[horoscopeId]);
    }

    private void initUI(View v){
        root = (ScrollView) v.findViewById(R.id.sc_general);
        root.setVisibility(View.INVISIBLE);

        rlInternet = (RelativeLayout) v.findViewById(R.id.rl_internet);

        tvGeneral = (TextView) v.findViewById(R.id.tv_general_horoscope_text);
        ivSign = (ImageView) v.findViewById(R.id.iv_sign_details);

        pbLove = (CustomProgress) v.findViewById(R.id.pb_love);
        pbLove.setProgressColor(getResources().getColor(R.color.orange));

        pbJob = (CustomProgress) v.findViewById(R.id.pb_job);
        pbJob.setProgressColor(getResources().getColor(R.color.purple));

        pbHappiness = (CustomProgress) v.findViewById(R.id.pb_happiness);
        pbHappiness.setProgressColor(getResources().getColor(R.color.blue));

        pbHealth = (CustomProgress) v.findViewById(R.id.pb_health);
        pbHealth.setProgressColor(getResources().getColor(R.color.green));

    }

    private void generateRandomValues() {
        for(int i=0;i<4;i++){
            list.add(Utils.generalRandInt( i, horoscopeId));
        }

        setValues();
    }

    private void setValues() {
        float love = list.get(0);
        float job = list.get(1);
        float happiness = list.get(2);
        float health = list.get(3);
        pbLove.setMaximumPercentage(love / 100);
        pbJob.setMaximumPercentage(job / 100);
        pbHappiness.setMaximumPercentage(happiness / 100);
        pbHealth.setMaximumPercentage(health / 100);

    }
}
