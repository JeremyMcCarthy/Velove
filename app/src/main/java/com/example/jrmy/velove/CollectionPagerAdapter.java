package com.example.jrmy.velove;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class CollectionPagerAdapter extends FragmentStatePagerAdapter {
    public CollectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    private BikeListFragment stationFragment;

    public BikeListFragment getStationFragment() {
        return stationFragment;
    }

    @Override
    public Fragment getItem(int i) {
        if (i==0){
            stationFragment = new BikeListFragment();
            return stationFragment;
        }
        else{
            Fragment fragment = new PagerFragment();
            Bundle args = new Bundle();
            // Our object is just an integer :-P
            args.putInt(PagerFragment.ARG_OBJECT, i + 1);
            fragment.setArguments(args);
            return fragment;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "OBJECT " + (position + 1);
    }
}
