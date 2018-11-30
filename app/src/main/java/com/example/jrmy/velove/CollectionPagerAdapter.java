package com.example.jrmy.velove;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class CollectionPagerAdapter extends FragmentPagerAdapter {
    public CollectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    private StationListFragment stationFragment;
    private MapsFragment mapsFragment;
    private GeneralInformationFragment informationFragment;

    public StationListFragment getStationFragment() {
        return stationFragment;
    }
    public MapsFragment getMapsFragment() {
        return mapsFragment;
    }

    // Return the fragment corresponding to the page we are on
    @Override
    public Fragment getItem(int i) {
        Fragment fragment;
        Bundle args;
        switch(i) {
            // The page 0 correspond to the list
            case 0 :
                stationFragment = new StationListFragment();
                fragment = stationFragment;
                break ;

            // The page 1 corresponds to the map
            case 1 :
                mapsFragment = new MapsFragment();
                fragment = mapsFragment;
                break ;

            case 2 :
                informationFragment = new GeneralInformationFragment();
                fragment = informationFragment;
                break;

            // The other pages correspond to a default page
            default :
                fragment = new PagerFragment();
                args = new Bundle();
                args.putInt(PagerFragment.ARG_OBJECT, i + 1);
                fragment.setArguments(args);
                break;
        }
        return fragment;
    }

    // Return the number of pages of the PageAdapter
    @Override
    public int getCount() {
        return 3;
    }

    // Return the title of the current page
    @Override
    public CharSequence getPageTitle(int position) {
        CharSequence title;
        switch(position) {
            case 1 :
                title = "Carte";
                break;
            case 0 :
                title = "Liste";
                break;
            default :
                title = "Détails";
                break;
        }
        return title;
    }
}
