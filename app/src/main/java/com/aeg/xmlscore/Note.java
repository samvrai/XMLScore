package com.aeg.xmlscore;

import android.graphics.Bitmap;

/**
 * Created by nemo on 9/11/14.
 *
 * Image size: 175x321
 */
public class Note {

    private String name;
    private String type;
    private float weight;
    private float nWeight;
    private boolean rest;
    private int stage;
    private int id;
    private int octave;
    private boolean flagF, flagS, flagN, flagD, flagO;


    public Note(String name, float weight, int stage, int octave, String ptype, boolean rest) {
        this.name = name;
        this.weight = weight;
        this.nWeight = weight;
        flagF = false;
        flagS = false;
        flagO = true;
        this.octave = octave;
        this.stage = stage;
        this.type = ptype;
        id = mId.getmId().generateId();
        this.rest = rest;
    }

    public void setFlagS() {
        this.flagO = false;
        this.flagS = true;
        this.flagF = false;
        this.flagN = false;
    }

    public boolean isFlagF() { return this.flagF;}
    public boolean isFlagS() { return this.flagS;}

    public boolean isFlagN() {
        return flagN;
    }

    public boolean isFlagD() {
        return flagD;
    }
    public boolean isFlagO() { return flagO; }

    public void setFlagD(boolean flagD) {
        this.flagD = flagD;
        if(flagD) {
            this.weight += this.weight/2;
        } else {
            this.weight = this.nWeight;

        }
    }

    public void setFlagN(boolean fn) {
        if(fn) {
            flagN = true;
            flagF = false;
            flagS = false;
            flagO = true;
        } else {
            flagN = false;
        }
    }

    public void setFlagO() {
        this.flagO = true;
        this.flagS = false;
        this.flagF = false;

    }

    public void setFlagF() {
        this.flagO = false;
        this.flagS = false;
        this.flagF = true;
        this.flagN = false;
    }

    public String getName() {
        return name;
    }

    public float getWeight() {
        return weight;
    }


    public int getStage() {
        return stage;
    }

    public int getId() { return id;}

    public int getOctave() { return octave;}

    public String getType() { return type;}
    public boolean isRest() { return rest;}

    public void defaultFlags(boolean fs, boolean ff, boolean fo, boolean fd, boolean fn) {
        flagN = fn;
        flagF = ff;
        flagS = fs;
        flagO = fo;
        flagD = fd;
    }
}
