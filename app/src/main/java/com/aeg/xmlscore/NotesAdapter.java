package com.aeg.xmlscore;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;

import java.util.ArrayList;

/**
 * Created by will on 19/11/14.
 */
public class NotesAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> notePages = new ArrayList<Fragment>();

    public NotesAdapter(FragmentManager fm) {
        super(fm);
        notePages.add(new NoteDrawerNotes());
        notePages.add(new NoteDrawerRests());
        NoteDrawerAlter nda = new NoteDrawerAlter();
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

    public CheckBox getcNatural() {
        return ((NoteDrawerAlter)notePages.get(2)).getcNatural();
    }

    public RadioButton getAlterN() {
        return ((NoteDrawerAlter)notePages.get(2)).getAlterN();
    }

    public RadioButton getAlterS() {
        return ((NoteDrawerAlter)notePages.get(2)).getAlterS();
    }

    public RadioButton getAlterF() {
        return ((NoteDrawerAlter)notePages.get(2)).getAlterF();
    }

    public CheckBox getcDotted() { return ((NoteDrawerAlter)notePages.get(2)).getcDotted();}
}
