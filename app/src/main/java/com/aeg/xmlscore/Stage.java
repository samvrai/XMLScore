package com.aeg.xmlscore;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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


    private int position;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            position = (Integer)getArguments().get("POSITION");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        View v = inflater.inflate(R.layout.fragment_stave_2, container, false);

        mTools.getTools().loadViews(this, (ViewGroup)v.findViewById(R.id.notePlace));


        /**
         * TODO Reformat this.
         *
         *
         */


        ImageView iv = (ImageView) v.findViewById(R.id.am);
        iv.setOnDragListener(new DragListener(this));
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams)iv.getLayoutParams();
        //Log.v("Measure", lp.topMargin + " " + getResources().getDisplayMetrics().density);
        mNoteManager.getNoteManager().loadPos(new NotePos("am", lp.topMargin));

        iv = (ImageView) v.findViewById(R.id.aMb);
        iv.setOnDragListener(new DragListener(this));
        lp = (RelativeLayout.LayoutParams)iv.getLayoutParams();
        mNoteManager.getNoteManager().loadPos(new NotePos("aM", lp.topMargin));

        iv = (ImageView) v.findViewById(R.id.bm);
        iv.setOnDragListener(new DragListener(this));
        //mNoteManager.getNoteManager().loadPos(new NotePos("bm", lp.topMargin));

        iv = (ImageView) v.findViewById(R.id.bM);
        iv.setOnDragListener(new DragListener(this));
        lp = (RelativeLayout.LayoutParams)iv.getLayoutParams();
        mNoteManager.getNoteManager().loadPos(new NotePos("bM", lp.topMargin));

        iv = (ImageView) v.findViewById(R.id.bmb);
        iv.setOnDragListener(new DragListener(this));
        lp = (RelativeLayout.LayoutParams)iv.getLayoutParams();
        mNoteManager.getNoteManager().loadPos(new NotePos("bm", lp.topMargin));

        iv = (ImageView) v.findViewById(R.id.cm);
        iv.setOnDragListener(new DragListener(this));
        lp = (RelativeLayout.LayoutParams)iv.getLayoutParams();
        mNoteManager.getNoteManager().loadPos(new NotePos("cm", lp.topMargin));

        iv = (ImageView) v.findViewById(R.id.cM);
        iv.setOnDragListener(new DragListener(this));
        lp = (RelativeLayout.LayoutParams)iv.getLayoutParams();
        mNoteManager.getNoteManager().loadPos(new NotePos("cM", lp.topMargin));

        iv = (ImageView) v.findViewById(R.id.cMM);
        iv.setOnDragListener(new DragListener(this));
        lp = (RelativeLayout.LayoutParams)iv.getLayoutParams();
        mNoteManager.getNoteManager().loadPos(new NotePos("cMM", lp.topMargin));

        iv = (ImageView) v.findViewById(R.id.dm);
        iv.setOnDragListener(new DragListener(this));
        lp = (RelativeLayout.LayoutParams)iv.getLayoutParams();
        mNoteManager.getNoteManager().loadPos(new NotePos("dm", lp.topMargin));

        iv = (ImageView) v.findViewById(R.id.dM);
        iv.setOnDragListener(new DragListener(this));
        lp = (RelativeLayout.LayoutParams)iv.getLayoutParams();
        mNoteManager.getNoteManager().loadPos(new NotePos("dM", lp.topMargin));

        iv = (ImageView) v.findViewById(R.id.dMb);
        iv.setOnDragListener(new DragListener(this));
        //mNoteManager.getNoteManager().loadPos(new NotePos("dM", lp.topMargin));

        iv = (ImageView) v.findViewById(R.id.em);
        iv.setOnDragListener(new DragListener(this));
        lp = (RelativeLayout.LayoutParams)iv.getLayoutParams();
        mNoteManager.getNoteManager().loadPos(new NotePos("em", lp.topMargin));

        iv = (ImageView) v.findViewById(R.id.eM);
        iv.setOnDragListener(new DragListener(this));
        lp = (RelativeLayout.LayoutParams)iv.getLayoutParams();
        mNoteManager.getNoteManager().loadPos(new NotePos("eM", lp.topMargin));

        iv = (ImageView) v.findViewById(R.id.emb);
        iv.setOnDragListener(new DragListener(this));
        lp = (RelativeLayout.LayoutParams)iv.getLayoutParams();
        //mNoteManager.getNoteManager().loadPos(new NotePos("em", lp.topMargin));

        iv = (ImageView) v.findViewById(R.id.fm);
        iv.setOnDragListener(new DragListener(this));
        lp = (RelativeLayout.LayoutParams)iv.getLayoutParams();
        mNoteManager.getNoteManager().loadPos(new NotePos("fm", lp.topMargin));

        iv = (ImageView) v.findViewById(R.id.fM);
        iv.setOnDragListener(new DragListener(this));
        lp = (RelativeLayout.LayoutParams)iv.getLayoutParams();
        mNoteManager.getNoteManager().loadPos(new NotePos("fM", lp.topMargin));

        iv = (ImageView) v.findViewById(R.id.fMb);
        iv.setOnDragListener(new DragListener(this));
        //mNoteManager.getNoteManager().loadPos(new NotePos("am", lp.topMargin));

        iv = (ImageView) v.findViewById(R.id.gm);
        iv.setOnDragListener(new DragListener(this));
        lp = (RelativeLayout.LayoutParams)iv.getLayoutParams();
        mNoteManager.getNoteManager().loadPos(new NotePos("gm", lp.topMargin));

        iv = (ImageView) v.findViewById(R.id.gM);
        iv.setOnDragListener(new DragListener(this));
        lp = (RelativeLayout.LayoutParams)iv.getLayoutParams();
        mNoteManager.getNoteManager().loadPos(new NotePos("gM", lp.topMargin));

        iv = (ImageView) v.findViewById(R.id.gmb);
        iv.setOnDragListener(new DragListener(this));
        //mNoteManager.getNoteManager().loadPos(new NotePos("am", lp.topMargin));

        return v;
    }

    public int getPosition() {
        return position;
    }
}
