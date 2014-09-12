package com.aeg.xmlscore;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by nemo on 9/11/14.
 *
 * This class stands for each section of the stave, containing in each the notes.
 *
 */


public class Stage extends Fragment {

    private ArrayList<Note> stageNotes;
    private Canvas stavePiece;
    private StaveDrawer drawer;
    private LinearLayout lLayout;
    private int index;

    /**
     * Will take values of:
     * -. 0                 When created.
     * -. Measure>=N>0      Meanwhile.
     * -. -1                Emptied.
     */
    private int howManyNotes = 0;

    public Stage() {
        this.stageNotes = new ArrayList<Note>();
        this.stavePiece = new Canvas();
        Context ctx = getActivity();
        this.drawer = new StaveDrawer(ctx);
        this.lLayout = new LinearLayout(this.getActivity());
        lLayout.addView(drawer);

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
