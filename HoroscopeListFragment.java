package hr.horoskop.horoskop.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import hr.horoskop.horoskop.R;
import hr.horoskop.horoskop.utils.AppConstants;

/**
 * A list fragment representing a list of Horoscopes. This fragment
 * also supports tablet devices by allowing list items to be given an
 * 'activated' state upon selection. This helps indicate which item is
 * currently being viewed in a {@link HoroscopeDetailFragment}.
 * <p/>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class HoroscopeListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    public static final String[] signs = new String[] { "Ovan", "Bik", "Blizanci", "Rak", "Lav", "Djevica", "Vaga", "Škorpion", "Strijelac", "Jarac", "Vodenjak", "Ribe" };

    private boolean device;




    /**
     * The serialization (saved instance state) Bundle key representing the
     * activated item position. Only used on tablets.
     */
    private static final String STATE_ACTIVATED_POSITION = "activated_position";

    /**
     * The fragment's current callback object, which is notified of list item
     * clicks.
     */
    private Callbacks mCallbacks = sDummyCallbacks;

    /**
     * The current activated item position. Only used on tablets.
     */
    private int mActivatedPosition = ListView.INVALID_POSITION;



    /**
     * A callback interface that all activities containing this fragment must
     * implement. This mechanism allows activities to be notified of item
     * selections.
     */
    public interface Callbacks {
        /**
         * Callback for when an item has been selected.
         */
        public void onItemSelected(String id);
    }

    /**
     * A dummy implementation of the {@link Callbacks} interface that does
     * nothing. Used only when this fragment is not attached to an activity.
     */
    private static Callbacks sDummyCallbacks = new Callbacks() {
        @Override
        public void onItemSelected(String id) {
        }
    };

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public HoroscopeListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public HoroscopeListFragment init(boolean device) {


        HoroscopeListFragment fragment = new HoroscopeListFragment();
        // Supply val input as an argument.
        Bundle args = new Bundle();
        args.putBoolean("device", device);
        fragment.setArguments(args);


        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (getArguments().containsKey("device")) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            device = getArguments().getBoolean("device");
        }

        int orientation = getResources().getConfiguration().orientation;

        int colNum = 3;

        if (getActivity().findViewById(R.id.horoscope_pager_tablet) != null) {

            if(orientation == 2){
                colNum = 4;
            }

        }

        if(device == true){
            colNum = 1;
        }


        mAdapter = new MyAdapter();
        mRecyclerView.setAdapter(mAdapter);

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), colNum);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_horoscope_list, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_list);

        if (getArguments().containsKey("device")) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            device = getArguments().getBoolean("device");
        }

     /*   int colNum = 3;
       if(device == true){
            colNum = 1;
        }


        mAdapter = new MyAdapter();
        mRecyclerView.setAdapter(mAdapter);

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), colNum);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);*/


        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

        public class ViewHolder extends RecyclerView.ViewHolder {

            private TextView tvSign;
            private CardView cardView;
            private ImageView ivSign;

            public ViewHolder(View v) {
                super(v);
                tvSign = (TextView) v.findViewById(R.id.tv_sign);
                cardView = (CardView) v.findViewById(R.id.card_view);
                ivSign = (ImageView) v.findViewById(R.id.iv_sign);
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public MyAdapter() {
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.horocope_list_item, parent, false);
            // set the view's size, margins, paddings and layout parameters
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder holder, final int i) {
            holder.tvSign.setText(signs[i]);
            holder.ivSign.setImageResource(AppConstants.IMAGES[i]);
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCallbacks.onItemSelected(String.valueOf(i));
                }
            });
        }

        @Override
        public int getItemCount() {
            return signs.length;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // Activities containing this fragment must implement its callbacks.
        if (!(activity instanceof Callbacks)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        }

        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        // Reset the active callbacks interface to the dummy implementation.
        mCallbacks = sDummyCallbacks;
    }

}
