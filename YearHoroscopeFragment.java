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
import hr.horoskop.horoskop.interfaces.YearHoroscopeLoaded;
import hr.horoskop.horoskop.model.HoroscopeFeed;
import hr.horoskop.horoskop.utils.AppConstants;
import hr.horoskop.horoskop.utils.CacheManager;
import hr.horoskop.horoskop.utils.CustomProgress;
import hr.horoskop.horoskop.utils.DateManager;
import hr.horoskop.horoskop.utils.Utils;
import mp.MpUtils;
import mp.PaymentRequest;
import mp.PaymentResponse;

/**
 * A simple {@link Fragment} subclass.
 */
public class YearHoroscopeFragment extends Fragment implements YearHoroscopeLoaded{

    private int horoscopeId;
    private ArrayList<Integer> randList = new ArrayList<Integer>();

    //UI
    private TextView tvDay, tvSign, tvDuration, tvDate;
    private ImageView ivSign;
    private ScrollView root;
    private CustomProgress pbLove, pbJob, pbHappiness, pbHealth;
    private RelativeLayout rlInternet;
    private Button btnYear;

    //Fortumo
    public static String SERVICE_ID = "034c2b463823f7d39345bd881f5551bf";
    public static String APP_SECRET = "9498ff8daa14f99ca7ada62ce5b104ee";


    public YearHoroscopeFragment() {
        // Required empty public constructor
    }

    public YearHoroscopeFragment init(int position) {
        Log.d("TAG", "on year horoscpe fragment init");
        Log.d("TAG", String.valueOf(position));

        YearHoroscopeFragment fragment = new YearHoroscopeFragment();
        // Supply val input as an argument.
        Bundle args = new Bundle();
        args.putInt("position", position);
        fragment.setArguments(args);


        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_year,container,false);

        horoscopeId  = getArguments().getInt("position");


        initUI(v);

        initListeners();

        if(Utils.isConnected(getActivity())) {
            horoscopeId = getArguments().getInt("position");

            HoroscopeParser year = new HoroscopeParser();
            year.setListener(this);
            year.getYearHoroscope();

            generateRandomValues();
        } else{
            root.setVisibility(View.INVISIBLE);
            rlInternet.setVisibility(View.VISIBLE);
            btnYear.setVisibility(View.INVISIBLE);
        }

        return v;
    }

    private void initListeners() {
        btnYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initPayment();
            }
        });
    }

    private void initUI(View v){

        root = (ScrollView) v.findViewById(R.id.sv_year);
        root.setVisibility(View.GONE);
        rlInternet = (RelativeLayout) v.findViewById(R.id.rl_internet);

        tvDay = (TextView) v.findViewById(R.id.tv_year_horoscope_text);
        tvSign = (TextView) v.findViewById(R.id.tv_year_horoscope_sign);
        tvDate = (TextView) v.findViewById(R.id.tv_year_horoscope_date);
        ivSign = (ImageView) v.findViewById(R.id.iv_sign_details);

        btnYear  = (Button) v.findViewById(R.id.btn_horoscope_year);

        if(CacheManager.getYearHoroscope(getActivity())!= null){
            String date = CacheManager.getYearHoroscope(getActivity());
            if(date.equals(String.valueOf(DateManager.getCurrentYear()))){
                root.setVisibility(View.VISIBLE);
                btnYear.setVisibility(View.GONE);
            } else{
                root.setVisibility(View.GONE);
                btnYear.setVisibility(View.VISIBLE);
            }
        }

        pbLove = (CustomProgress) v.findViewById(R.id.pb_year_love);
        pbLove.setProgressColor(getResources().getColor(R.color.orange));

        pbJob = (CustomProgress) v.findViewById(R.id.pb_year_job);
        pbJob.setProgressColor(getResources().getColor(R.color.purple));

        pbHappiness = (CustomProgress) v.findViewById(R.id.pb_year_happiness);
        pbHappiness.setProgressColor(getResources().getColor(R.color.blue));

        pbHealth = (CustomProgress) v.findViewById(R.id.pb_year_health);
        pbHealth.setProgressColor(getResources().getColor(R.color.green));

        colorizeBuyButton(horoscopeId);

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

        btnYear.setBackgroundColor(color);
    }


    @Override
    public void onYearHoroscopeLoaded(HoroscopeFeed horoscopeFeed) {
        tvDay.setText(horoscopeFeed.getItem(horoscopeId).getHoroscope());
        tvSign.setText(horoscopeFeed.getItem(horoscopeId).getSign());
        tvSign.setText("Godišnji horoskop - " + DateManager.getCurrentYear());
        ivSign.setImageResource(AppConstants.IMAGES[horoscopeId]);
    }

    private void generateRandomValues() {
        for(int i=0;i<4;i++){
            randList.add(Utils.randInt(DateManager.getYearForSliders(), i, horoscopeId, AppConstants.TYPE_YEAR));
        }

        setValues();
    }

    private void setValues() {
        float love = randList.get(0);
        float job = randList.get(1);
        float happiness = randList.get(2);
        float health = randList.get(3);
        pbLove.setMaximumPercentage(love/100);
        pbJob.setMaximumPercentage(job / 100);
        pbHappiness.setMaximumPercentage(happiness/100);
        pbHealth.setMaximumPercentage(health / 100);
    }

    private void initPayment() {
        PaymentRequest.PaymentRequestBuilder builder = new PaymentRequest.PaymentRequestBuilder();
        builder.setService(SERVICE_ID, APP_SECRET);
        builder.setDisplayString("Godišnji horoskop");
        builder.setProductName("Godišnji horoskop");  // non-consumable purchases are restored using this value
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
                        btnYear.setVisibility(View.GONE);
                        CacheManager.saveYearHoroscope(getActivity(), DateManager.getCurrentYear());
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
