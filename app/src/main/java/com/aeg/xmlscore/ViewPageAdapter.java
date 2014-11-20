package com.aeg.xmlscore;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by nemo on 9/12/14.
 */
public class ViewPageAdapter extends FragmentStatePagerAdapter{

    private ArrayList<Stage> myStages = new ArrayList<Stage>();


    public ViewPageAdapter(FragmentManager fm) {
        super(fm);
        Stage stg = new Stage();
        myStages.add(stg);
    }

    @Override
    public Fragment getItem(int i) {
        return this.myStages.get(i);
    }

    @Override
    public int getCount() {

        return this.myStages.size();
    }

    public void addStage() {
        this.myStages.add(new Stage());
        this.notifyDataSetChanged();
    }

}
