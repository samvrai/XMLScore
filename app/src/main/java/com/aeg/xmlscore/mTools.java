package com.aeg.xmlscore;


import android.content.res.Resources;
import android.graphics.Bitmap;
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
    private final int MEASURE = 880;
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
            case R.id.wholerest:
                weight = 64;
                break;
            case R.id.half:
                weight = 32;
                break;
            case R.id.halfrest:
                weight = 32;
                break;
            case R.id.quarter:
                weight = 16;
                break;
            case R.id.quarterrest:
                weight = 16;
                break;
            case R.id.eighth:
                weight = 8;
                break;
            case R.id.eighthrest:
                weight = 8;
                break;
            case R.id.sixteenth:
                weight = 4;
                break;
            case R.id.sixteenthrest:
                weight = 4;
                break;
            case R.id.thirtytwoth:
                weight = 2;
                break;
            case R.id.thirtytwothrest:
                weight = 2;
                break;
            case R.id.sixtyfourth:
                weight = 1;
                break;
            case R.id.sixtyfourthrest:
                weight = 1;
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
            case R.id.wholerest:
                weight = "whole";
                break;
            case R.id.half:
                weight = "half";
                break;
            case R.id.halfrest:
                weight = "half";
                break;
            case R.id.quarter:
                weight = "quarter";
                break;
            case R.id.quarterrest:
                weight = "quarter";
                break;
            case R.id.eighth:
                weight = "eighth";
                break;
            case R.id.eighthrest:
                weight = "eighth";
                break;
            case R.id.sixteenth:
                weight = "16th";
                break;
            case R.id.sixteenthrest:
                weight = "16th";
                break;
            case R.id.thirtytwoth:
                weight = "32th";
                break;
            case R.id.thirtytwothrest:
                weight = "32th";
                break;
            case R.id.sixtyfourth:
                weight = "64th";
                break;
            case R.id.sixtyfourthrest:
                weight = "64th";
                break;
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
        switch(id) {
            case R.id.wholerest:
                return true;
            case R.id.halfrest:
                return true;
            case R.id.quarterrest:
                return true;
            case R.id.eighthrest:
                return true;
            case R.id.sixteenthrest:
                return true;
            case R.id.thirtytwothrest:
                return true;
            case R.id.sixtyfourthrest:
                return true;
            default:
                return false;
        }
    }

    public void relocate(int stage, ViewGroup vg) {

        Iterator<Note> it = mNoteManager.getNoteManager().notesAtStage(stage).iterator();

        float step = MEASURE / (mNoteManager.getNoteManager().notesAtStage(stage).size() + 1);
        float accumulate = 200;

        vg.removeAllViews();

        while(it.hasNext()) {
            Note note = it.next();
            ImageView iv = new ImageView(vg.getContext());
            iv.setId(note.getId());
            iv.setImageBitmap(bitmapper(note.getType(), note.isRest(), vg.getResources()));
            iv.setScaleX(0.8f);
            iv.setScaleY(0.8f);

            accumulate += step;
            iv.setX(accumulate -150);
            iv.setContentDescription(vg.getContext().getString(R.string.added_notes));

            iv.setOnClickListener(new ClickListener(vg.getContext()));

            if(!note.isRest()) {
                float y = mNoteManager.getNoteManager().getNoteY(note.getName(), note.getOctave());
                if(y < 450) {
                    iv.setRotationX(180);
                    iv.setRotationY(180);
                    y -= 55;
                } else {
                    y -= 268;
                }
                iv.setY(y);
                if(note.getOctave() == 4 && note.getName() == 'C') {
                    ImageView lc = new ImageView(vg.getContext());
                    Bitmap bmc = BitmapFactory.decodeResource(vg.getResources(), R.drawable.blackline2);
                    lc.setImageBitmap(bmc);

                    if(note.getType() == "whole") {
                        lc.setScaleX(0.1f);
                        lc.setX(iv.getX() - 482);
                    } else {
                        lc.setScaleX(0.09f);
                        lc.setX(iv.getX() - 490);
                    }

                    lc.setY(600);
                    vg.addView(lc);
                }
                if(note.getOctave() == 4 && note.getName() == 'D') {
                    ImageView ld = new ImageView(vg.getContext());
                    Bitmap bmd = BitmapFactory.decodeResource(vg.getResources(), R.drawable.blackline2);
                    ld.setImageBitmap(bmd);

                    if(note.getType() == "whole") {
                        ld.setScaleX(0.1f);
                        ld.setX(iv.getX() - 482);
                    } else {
                        ld.setScaleX(0.09f);
                        ld.setX(iv.getX() - 490);
                    }
                    ld.setY(570);
                    vg.addView(ld);
                }
                if(note.getOctave() == 5 && note.getName() == 'G') {
                    ImageView lg = new ImageView(vg.getContext());
                    Bitmap bmg = BitmapFactory.decodeResource(vg.getResources(), R.drawable.blackline2);
                    lg.setImageBitmap(bmg);

                    if(note.getType() == "whole") {
                        lg.setScaleX(0.1f);
                        lg.setX(iv.getX() - 452);
                    } else {
                        lg.setScaleX(0.09f);
                        lg.setX(iv.getX() - 444);
                    }
                    lg.setY(270);
                    vg.addView(lg);
                }
                if(note.getOctave() == 5 && note.getName() == 'A') {
                    ImageView la = new ImageView(vg.getContext());
                    Bitmap bma = BitmapFactory.decodeResource(vg.getResources(), R.drawable.blackline2);
                    la.setImageBitmap(bma);

                    if(note.getType() == "whole") {
                        la.setScaleX(0.1f);
                        la.setX(iv.getX() - 452);
                    } else {
                        la.setScaleX(0.09f);
                        la.setX(iv.getX() - 444);
                    }
                    la.setY(240);
                    vg.addView(la);
                }
                if(note.getOctave() == 5 && note.getName() == 'B') {
                    ImageView lb = new ImageView(vg.getContext());
                    Bitmap bmb = BitmapFactory.decodeResource(vg.getResources(), R.drawable.blackline2);
                    lb.setImageBitmap(bmb);

                    if(note.getType() == "whole") {
                        lb.setScaleX(0.1f);
                        lb.setX(iv.getX() - 452);
                    } else {
                        lb.setScaleX(0.09f);
                        lb.setX(iv.getX() - 444);
                    }
                    lb.setY(210);
                    vg.addView(lb);
                }
                if(note.getOctave() == 6 && note.getName() == 'C') {
                    ImageView lc2 = new ImageView(vg.getContext());
                    Bitmap bmc2 = BitmapFactory.decodeResource(vg.getResources(), R.drawable.blackline2);
                    lc2.setImageBitmap(bmc2);
                    lc2.setScaleX(0.1f);
                    if(note.getType() == "whole") {
                        lc2.setScaleX(0.1f);
                        lc2.setX(iv.getX() - 452);
                    } else {
                        lc2.setScaleX(0.09f);
                        lc2.setX(iv.getX() - 444);
                    }
                    lc2.setY(180);
                    vg.addView(lc2);
                }
            } else {
                switch (note.getType()) {
                    case "whole":
                        iv.setY(245);
                        break;
                    case "half":
                        iv.setY(211);
                        break;
                    case "quarter":
                        iv.setScaleX(1f);
                        iv.setScaleY(1f);
                        iv.setY(177);
                        break;
                    case "eighth":
                        iv.setScaleX(1f);
                        iv.setScaleY(1f);
                        iv.setY(143);
                        break;
                    case "16th":
                        iv.setScaleX(1f);
                        iv.setScaleY(1f);
                        iv.setY(148);
                        break;
                    case "32th":
                        iv.setScaleX(1f);
                        iv.setScaleY(1f);
                        iv.setY(210);
                        break;
                    case "64th":
                        iv.setScaleX(1f);
                        iv.setScaleY(1f);
                        iv.setY(210);
                        break;
                }
            }

            if(note.isFlagS()) {
                ImageView altS = new ImageView(vg.getContext());
                Bitmap bm = BitmapFactory.decodeResource(vg.getResources(), R.drawable.sharp);
                altS.setImageBitmap(bm);
                altS.setScaleX(0.7f);
                altS.setScaleY(0.7f);
                altS.setX(iv.getX() - 45);
                altS.setY(iv.getY() + 180);
                vg.addView(altS);
            }
            if(note.isFlagF()) {
                ImageView altS = new ImageView(vg.getContext());
                Bitmap bm = BitmapFactory.decodeResource(vg.getResources(), R.drawable.flat);
                altS.setImageBitmap(bm);
                altS.setScaleX(0.7f);
                altS.setScaleY(0.7f);
                altS.setX(iv.getX() - 40);
                altS.setY(iv.getY() + 170);
                vg.addView(altS);
            }
            if(note.isFlagD()) {
                ImageView altS = new ImageView(vg.getContext());
                Bitmap bm = BitmapFactory.decodeResource(vg.getResources(), R.drawable.dot);
                altS.setImageBitmap(bm);
                altS.setScaleX(0.8f);
                altS.setScaleY(0.8f);
                altS.setX(iv.getX() + 80);
                altS.setY(iv.getY() + 260);
                vg.addView(altS);
            }
            if(note.isFlagN()) {
                ImageView altS = new ImageView(vg.getContext());
                Bitmap bm = BitmapFactory.decodeResource(vg.getResources(), R.drawable.natural);
                altS.setImageBitmap(bm);
                altS.setScaleX(0.7f);
                altS.setScaleY(0.7f);
                altS.setX(iv.getX() - 35);
                altS.setY(iv.getY() + 193);
                vg.addView(altS);
            }
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
            iv.setImageBitmap(bitmapper(dummy.getType(), dummy.isRest(), vg.getResources()));

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

    private Bitmap bitmapper(String type, boolean rest, Resources res) {
        if(!rest) {
            switch (type) {
                case "whole":
                    return BitmapFactory.decodeResource(res, R.drawable.whole);

                case "half":
                    return BitmapFactory.decodeResource(res, R.drawable.half);

                case "quarter":
                    return BitmapFactory.decodeResource(res, R.drawable.quarter);

                case "eighth":
                    return BitmapFactory.decodeResource(res, R.drawable.eighth);

                case "16th":
                    return BitmapFactory.decodeResource(res, R.drawable.sixteenth);

                case "32th":
                    return BitmapFactory.decodeResource(res, R.drawable.thirtytwoth);

                case "64th":
                    return BitmapFactory.decodeResource(res, R.drawable.sixtyfourth);
                default:
                    return null;
            }
        } else {
            switch (type) {
                case "whole":
                    return BitmapFactory.decodeResource(res, R.drawable.wholerest);

                case "half":
                    return BitmapFactory.decodeResource(res, R.drawable.halfrest);

                case "quarter":
                    return BitmapFactory.decodeResource(res, R.drawable.quarterrest);

                case "eighth":
                    return BitmapFactory.decodeResource(res, R.drawable.eighthrest);

                case "16th":
                    return BitmapFactory.decodeResource(res, R.drawable.sixteenthrest);

                case "32th":
                    return BitmapFactory.decodeResource(res, R.drawable.thirtytwothrest);

                case "64th":
                    return BitmapFactory.decodeResource(res, R.drawable.sixtyfourthrest);
                default:
                    return null;
            }
        }
    }
}
