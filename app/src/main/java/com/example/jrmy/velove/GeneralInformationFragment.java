package com.example.jrmy.velove;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

// Fragment d'affichage des différentes informations concernant l'application
// Ces informations sont : le but de l'application, le lien vers les sources ainsi que le lien vers les données
// C'est uniquement un fragment d'affichage, sans aucune autre opération
public class GeneralInformationFragment extends Fragment {


    public GeneralInformationFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_general_information, container, false);
    }
}
