package com.example.jrmy.velove;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class CollectionPagerAdapter extends FragmentStatePagerAdapter {
    public CollectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment;
        if(i==2) {
            fragment = new MapsFragment();
            Bundle args = new Bundle();
            args.putInt(MapsFragment.ARG_OBJECT, i + 1);
            fragment.setArguments(args);
        }
        else {
            fragment = new PagerFragment();
            Bundle args = new Bundle();
            // Our object is just an integer :-P
            args.putInt(PagerFragment.ARG_OBJECT, i + 1);
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "OBJECT " + (position + 1);
    }
}
