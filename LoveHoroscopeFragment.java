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
import hr.horoskop.horoskop.interfaces.LoveHoroscopeLoaded;
import hr.horoskop.horoskop.utils.Utils;
import mp.MpUtils;
import mp.PaymentRequest;
import mp.PaymentResponse;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoveHoroscopeFragment extends Fragment implements LoveHoroscopeLoaded {

    private int horoscopeId;
    private ArrayList<Integer> randList = new ArrayList<Integer>();

    //UI
    private TextView  tvSign,  tvDate;
    private ImageView ivSign;
    private ScrollView root;
    private Button btnLove;
    private CustomProgress pbLove, pbJob, pbHappiness, pbHealth;
    private TextView tvLove;
    private RelativeLayout rlInternet;

    //Fortumo
    public static String SERVICE_ID = "bb7d8fc8334da436a259a4d1bcce3f63";
    public static String APP_SECRET = "1871c9943707d3049e246e10ceaddd5a";


    public LoveHoroscopeFragment() {
        // Required empty public constructor
    }

    public LoveHoroscopeFragment init(int position) {
        Log.d("TAG", "on month horoscpe fragment init");
        Log.d("TAG", String.valueOf(position));
        horoscopeId = position;


        LoveHoroscopeFragment fragment = new LoveHoroscopeFragment();
        // Supply val input as an argument.
        Bundle args = new Bundle();
        args.putInt("position", position);
        fragment.setArguments(args);


        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tab_love,container,false);

        horoscopeId  = getArguments().getInt("position");


        initUI(v);

        if(Utils.isConnected(getActivity())) {
            initListeners();

            HoroscopeParser parser = new HoroscopeParser();
            parser.setListener(this);
            parser.getLoveHoroscope();

            generateRandomValues();
        } else{
            root.setVisibility(View.INVISIBLE);
            rlInternet.setVisibility(View.VISIBLE);
            btnLove.setVisibility(View.INVISIBLE);
        }

        return v;
    }


    private void initListeners() {
        btnLove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initPayment();
            }
        });
    }

    private void initUI(View v){
        root = (ScrollView) v.findViewById(R.id.sc_love);
        root.setVisibility(View.GONE);
        rlInternet = (RelativeLayout) v.findViewById(R.id.rl_internet);
        btnLove = (Button) v.findViewById(R.id.btn_horoscope_love);
        tvLove = (TextView) v.findViewById(R.id.tv_love_horoscope_text);
        tvSign = (TextView) v.findViewById(R.id.tv_love_horoscope_sign);
        tvDate = (TextView) v.findViewById(R.id.tv_love_horoscope_date);
        ivSign = (ImageView) v.findViewById(R.id.iv_sign_details);

        if(CacheManager.getLoveHoroscope(getActivity())!= null){
            String date = CacheManager.getLoveHoroscope(getActivity());
            if(date.equals(String.valueOf(DateManager.getTodayEndDate()))){
                root.setVisibility(View.VISIBLE);
                btnLove.setVisibility(View.GONE);
            } else{
                root.setVisibility(View.GONE);
                btnLove.setVisibility(View.VISIBLE);
            }
        }

        pbLove = (CustomProgress) v.findViewById(R.id.pb_love);
        pbLove.setProgressColor(getResources().getColor(R.color.orange));

        pbJob = (CustomProgress) v.findViewById(R.id.pb_job);
        pbJob.setProgressColor(getResources().getColor(R.color.purple));

        pbHappiness = (CustomProgress) v.findViewById(R.id.pb_happiness);
        pbHappiness.setProgressColor(getResources().getColor(R.color.blue));

        pbHealth = (CustomProgress) v.findViewById(R.id.pb_health);
        pbHealth.setProgressColor(getResources().getColor(R.color.green));

        colorizeBuyButton(horoscopeId);


    }

    private void generateRandomValues() {
        for(int i=0;i<4;i++){
            randList.add(Utils.randInt(DateManager.getTodayDateForSliders(), i, horoscopeId, AppConstants.TYPE_LOVE));
        }

        setValues();
    }

    private void setValues() {
        float love = randList.get(0);
        float job = randList.get(1);
        float happiness = randList.get(2);
        float health = randList.get(3);
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

        btnLove.setBackgroundColor(color);
    }

    private void initPayment() {
        PaymentRequest.PaymentRequestBuilder builder = new PaymentRequest.PaymentRequestBuilder();
        builder.setService(SERVICE_ID, AppConstants.APP_SECRET);
        builder.setDisplayString("Ljubavni horoskop");
        builder.setProductName("Ljubavni horoskop");  // non-consumable purchases are restored using this value
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
                        btnLove.setVisibility(View.GONE);
                        CacheManager.saveLoveHoroscope(getActivity(), DateManager.getTodayEndDate());
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




    @Override
    public void onLoveHoroscopeLoaded(HoroscopeFeed loveHoroscopeFeed) {
        tvLove.setText(loveHoroscopeFeed.getItem(horoscopeId).getHoroscope());
        tvSign.setText("Ljubavni horoskop - "+DateManager.getCurrentDate());
        ivSign.setImageResource(AppConstants.IMAGES[horoscopeId]);
    }
}
