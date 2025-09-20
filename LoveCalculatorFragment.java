package hr.horoskop.horoskop.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hr.horoskop.horoskop.R;
import hr.horoskop.horoskop.activities.LoveCalculatorResultActivity;
import mp.MpUtils;
import mp.PaymentRequest;
import mp.PaymentResponse;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoveCalculatorFragment extends Fragment {

    Button btnCalaculate;
    private EditText etFirstName, etSecondName;
    private String firstName, secondName;
    private static String SERVICE_ID = "c70e9c9ce4ab252595cf3b184dcebc25";
    private static String APP_SECRET = "8f6e63cb68751f17d0311d2e857843b8";

    public LoveCalculatorFragment() {
        // Required empty public constructor
    }

    public LoveCalculatorFragment init() {


        LoveCalculatorFragment fragment = new LoveCalculatorFragment();
        // Supply val input as an argument.
        Bundle args = new Bundle();
        fragment.setArguments(args);


        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_love_calculator, container, false);

        btnCalaculate = (Button) view.findViewById(R.id.btn_calculate);
        etFirstName = (EditText) view.findViewById(R.id.et_first_name);
        etSecondName = (EditText) view.findViewById(R.id.et_second_name);

        btnCalaculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firstName = etFirstName.getText().toString();
                secondName = etSecondName.getText().toString();

                if (firstName.isEmpty() || secondName.isEmpty()) {
                    Toast.makeText(getActivity(), "Morate unijeti oba imena!", Toast.LENGTH_SHORT).show();

                } else {
                    initPayment();
                }

            }
        });
        
        return view;
    }


    private void initPayment() {
        PaymentRequest.PaymentRequestBuilder builder = new PaymentRequest.PaymentRequestBuilder();
        builder.setService(SERVICE_ID, APP_SECRET);
        builder.setDisplayString("Ljubavni kalkulator");
        builder.setProductName("ljubavni_kalkulator");  // non-consumable purchases are restored using this value
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
                        //loveListener.showLoveResult(firstName, secondName);
                        Intent i = new Intent(getActivity(), LoveCalculatorResultActivity.class);
                        i.putExtra("firstName", firstName);
                        i.putExtra("secondName", secondName);
                        startActivity(i);
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
