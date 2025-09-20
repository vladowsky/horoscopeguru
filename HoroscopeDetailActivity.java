package hr.horoskop.horoskop.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdView;

import hr.horoskop.horoskop.R;
import hr.horoskop.horoskop.adapters.ViewPagerAdapter;
import hr.horoskop.horoskop.fragments.HoroscopeDetailFragment;
import hr.horoskop.horoskop.utils.SlidingTabLayout;


/**
 * An activity representing a single Horoscope detail screen. This
 * activity is only used on handset devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link MainActivity}.
 * <p/>
 * This activity is mostly just a 'shell' activity containing nothing
 * more than a {@link HoroscopeDetailFragment}.
 */
public class HoroscopeDetailActivity extends ActionBarActivity {

    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[]={"Dnevni","Ljubavni", "Tjedni", "Mjesečni", "Godišnji", "Karakteristike"};
    int Numboftabs =6;
    public static final String SIGN = "sign";
    private String value;
    public static final String[] signs = new String[] { "Ovan", "Bik", "Blizanci", "Rak", "Lav", "Djevica", "Vaga", "Škorpion", "Strijelac", "Jarac", "Vodenjak", "Ribe" };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horoscope_detail);

        // Show the Up button in the action bar.
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getIntent().getStringExtra(SIGN) != null) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            value = getIntent().getStringExtra(SIGN);
        }




        //Visibility GONE u XMLu

        final PublisherAdView mAdView = (PublisherAdView) findViewById(R.id.adView);
        PublisherAdRequest adRequest = new PublisherAdRequest.Builder().addTestDevice("6265E854E00A1E57C908308A26B86ED0").build();
            mAdView.loadAd(adRequest);
            mAdView.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    mAdView.setVisibility(View.VISIBLE);
                    super.onAdLoaded();
                }
            });


        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter =  new ViewPagerAdapter(getSupportFragmentManager(),Titles,Numboftabs, value);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.horoscope_pager);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width


        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);


        setUpToolbar(value);


    // savedInstanceState is non-null when there is fragment state
    // saved from previous configurations of this activity
    // (e.g. when rotating the screen from portrait to landscape).
    // In this case, the fragment will automatically be re-added
    // to its container so we don't need to manually add it.
    // For more information, see the Fragments API guide at:
    //
    // http://developer.android.com/guide/components/fragments.html
    //
       /* if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(HoroscopeDetailFragment.SIGN,
                    getIntent().getStringExtra(HoroscopeDetailFragment.SIGN));
            HoroscopeDetailFragment fragment = new HoroscopeDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.horoscope_detail_container, fragment)
                    .commit();
        }*/

    }

    private void setUpToolbar(String value) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_tablet);

        setUpToolbarColor(value, toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Horoskop - "+signs[Integer.parseInt(value)]);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void setUpToolbarColor(final String value, Toolbar toolbar) {

        int pos = Integer.parseInt(value);

        colorStatusBar(pos);

        colorToolbarAndTabs(pos, toolbar);

    }

    private void colorToolbarAndTabs(final int position, Toolbar toolbar) {
        if(position == 0 || position == 4 || position == 8) {
            toolbar.setBackgroundColor(getResources().getColor(R.color.orange));
            tabs.setBackgroundColor(getResources().getColor(R.color.orange));
        } else if(position == 1 || position == 5 || position == 9 ){
            toolbar.setBackgroundColor(getResources().getColor(R.color.purple));
            tabs.setBackgroundColor(getResources().getColor(R.color.purple));
        } else if(position == 2 || position == 6 || position == 10 ){
            toolbar.setBackgroundColor(getResources().getColor(R.color.blue));
            tabs.setBackgroundColor(getResources().getColor(R.color.blue));
        } else if(position == 3 || position == 7 || position == 11 ){
            toolbar.setBackgroundColor(getResources().getColor(R.color.green));
            tabs.setBackgroundColor(getResources().getColor(R.color.green));
        }

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position1) {
                int color = 0;

                if (position == 0 || position == 4 || position == 8) {
                    color = getResources().getColor(R.color.dark_orange);
                } else if (position == 1 || position == 5 || position == 9) {
                    color = getResources().getColor(R.color.dark_purple);
                } else if (position == 2 || position == 6 || position == 10) {
                    color = getResources().getColor(R.color.dark_blue);
                } else if (position == 3 || position == 7 || position == 11) {
                    color = getResources().getColor(R.color.dark_green);
                }


                return color;
            }

        });
    }

    private void colorStatusBar(int position) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            if(position == 0 || position == 4 || position == 8) {
                window.setStatusBarColor(getResources().getColor(R.color.dark_orange));
            } else if(position == 1 || position == 5 || position == 9 ){
                window.setStatusBarColor(getResources().getColor(R.color.dark_purple));
            } else if(position == 2 || position == 6 || position == 10 ){
                window.setStatusBarColor(getResources().getColor(R.color.dark_blue));
            } else if(position == 3 || position == 7 || position == 11 ){
                window.setStatusBarColor(getResources().getColor(R.color.dark_green));
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpTo(this, new Intent(this, MainActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
