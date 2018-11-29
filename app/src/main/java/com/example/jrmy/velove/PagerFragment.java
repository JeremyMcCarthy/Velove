package com.example.jrmy.velove;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PagerFragment extends Fragment {
    public static final String ARG_OBJECT = "object";


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.fragment_pager, container, false);
        Bundle args = getArguments();
        CardView c = rootView.findViewById(R.id.cardV);
        ((TextView) rootView.findViewById(R.id.text1)).setText(
                Integer.toString(args.getInt(ARG_OBJECT)));
        return rootView;
    }

}
