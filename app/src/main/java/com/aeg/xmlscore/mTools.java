package com.aeg.xmlscore;


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
                weight = 16;
                break;
            case R.id.half:
                weight = 8;
                break;
            case R.id.quarter:
                weight = 4;
                break;
            case R.id.eight:
                weight = 2;
                break;
            case R.id.sixteenth:
                weight = 1;
                break;
        }

        return weight;
    }

    public String droppedN(int id) {
        String name = null;
        switch (id) {
            case R.id.am:
                name = "am";
                break;
            case R.id.aMb:
                name = "aM";
                break;
            case R.id.bM:
                name = "bM";
                break;
            case R.id.bmb:
                name = "bm";
                break;
            case R.id.cm:
                name = "cm";
                break;
            case R.id.cM:
                name = "cM";
                break;
            case R.id.cMM:
                name = "cMM";
                break;
            case R.id.dm:
                name = "dm";
                break;
            case R.id.dMb:
                name = "dM";
                break;
            case R.id.eM:
                name = "eM";
                break;
            case R.id.emb:
                name = "em";
                break;
            case R.id.fm:
                name = "fm";
                break;
            case R.id.fMb:
                name = "fM";
                break;
            case R.id.gM:
                name = "gM";
                break;
            case R.id.gmb:
                name = "gm";
                break;

        }

        return name;
    }

    public void relocate(int stage, ViewGroup vg) {
        ArrayList<Note> al = mNoteManager.getNoteManager().notesAtStage(stage);

        float step = MEASURE / (al.size() + 1);
        float accumulate = 0;

        Iterator<Note> it = al.iterator();
        Note dummy;
        int i = 0;

        while(it.hasNext()) {
            accumulate += step;
            dummy = it.next();
            dummy.setPosX(accumulate -150);

            vg.getChildAt(i).setX(accumulate -150);
            i++;
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

            float y = mNoteManager.getNoteManager().getNoteY(dummy.getName());
            if(y < 450) {
                iv.setRotationX(180);
                iv.setRotationY(180);
                y -= 50;
            } else {
                y -= 230;
            }

            iv.setY(y);
            iv.setX(dummy.getPosx());
            iv.setId(dummy.getId());
            //Toast.makeText(stage.getActivity(), String.valueOf(iv.getY()), Toast.LENGTH_SHORT).show();
            iv.setOnClickListener(new ClickListener(vg.getContext()));


            vg.addView(iv);
        }
    }
}
