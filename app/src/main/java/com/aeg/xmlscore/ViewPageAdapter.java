package com.aeg.xmlscore;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

/**
 * Created by nemo on 9/12/14.
 */
public class ViewPageAdapter extends FragmentStatePagerAdapter implements ViewPager.OnPageChangeListener{

    private ArrayList<Stage> myStages = new ArrayList<Stage>();
    private ViewPager pager;

    public ViewPageAdapter(FragmentManager fm, ViewPager pVp) {
        super(fm);
        this.pager = pVp;
        this.pager.setAdapter(this);
        this.pager.setOnPageChangeListener(this);
    }

    @Override
    public Fragment getItem(int i) {
        return this.myStages.get(i);
    }

    @Override
    public int getCount() {
        return this.myStages.size();
    }

    public void addStage(Stage pStage) {
        this.myStages.add(pStage);
    }

    private void update() {
        this.pager.setAdapter(null);
        this.pager.setAdapter(this);
    }

    @Override
    public void onPageScrolled(int i, float v, int i2) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
