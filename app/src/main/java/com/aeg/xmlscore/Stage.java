package com.aeg.xmlscore;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by nemo on 9/11/14.
 *
 * This class stands for each section of the stave, containing in each the notes.
 *
 */


public class Stage extends Fragment {

    private ArrayList<Note> stageNotes;
    private StaveDrawer drawer;

    /**
     * Will take values of:
     * -. 0                 When created.
     * -. Measure>=N>0      Meanwhile.
     * -. -1                Emptied.
     */
    private int howManyNotes = 0;

    public Stage() {
        this.stageNotes = new ArrayList<Note>();
        Context ctx = getActivity();
        this.drawer = new StaveDrawer(ctx);

        ViewPager vp = (ViewPager) getView().findViewById(R.id.lStave);
        vp.addView(drawer);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stave, container, false);
    }



    public int filled() {
        return this.howManyNotes;
    }

    public int addNote(Note pNote) {
        this.stageNotes.add(pNote);
        return 0;
    }
}
