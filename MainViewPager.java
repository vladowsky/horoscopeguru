package hr.horoskop.horoskop.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import hr.horoskop.horoskop.fragments.HoroscopeListFragment;
import hr.horoskop.horoskop.fragments.InfoFragment;
import hr.horoskop.horoskop.fragments.LoveCalculatorFragment;
import hr.horoskop.horoskop.fragments.MoonFragment;
import hr.horoskop.horoskop.fragments.PlanetsPositionFragment;
import hr.horoskop.horoskop.fragments.PlanetsTransitFragment;

/**
 * Created by Zoran on 22.7.2015..
 */
public class MainViewPager extends FragmentStatePagerAdapter {

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created
    boolean device = false;
    // Build a Constructor and assign the passed Values to appropriate values in the class
    public MainViewPager(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb, boolean device) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;
        this.device = device;
    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return new HoroscopeListFragment().init(device);
            case 1:
                return new LoveCalculatorFragment().init();
            case 2:
                return new PlanetsPositionFragment().init();
            case 3:
                return new MoonFragment().init();
            case 4:
                return new PlanetsTransitFragment().init();
            case 5:
                return new InfoFragment().init();

        }

        return null;
    }

    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}