package hr.horoskop.horoskop.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdView;

import hr.horoskop.horoskop.R;
import hr.horoskop.horoskop.adapters.MainViewPager;
import hr.horoskop.horoskop.adapters.ViewPagerAdapter;
import hr.horoskop.horoskop.fragments.HoroscopeDetailFragment;
import hr.horoskop.horoskop.fragments.HoroscopeListFragment;
import hr.horoskop.horoskop.utils.HoroscopeService;
import hr.horoskop.horoskop.utils.SlidingTabLayout;


/**
 * An activity representing a list of Horoscopes. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link HoroscopeDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p/>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link HoroscopeListFragment} and the item details
 * (if present) is a {@link HoroscopeDetailFragment}.
 * <p/>
 * This activity also implements the required
 * {@link HoroscopeListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class MainActivity extends AppCompatActivity
        implements HoroscopeListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    ViewPager pager;
    MainViewPager adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[]={"Horoskop","Ljubavni kalkulator", "Položaj planeta", "Kretanje mjeseca", "Tranziti planeta", "Info"};

    ViewPager tabletPager;
    ViewPagerAdapter adapterTablet;
    SlidingTabLayout tabsTablet;
    CharSequence TitlesTablet[]={"Dnevni","Ljubavni", "Tjedni", "Mjesečni", "Godišnji", "Karakteristike"};
    int Numboftabs =6;
    private LinearLayout llAds;

    public AlarmManager alarmManager;
    Intent alarmIntent;
    PendingIntent pendingIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horoscope_list);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_tablet);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));

        HoroscopeService.enableAlarmManager(getApplicationContext());


        if (findViewById(R.id.horoscope_pager_tablet) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
            // Assigning ViewPager View and setting the adapter
            tabletPager = (ViewPager) findViewById(R.id.horoscope_pager_tablet);
            tabletPager.setVisibility(View.GONE);

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.

        }

        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter =  new MainViewPager(getSupportFragmentManager(),Titles, Titles.length, false);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.horoscope_pager);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width


        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);


        final PublisherAdView mAdView = (PublisherAdView) findViewById(R.id.adView);
        llAds = (LinearLayout) findViewById(R.id.ll_ads);
        PublisherAdRequest adRequest = new PublisherAdRequest.Builder().addTestDevice("6265E854E00A1E57C908308A26B86ED0").build();
        if(mTwoPane == false) {
            mAdView.loadAd(adRequest);
            mAdView.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    llAds.setVisibility(View.VISIBLE);
                    super.onAdLoaded();
                }
            });
        }

       /* HoroscopeListFragment horoscopeListFragment = new HoroscopeListFragment();
        Bundle arguments = new Bundle();
        arguments.putBoolean("device", false);
        //arguments.putBoolean("deviceLandscape", mTwoPaneLandscape);
        horoscopeListFragment.setArguments(arguments);*/

        //getSupportFragmentManager().beginTransaction().replace(R.id.fl_horoscope_list, horoscopeListFragment).commit();
    }

    private int getCurrentVersion(){
        return android.os.Build.VERSION.SDK_INT;
    }


    @Override
    public void onItemSelected(String sign) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            /*Bundle arguments = new Bundle();
            arguments.putString(HoroscopeDetailFragment.SIGN, sign);
            HoroscopeDetailFragment fragment = new HoroscopeDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.horoscope_detail_container, fragment)
                    .commit();*/
            // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
            //
            //
            //

            // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
            adapter =  new MainViewPager(getSupportFragmentManager(),Titles, Titles.length, true);

            // Assigning ViewPager View and setting the adapter
            pager = (ViewPager) findViewById(R.id.horoscope_pager);
            pager.setAdapter(adapter);

            // Assiging the Sliding Tab Layout View
            tabs = (SlidingTabLayout) findViewById(R.id.tabs);
            tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width


            // Setting the ViewPager For the SlidingTabsLayout
            tabs.setViewPager(pager);



            adapterTablet =  new ViewPagerAdapter(getSupportFragmentManager(),TitlesTablet,Numboftabs, sign);


            tabletPager.setAdapter(adapterTablet);
            tabletPager.setVisibility(View.VISIBLE);

            // Assiging the Sliding Tab Layout View
            tabsTablet = (SlidingTabLayout) findViewById(R.id.tabs);
            tabsTablet.setVisibility(View.VISIBLE);
            tabsTablet.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

            // Setting Custom Color for the Scroll bar indicator of the Tab View
            tabsTablet.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
                @Override
                public int getIndicatorColor(int position) {
                    return getResources().getColor(R.color.tabsScrollColor);
                }

            });

            // Setting the ViewPager For the SlidingTabsLayout
            tabsTablet.setViewPager(tabletPager);



        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, HoroscopeDetailActivity.class);
            detailIntent.putExtra(HoroscopeDetailFragment.SIGN, sign);
            startActivity(detailIntent);
        }
    }
}
