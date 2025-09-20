package hr.horoskop.horoskop.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.RelativeLayout;

import hr.horoskop.horoskop.R;
import hr.horoskop.horoskop.utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlanetsTransitFragment extends Fragment {

    private WebView mWebView;
    private RelativeLayout rlInternet;


    public PlanetsTransitFragment() {
        // Required empty public constructor
    }

    public PlanetsTransitFragment init() {


        PlanetsTransitFragment fragment = new PlanetsTransitFragment();
        // Supply val input as an argument.
        Bundle args = new Bundle();
        fragment.setArguments(args);


        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_planets_transit, container, false);

        rlInternet = (RelativeLayout) v.findViewById(R.id.rl_internet);
        mWebView = (WebView) v.findViewById(R.id.wv_planets);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setUseWideViewPort(true);

        if(Utils.isConnected(getActivity())) {
            mWebView.loadUrl("http://vladowsky.com/horoHR/transits/index.php");
        } else{
            rlInternet.setVisibility(View.VISIBLE);
        }



        return v;
    }


}
