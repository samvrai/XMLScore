package com.aeg.xmlscore;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by will on 19/11/14.
 */
public class NotesAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> notePages = new ArrayList<Fragment>();

    public NotesAdapter(FragmentManager fm) {
        super(fm);
        notePages.add(new NoteDrawerB());
        NoteDrawerA nda = new NoteDrawerA();
        notePages.add(nda);
        //TODO Add the other drawer.


    }

    @Override
    public Fragment getItem(int i) {
        return notePages.get(i);
    }

    @Override
    public int getCount() {
        return notePages.size();
    }
}
