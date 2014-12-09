package com.aeg.xmlscore;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by nemo on 9/12/14.
 */
public class ViewPageAdapter extends FragmentStatePagerAdapter{

    private ArrayList<Stage> myStages = new ArrayList<Stage>();
    private Context ctx;

    public ViewPageAdapter(FragmentManager fm, Context ctx) {
        super(fm);
        this.ctx = ctx;
        this.addStage();

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
        //Toast.makeText(ctx, String.valueOf(myStages.size() + 1), Toast.LENGTH_SHORT).show();
        Stage stg = new Stage();
        Bundle bundle = new Bundle();
        bundle.putInt("POSITION", myStages.size() + 1);
        stg.setArguments(bundle);
        this.myStages.add(stg);
        this.notifyDataSetChanged();
        //this.setPrimaryItem(, myStages.size(),);
    }



}
