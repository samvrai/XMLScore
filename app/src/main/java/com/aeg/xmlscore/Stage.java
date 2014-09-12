package com.aeg.xmlscore;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by nemo on 9/11/14.
 *
 * This class stands for each section of the stave, containing in each the notes.
 *
 */


public class Stage extends Fragment {

    public static final String ARG_OBJECT = "object";
    private ArrayList<Note> stageNotes;
    private Canvas stavePiece;
    private StaveDrawer drawer;
    private LinearLayout lLayout;

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
        this.drawer = new StaveDrawer(this.getActivity());
        this.lLayout = new LinearLayout(this.getActivity());
        lLayout.addView(drawer);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }



    public int filled() {
        return this.howManyNotes;
    }

    public int addNote(Note pNote) {
        this.stageNotes.add(pNote);
        return 0;
    }
}
