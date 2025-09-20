package hr.horoskop.horoskop.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hr.horoskop.horoskop.R;
import hr.horoskop.horoskop.controlor.PlanetParser;
import hr.horoskop.horoskop.model.PlanetFeed;
import hr.horoskop.horoskop.model.PlanetItem;
import hr.horoskop.horoskop.utils.AppConstants;
import hr.horoskop.horoskop.interfaces.PlanetLoaded;
import hr.horoskop.horoskop.utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlanetsPositionFragment extends Fragment implements PlanetLoaded {

    private List<PlanetItem> planetList = new ArrayList<PlanetItem>();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RelativeLayout rlInternet;

    public PlanetsPositionFragment() {
        // Required empty public constructor
    }

    public PlanetsPositionFragment init() {


        PlanetsPositionFragment fragment = new PlanetsPositionFragment();
        // Supply val input as an argument.
        Bundle args = new Bundle();
        fragment.setArguments(args);


        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_planets_position, container, false);


        mRecyclerView = (RecyclerView) v.findViewById(R.id.rv_planets);
        rlInternet = (RelativeLayout) v.findViewById(R.id.rl_internet);

        mAdapter = new PlanetsAdapter();
        mRecyclerView.setAdapter(mAdapter);

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        if(Utils.isConnected(getActivity())) {
            PlanetParser planetParser = new PlanetParser();
            planetParser.setListener(this);
            planetParser.getPlanets();
        } else{
            rlInternet.setVisibility(View.VISIBLE);
        }

        return v;
    }


    @Override
    public void onPlanetLoaded(PlanetFeed planetFeed) {
        for(int i=0;i<planetFeed.getItemCount();i++){
            planetList.add(planetFeed.getItem(i));
        }
        mAdapter.notifyDataSetChanged();
    }

    public class PlanetsAdapter extends RecyclerView.Adapter<PlanetsAdapter.ViewHolder>{


        public class ViewHolder extends RecyclerView.ViewHolder {

            private TextView tvPlanet;
            private TextView tvPosition;
            private ImageView ivSign;

            public ViewHolder(View v) {
                super(v);
                tvPlanet = (TextView) v.findViewById(R.id.tv_planet);
                tvPosition = (TextView) v.findViewById(R.id.tv_planet_position);
                ivSign = (ImageView) v.findViewById(R.id.iv_sign);
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public PlanetsAdapter() {
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.planets_position_item, parent, false);
            // set the view's size, margins, paddings and layout parameters
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(PlanetsAdapter.ViewHolder holder, final int i) {
            holder.tvPlanet.setText(planetList.get(i).getPlanet());
            int positionOfSign = handleSign(planetList.get(i).getSign());
            holder.ivSign.setImageResource(AppConstants.IMAGES[positionOfSign]);
            holder.tvPosition.setText(planetList.get(i).getPosition());
        }

        private int handleSign(String sign) {
            int position = 0;

            if(sign.equals(AppConstants.SHORT_ARIES)){
                position = 0;
            } else if(sign.equals(AppConstants.SHORT_TAURUS)){
                position = 1;
            }
            else if(sign.equals(AppConstants.SHORT_GEMINI)){
                position = 2;
            }
            else if(sign.equals(AppConstants.SHORT_CANCER)){
                position = 3;
            }
            else if(sign.equals(AppConstants.SHORT_LEO)){
                position = 4;
            }
            else if(sign.equals(AppConstants.SHORT_VIRGO)){
                position = 5;
            }
            else if(sign.equals(AppConstants.SHORT_LIBRA)){
                position = 6;
            }
            else if(sign.equals(AppConstants.SHORT_SCORPIO)){
                position = 7;
            }
            else if(sign.equals(AppConstants.SHORT_SAGITTARIUS)){
                position = 8;
            }
            else if(sign.equals(AppConstants.SHORT_CAPRICORN)){
                position = 9;
            }
            else if(sign.equals(AppConstants.SHORT_AQUARIUS)){
                position = 10;
            }
            else if(sign.equals(AppConstants.SHORT_PISCES)){
                position = 11;
            }

            return position;
        }

        @Override
        public int getItemCount() {
            return planetList.size();
        }
    }
}
