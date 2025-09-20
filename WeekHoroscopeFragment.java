package hr.horoskop.horoskop.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

import hr.horoskop.horoskop.R;
import hr.horoskop.horoskop.controlor.HoroscopeParser;
import hr.horoskop.horoskop.model.HoroscopeFeed;
import hr.horoskop.horoskop.utils.AppConstants;
import hr.horoskop.horoskop.utils.CacheManager;
import hr.horoskop.horoskop.utils.CustomProgress;
import hr.horoskop.horoskop.utils.DateManager;
import hr.horoskop.horoskop.utils.Utils;
import hr.horoskop.horoskop.interfaces.WeekHoroscopeLoaded;
import mp.MpUtils;
import mp.PaymentRequest;
import mp.PaymentResponse;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeekHoroscopeFragment extends Fragment implements WeekHoroscopeLoaded {

    private TextView tvWeek;
    private int horoscopeId;

    private ArrayList<Integer> list = new ArrayList<Integer>();

    //UI
    private TextView tvSign, tvDate;
    private ImageView ivSign;
    private ScrollView root;
    private Button btnWeek;
    private CustomProgress pbLove, pbJob, pbHappiness, pbHealth;
    private RelativeLayout rlInternet;




    public WeekHoroscopeFragment() {
        // Required empty public constructor
    }

    public WeekHoroscopeFragment init(int position) {
        Log.d("TAG", "on weekly horoscpe fragment init");
        Log.d("TAG", String.valueOf(position));
        horoscopeId = position;


        WeekHoroscopeFragment fragment = new WeekHoroscopeFragment();
        // Supply val input as an argument.
        Bundle args = new Bundle();
        args.putInt("position", position);
        fragment.setArguments(args);


        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tab_week,container,false);


        horoscopeId  = getArguments().getInt("position");

        initUI(v);

        if(Utils.isConnected(getActivity())) {

            HoroscopeParser week = new HoroscopeParser();
            week.setListener(this);
            week.getWeekHoroscope();

            initListeners();

            generateRandomValues();

        } else{
            root.setVisibility(View.INVISIBLE);
            rlInternet.setVisibility(View.VISIBLE);
            btnWeek.setVisibility(View.INVISIBLE);
        }


        return v;
    }

    private void initListeners() {
        btnWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initPayment();
            }
        });
    }


    private void initUI(View v){
        root = (ScrollView) v.findViewById(R.id.sc_week);
        root.setVisibility(View.GONE);
        rlInternet = (RelativeLayout) v.findViewById(R.id.rl_internet);
        tvWeek = (TextView) v.findViewById(R.id.tv_week_horoscope_text);
        tvSign = (TextView) v.findViewById(R.id.tv_week_horoscope_sign);
        tvDate = (TextView) v.findViewById(R.id.tv_week_horoscope_date);
        ivSign = (ImageView) v.findViewById(R.id.iv_sign_details);
        btnWeek = (Button) v.findViewById(R.id.btn_week_horoscope);

        if(CacheManager.getWeekHoroscope(getActivity())!= null){
            String date = CacheManager.getWeekHoroscope(getActivity());
            if(date.equals(String.valueOf(DateManager.getLastDayOfWeek()))){
                root.setVisibility(View.VISIBLE);
                btnWeek.setVisibility(View.GONE);
            } else{
                root.setVisibility(View.GONE);
                btnWeek.setVisibility(View.VISIBLE);
            }
        }
        pbLove = (CustomProgress) v.findViewById(R.id.pb_week_love);
        pbLove.setProgressColor(getResources().getColor(R.color.orange));

        pbJob = (CustomProgress) v.findViewById(R.id.pb_week_job);
        pbJob.setProgressColor(getResources().getColor(R.color.purple));

        pbHappiness = (CustomProgress) v.findViewById(R.id.pb_week_happiness);
        pbHappiness.setProgressColor(getResources().getColor(R.color.blue));

        pbHealth = (CustomProgress) v.findViewById(R.id.pb_week_health);
        pbHealth.setProgressColor(getResources().getColor(R.color.green));

        colorizeBuyButton(horoscopeId);


    }

    private void generateRandomValues() {
        for(int i=0;i<4;i++){
            list.add(Utils.randInt(DateManager.getLastDayOfWeekForSliders(), i, horoscopeId, AppConstants.TYPE_WEEK));
        }

        setValues();
    }

    private void setValues() {
        float love = list.get(0);
        float job = list.get(1);
        float happiness = list.get(2);
        float health = list.get(3);
        pbLove.setMaximumPercentage(love / 100);
        pbJob.setMaximumPercentage(job/100);
        pbHappiness.setMaximumPercentage(happiness / 100);
        pbHealth.setMaximumPercentage(health / 100);

    }
    private void colorizeBuyButton(int position) {
        int color = 0;

        if (position == 0 || position == 4 || position == 8) {
            color = getResources().getColor(R.color.orange);
        } else if (position == 1 || position == 5 || position == 9) {
            color = getResources().getColor(R.color.purple);
        } else if (position == 2 || position == 6 || position == 10) {
            color = getResources().getColor(R.color.blue);
        } else if (position == 3 || position == 7 || position == 11) {
            color = getResources().getColor(R.color.green);
        }

        btnWeek.setBackgroundColor(color);
    }

    @Override
    public void onWeekHoroscopeLoaded(HoroscopeFeed weekHoroscopeFeed) {
        tvWeek.setText(weekHoroscopeFeed.getItem(horoscopeId).getHoroscope());
        tvSign.setText("Tjedni horoskop - "+DateManager.getCurrentWeek());
        ivSign.setImageResource(AppConstants.IMAGES[horoscopeId]);
    }

    private void initPayment() {
        PaymentRequest.PaymentRequestBuilder builder = new PaymentRequest.PaymentRequestBuilder();
        builder.setService(AppConstants.SERVICE_ID, AppConstants.APP_SECRET);
        builder.setDisplayString("Tjedni horoskop");
        builder.setProductName("Tjedni horoskop");  // non-consumable purchases are restored using this value
        builder.setType(MpUtils.PRODUCT_TYPE_CONSUMABLE);              // non-consumable items can be later restored
        builder.setIcon(R.drawable.ic_launcher);
        PaymentRequest pr = builder.build();
        makePayment(pr);
    }

    // Fortumo related glue-code
    private static final int REQUEST_CODE = 1234; // Can be anything

    protected final void makePayment(PaymentRequest payment) {
        startActivityForResult(payment.toIntent(getActivity()), REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            if(data == null) {
                return;
            }

            // OK
            if (resultCode == getActivity().RESULT_OK) {
                PaymentResponse response = new PaymentResponse(data);

                switch (response.getBillingStatus()) {
                    case MpUtils.MESSAGE_STATUS_BILLED:
                        Log.d("TAG", "MESSAGE_STATUS_BILLED");
                        root.setVisibility(View.VISIBLE);
                        btnWeek.setVisibility(View.GONE);
                        CacheManager.saveWeekHoroscope(getActivity(), DateManager.getLastDayOfWeek());
                        break;
                    case MpUtils.MESSAGE_STATUS_FAILED:
                        Log.d("TAG", "MESSAGE_STATUS_FAILED");
                        break;
                    case MpUtils.MESSAGE_STATUS_PENDING:
                        Log.d("TAG", "MESSAGE_STATUS_FAILED");
                        break;
                }
                // Cancel
            } else {
                // ..
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
