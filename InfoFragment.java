package hr.horoskop.horoskop.fragments;


import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import hr.horoskop.horoskop.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment {

    TextView tvRate, tvVersion;


    public InfoFragment() {
        // Required empty public constructor
    }

    public InfoFragment init() {


        InfoFragment fragment = new InfoFragment();
        // Supply val input as an argument.
        Bundle args = new Bundle();
        fragment.setArguments(args);


        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_info, container, false);

        tvRate = (TextView) v.findViewById(R.id.textView8);
        tvVersion = (TextView) v.findViewById(R.id.textView10);

        tvVersion.setText(getApplicationVersionName(getActivity()));

        tvRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rateApp();
            }
        });

        return v;
    }

    private void rateApp() {
        Uri uri = Uri.parse("market://details?id="
                + getActivity().getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(getActivity(), "Greška pri ocjenjivanju.",
                    Toast.LENGTH_LONG).show();
        }
    }

    public static String getApplicationVersionName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException ex) {} catch(Exception e){}
        return "";
    }


}
