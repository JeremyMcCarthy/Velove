package com.example.jrmy.velove;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

public class CollectionPagerAdapter extends FragmentPagerAdapter {
    public CollectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    private BikeListFragment stationFragment;
    private MapsFragment mapsFragment;

    public BikeListFragment getStationFragment() {
        return stationFragment;
    }
    public MapsFragment getMapsFragment() {
        return mapsFragment;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment;
        Bundle args;
        switch(i) {
            case 1 :
                mapsFragment = new MapsFragment();
                fragment = mapsFragment;
                break ;
            case 0 :
                stationFragment = new BikeListFragment();
                fragment = stationFragment;
                break ;
            default :
                fragment = new PagerFragment();
                args = new Bundle();
                args.putInt(PagerFragment.ARG_OBJECT, i + 1);
                fragment.setArguments(args);
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

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
                title = "Unkonwn";
                break;
        }
        return title;
    }
}
