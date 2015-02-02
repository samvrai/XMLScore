package com.aeg.xmlscore;

import android.graphics.Bitmap;

/**
 * Created by nemo on 9/11/14.
 *
 * Image size: 175x321
 */
public class Note {

    private char name;
    private String type;
    private float weight;
    private float nWeight;
    private boolean rest;
    private int stage;
    private int id;
    private int octave;
    private Bitmap image;
    private boolean flagF, flagS, flagN, flagD;


    public Note(char name, float weight, int stage, int octave, Bitmap image, String ptype, boolean rest) {
        this.name = name;
        this.weight = weight;
        this.nWeight = weight;
        flagF = false;
        flagS = false;
        this.octave = octave;
        this.stage = stage;
        this.image = image;
        this.type = ptype;
        id = mId.getmId().generateId();
        this.rest = rest;
    }

    public void setFlagB(boolean flagF) {
        this.flagF = flagF;
    }

    public void setFlagS(boolean flagS) {
        this.flagS = flagS;
    }

    public boolean isFlagF() { return this.flagF;}
    public boolean isFlagS() { return this.flagS;}

    public boolean isFlagN() {
        return flagN;
    }

    public boolean isFlagD() {
        return flagD;
    }

    public void setFlagD(boolean flagD) {
        this.flagD = flagD;
        if(flagD) {
            this.weight += this.weight/2;
        } else {
            this.weight = this.nWeight;

        }
    }

    public void setFlagN(boolean flagN) {
        this.flagN = flagN;
    }

    public char getName() {
        return name;
    }

    public float getWeight() {
        return weight;
    }


    public int getStage() {
        return stage;
    }

    public Bitmap getImage() {
        return image;
    }

    public int getId() { return id;}

    public int getOctave() { return octave;}

    public String getType() { return type;}
    public boolean isRest() { return rest;}
}
