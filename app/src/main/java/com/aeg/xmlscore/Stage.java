package com.aeg.xmlscore;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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



        View v = inflater.inflate(R.layout.fragment_stave, container, false);

        this.loadViews((ViewGroup)v);

        /**
         * TODO Reformat this.
         *
         *
         */

        //ImageView iv;

        /*for(int i = 0; i <= container.getChildCount(); i++) {
            iv = (ImageView)container.getChildAt(i);
            iv.setOnDragListener(new DragListener(this));
        }*/


        ImageView iv = (ImageView) v.findViewById(R.id.am);
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

    public int getPosition() {
        return position;
    }

    public void loadViews(ViewGroup v) {
        int s = this.getPosition();
        Toast.makeText(getActivity(), String.valueOf(mNoteManager.getNoteManager().notesAtStage(s).size()), Toast.LENGTH_SHORT).show();
        ArrayList<Note> al = mNoteManager.getNoteManager().notesAtStage(getPosition());
        Iterator<Note> it = al.iterator();
        Note dummy = null;
        while(it.hasNext()) {
            dummy = it.next();
            ImageView iv = new ImageView(this.getActivity());
            iv.setImageBitmap(dummy.getImage());
            iv.setX(dummy.getPosx());
            iv.setY(dummy.getPosy());

            v.addView(iv);
        }
    }
}
