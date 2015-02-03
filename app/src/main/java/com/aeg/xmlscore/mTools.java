package com.aeg.xmlscore;


import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by will on 25/11/14.
 */
public class mTools {

    private static mTools mtools = null;
    private final int MEASURE = 1050;
    private String name;

    private mTools() {

    }

    public static mTools getTools() {
        if(mtools == null) {
            mtools = new mTools();
        }

        return mtools;
    }

    public float droppedW(int id) {
        float weight = 0;

        switch (id) {
            case R.id.whole:
                weight = 64;
                break;
            case R.id.half:
                weight = 32;
                break;
            case R.id.quarter:
                weight = 16;
                break;
            case R.id.eighth:
                weight = 8;
                break;
            case R.id.sixteenth:
                weight = 4;
                break;

        }

        return weight;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String droppedT(int id) {
        String weight = "";
        switch (id) {
            case R.id.whole:
                weight = "whole";
                break;
            case R.id.half:
                weight = "half";
                break;
            case R.id.quarter:
                weight = "quarter";
                break;
            case R.id.eighth:
                weight = "eighth";
                break;
            case R.id.sixteenth:
                weight = "16th";
                break;

            /**
             * case R.id.thirtysecond:
             *  weight = "32th";
             *  break;
             *case R.id.sixtyfourth:
             *  weight = "64th";
             *  break;
             */
        }

        return weight;
    }

    public char droppedN(int id) {
        char name = ' ';
        switch (id) {
            case R.id.am:
                name = 'A';
                break;
            case R.id.aMb:
                name = 'A';
                break;
            case R.id.bM:
                name = 'B';
                break;
            case R.id.bmb:
                name = 'B';
                break;
            case R.id.cm:
                name = 'C';
                break;
            case R.id.cM:
                name = 'C';
                break;
            case R.id.cMM:
                name = 'C';
                break;
            case R.id.dm:
                name = 'D';
                break;
            case R.id.dMb:
                name = 'D';
                break;
            case R.id.eM:
                name = 'E';
                break;
            case R.id.emb:
                name = 'E';
                break;
            case R.id.fm:
                name = 'F';
                break;
            case R.id.fMb:
                name = 'F';
                break;
            case R.id.gM:
                name = 'G';
                break;
            case R.id.gmb:
                name = 'G';
                break;

        }
        return name;
    }

    public int droppedOctave(int id) {
        int name = 0;
        switch (id) {
            case R.id.cm:
                name = 4;
                break;
            case R.id.dm:
                name = 4;
                break;
            case R.id.emb:
                name = 4;
                break;
            case R.id.fm:
                name = 4;
                break;
            case R.id.gmb:
                name = 4;
                break;
            case R.id.am:
                name = 4;
                break;
            case R.id.bmb:
                name = 4;
                break;


            case R.id.cM:
                name = 5;
                break;
            case R.id.dMb:
                name = 5;
                break;
            case R.id.eM:
                name = 5;
                break;
            case R.id.fMb:
                name = 5;
                break;
            case R.id.gM:
                name = 5;
                break;
            case R.id.aMb:
                name = 5;
                break;
            case R.id.bM:
                name = 5;
                break;

            case R.id.cMM:
                name = 6;
                break;
        }
        return name;
    }

    public boolean isRest(int id) {

        return false;
    }

    public void relocate(int stage, ViewGroup vg) {

        Iterator<Note> it = mNoteManager.getNoteManager().notesAtStage(stage).iterator();

        float step = MEASURE / (mNoteManager.getNoteManager().notesAtStage(stage).size() + 1);
        float accumulate = 120;

        vg.removeAllViews();

        while(it.hasNext()) {
            Note note = it.next();
            ImageView iv = new ImageView(vg.getContext());
            iv.setId(note.getId());
            iv.setImageBitmap(note.getImage());
            iv.setOnClickListener(new ClickListener(vg.getContext()));
            iv.setContentDescription(vg.getContext().getString(R.string.added_notes));
            accumulate += step;
            iv.setX(accumulate -150);
            float y = mNoteManager.getNoteManager().getNoteY(note.getName(), note.getOctave());
            if(y < 450) {
                iv.setRotationX(180);
                iv.setRotationY(180);
                y -= 15;
            } else {
                y -= 200;
            }
            iv.setY(y);
            /*if(note.isFlagF()) {
                ImageView fiv = new ImageView(iv.getContext());
                fiv.setImageResource(R.drawable.flat);
                fiv.setX(iv.getX() - 20);
                fiv.setY(iv.getY());
            } else if(note.isFlagS()) {

            }*/


            vg.addView(iv);
        }

    }

    public void loadViews(Stage stage, ViewGroup vg) {
        //ViewGroup vg = (ViewGroup)stage.getActivity().findViewById(R.id.notePlace);
        ArrayList<Note> al = mNoteManager.getNoteManager().notesAtStage(stage.getPosition());

        vg.removeAllViews();

        Iterator<Note> it = al.iterator();
        Note dummy;
        while(it.hasNext()) {
            dummy = it.next();
            ImageView iv = new ImageView(stage.getActivity());
            iv.setImageBitmap(dummy.getImage());

            float y = mNoteManager.getNoteManager().getNoteY(dummy.getName(), dummy.getOctave());
            if(y < 450) {
                iv.setRotationX(180);
                iv.setRotationY(180);
                y -= 50;
            } else {
                y -= 230;
            }

            iv.setY(y);
            iv.setId(dummy.getId());
            //Toast.makeText(stage.getActivity(), String.valueOf(iv.getY()), Toast.LENGTH_SHORT).show();
            iv.setOnClickListener(new ClickListener(vg.getContext()));
            vg.addView(iv);
        }

        this.relocate(stage.getPosition(), vg);
    }

    public String getName() { return name;}
}
