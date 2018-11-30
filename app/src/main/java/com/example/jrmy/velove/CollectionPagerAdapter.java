package com.example.jrmy.velove;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

//Gestionnaire des fragments dans les différents onglets
//FragmentPagerAdapter permet de mettre en mémoire les fragments dès le lancement de l'application, fonctionement plus fluide
//FragmentStatePagerAdapter aurait permis de les lancer uniquement lorsqu'ils sont affichés cependant nous n'en n'avons que 3 donc pas nécessaire
public class CollectionPagerAdapter extends FragmentPagerAdapter {
    public CollectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    private StationListFragment stationFragment;
    private MapsFragment mapsFragment;

    //pour pouvoir récupérer les fragments depuis la MainActivity
    public StationListFragment getStationFragment() {
        return stationFragment;
    }
    public MapsFragment getMapsFragment() {
        return mapsFragment;
    }

    //Attribution des fragments à chaque onglet
    @Override
    public Fragment getItem(int i) {
        Fragment fragment;
        switch(i) {
            case 1 :
                mapsFragment = new MapsFragment();
                fragment = mapsFragment;
                break ;
            case 0 :
                stationFragment = new StationListFragment();
                fragment = stationFragment;
                break ;
            default :
                fragment = new GeneralInformationFragment();
                break;
        }
        return fragment;
    }

    //nombre d'onglets
    @Override
    public int getCount() {
        return 3;
    }

    //gestion des titres des onglets
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
                title = "Infos";
                break;
        }
        return title;
    }
}
