package com.aeg.xmlscore;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by nemo on 9/12/14.
 *
 */
public class ViewPageAdapter extends FragmentStatePagerAdapter{

    private ArrayList<Stage> myStages = new ArrayList<Stage>();

    public ViewPageAdapter(FragmentManager fm) {
        super(fm);
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
        Stage stg = new Stage();
        Bundle bundle = new Bundle();
        bundle.putInt("POSITION", myStages.size() + 1);
        stg.setArguments(bundle);
        this.myStages.add(stg);
        this.notifyDataSetChanged();
    }

    public void remove() {
        this.myStages.remove(myStages.size() - 1);
        this.notifyDataSetChanged();
    }



}
