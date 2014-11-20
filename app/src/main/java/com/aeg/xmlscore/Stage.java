package com.aeg.xmlscore;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by nemo on 9/11/14.
 *
 * This class stands for each section of the stave, containing in each the notes.
 *
 */


public class Stage extends Fragment {

    /*private ArrayList<Note> stageNotes;

    /**
     * Will take values of:
     * -. 0                 When created.
     * -. Measure>=N>0      Meanwhile.
     * -. -1                Emptied.
     */
    private ArrayList<Note> stageNotes;

    public Stage(){
        this.stageNotes = new ArrayList<Note>();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_stave, container, false);


        ImageView iv = (ImageView)v.findViewById(R.id.am);
        iv.setOnDragListener(new DragListener(this));

        iv = (ImageView) v.findViewById(R.id.aM);
        iv.setOnDragListener(new DragListener(this));

        iv = (ImageView) v.findViewById(R.id.aMb);
        iv.setOnDragListener(new DragListener(this));

        iv = (ImageView) v.findViewById(R.id.bm);
        iv.setOnDragListener(new DragListener(this));

        iv = (ImageView) v.findViewById(R.id.bM);
        iv.setOnDragListener(new DragListener(this));

        iv = (ImageView) v.findViewById(R.id.bmb);
        iv.setOnDragListener(new DragListener(this));

        iv = (ImageView) v.findViewById(R.id.cm);
        iv.setOnDragListener(new DragListener(this));

        iv = (ImageView) v.findViewById(R.id.cM);
        iv.setOnDragListener(new DragListener(this));

        iv = (ImageView) v.findViewById(R.id.cMM);
        iv.setOnDragListener(new DragListener(this));

        iv = (ImageView) v.findViewById(R.id.dm);
        iv.setOnDragListener(new DragListener(this));

        iv = (ImageView) v.findViewById(R.id.dM);
        iv.setOnDragListener(new DragListener(this));

        iv = (ImageView) v.findViewById(R.id.dMb);
        iv.setOnDragListener(new DragListener(this));

        iv = (ImageView) v.findViewById(R.id.em);
        iv.setOnDragListener(new DragListener(this));

        iv = (ImageView) v.findViewById(R.id.eM);
        iv.setOnDragListener(new DragListener(this));

        iv = (ImageView) v.findViewById(R.id.emb);
        iv.setOnDragListener(new DragListener(this));

        iv = (ImageView) v.findViewById(R.id.fm);
        iv.setOnDragListener(new DragListener(this));

        iv = (ImageView) v.findViewById(R.id.fM);
        iv.setOnDragListener(new DragListener(this));

        iv = (ImageView) v.findViewById(R.id.fMb);
        iv.setOnDragListener(new DragListener(this));

        iv = (ImageView) v.findViewById(R.id.gm);
        iv.setOnDragListener(new DragListener(this));

        iv = (ImageView) v.findViewById(R.id.gM);
        iv.setOnDragListener(new DragListener(this));

        iv = (ImageView) v.findViewById(R.id.gmb);
        iv.setOnDragListener(new DragListener(this));

        return v;
    }

    public int totalWeight() {
        int r = 0;
        Iterator<Note> it = this.stageNotes.iterator();
        while(it.hasNext()) {
            r += it.next().getWeight();
        }

        return r;
    }

    public void addNote(Note pNote) {

        if(this.totalWeight() + pNote.getWeight() <= 0) {
            this.stageNotes.add(pNote);
        } else {
            Toast.makeText(getActivity(), "This note doesn't fit", Toast.LENGTH_SHORT).show();
        }
    }
}
