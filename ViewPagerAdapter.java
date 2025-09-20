package hr.horoskop.horoskop.adapters;

/**
 * Created by ZoPa on 22.4.2015..
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import hr.horoskop.horoskop.fragments.DayHoroscopeFragment;
import hr.horoskop.horoskop.fragments.GeneralHoroscopeFragment;
import hr.horoskop.horoskop.fragments.LoveHoroscopeFragment;
import hr.horoskop.horoskop.fragments.MonthHoroscopeFragment;
import hr.horoskop.horoskop.fragments.WeekHoroscopeFragment;
import hr.horoskop.horoskop.fragments.YearHoroscopeFragment;

/**
 * Created by hp1 on 21-01-2015.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created
    String value;


    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapter(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb, String value) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;
        this.value = value;

    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        int v = Integer.parseInt(value);

        switch (position){
            case 0:
                return new DayHoroscopeFragment().init(v);
            case 1:
                return  new LoveHoroscopeFragment().init(v);
            case 2:
                return  new WeekHoroscopeFragment().init(v);
            case 3:
                return  new MonthHoroscopeFragment().init(v);
            case 4:
                return new YearHoroscopeFragment().init(v);
            case 5:
                return new GeneralHoroscopeFragment().init(v);

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