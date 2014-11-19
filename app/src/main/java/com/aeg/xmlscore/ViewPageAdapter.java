package com.aeg.xmlscore;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

/**
 * Created by nemo on 9/12/14.
 */
public class ViewPageAdapter extends FragmentStatePagerAdapter{

    private ArrayList<Stage> myStages = new ArrayList<Stage>();


    public ViewPageAdapter(FragmentManager fm) {
        super(fm);
        myStages.add(new Stage());
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
        Bundle bundle = new Bundle();
        bundle.putInt("number", this.getCount());
        Stage stage = new Stage();
        stage.setArguments(bundle);
        this.myStages.add(stage);
        this.notifyDataSetChanged();
    }

}
