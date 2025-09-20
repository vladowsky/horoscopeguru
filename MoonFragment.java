package hr.horoskop.horoskop.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import hr.horoskop.horoskop.R;
import hr.horoskop.horoskop.controlor.MoonParser;
import hr.horoskop.horoskop.model.Moon;
import hr.horoskop.horoskop.utils.AppConstants;
import hr.horoskop.horoskop.interfaces.MoonLoaded;
import hr.horoskop.horoskop.utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoonFragment extends Fragment implements MoonLoaded {


    private TextView tvMoon;
    private ImageView ivSign;
    private TextView tvSign;
    private RelativeLayout rlInternet;


    public MoonFragment() {
        // Required empty public constructor
    }

    public MoonFragment init() {


        MoonFragment fragment = new MoonFragment();
        // Supply val input as an argument.
        Bundle args = new Bundle();
        fragment.setArguments(args);


        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_moon, container, false);

        tvMoon = (TextView) v.findViewById(R.id.tv_moon);
        tvSign = (TextView) v.findViewById(R.id.tv_moon_sign_title);
        ivSign = (ImageView) v.findViewById(R.id.iv_moon_sign);
        rlInternet = (RelativeLayout) v.findViewById(R.id.rl_internet);

        if(Utils.isConnected(getActivity())) {
            MoonParser moonParser = new MoonParser();
            moonParser.setListener(this);
            moonParser.getMoon();
        } else{
            rlInternet.setVisibility(View.VISIBLE);
            tvSign.setVisibility(View.INVISIBLE);
        }

        return v;
    }


    @Override
    public void onMoonLoaded(Moon moon) {
        tvMoon.setText(moon.getText());
        int id = moon.getSignId();
        ivSign.setImageResource(AppConstants.IMAGES[id]);
        tvSign.setText(moon.getSign());
    }
}
