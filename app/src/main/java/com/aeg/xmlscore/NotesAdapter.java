package com.aeg.xmlscore;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by will on 19/11/14.
 */
public class NotesAdapter extends FragmentStatePagerAdapter {

    private ArrayList<NoteDrawerA> notePages = new ArrayList<NoteDrawerA>();

    public NotesAdapter(FragmentManager fm) {
        super(fm);
        notePages.add(new NoteDrawerA());
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
