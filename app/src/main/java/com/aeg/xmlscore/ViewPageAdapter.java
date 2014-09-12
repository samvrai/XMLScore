package com.aeg.xmlscore;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by nemo on 9/12/14.
 */
public class ViewPageAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Stage> myStages = new ArrayList<Stage>();

    public ViewPageAdapter(FragmentManager fm) {
        super(fm);
        this.addStage(new Stage());
    }

    @Override
    public Fragment getItem(int i) {
        return this.myStages.get(i);
    }

    @Override
    public int getCount() {
        return 0;
    }

    public void addStage(Stage pStage) {
        this.myStages.add(pStage);
    }
}
