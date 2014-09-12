package com.aeg.xmlscore;

import android.os.Bundle;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Created by nemo on 9/11/14.
 */
public class StavesAdapter extends FragmentStatePagerAdapter {

    public StavesAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = new Stage();
        Bundle args = new Bundle();
        // Our object is just an integer :-P
        args.putInt(Stage.ARG_OBJECT, i + 1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return 100;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "OBJECT " + (position + 1);
    }
}
