package com.aeg.xmlscore;

import android.graphics.Bitmap;

/**
 * Created by nemo on 9/11/14.
 *
 * Image size: 175x321
 */
public class Note {

    private String name;
    private float weight;
    private float nWeight;

    private int stage;
    private int id;
    private Bitmap image;
    private boolean flagB, flagS, flagN, flagD;


    public Note(String name, float weight, int stage, Bitmap image) {
        this.name = name;
        this.weight = weight;
        this.nWeight = weight;
        flagB = false;
        flagS = false;
        this.stage = stage;
        this.image = image;
        id = mId.getmId().generateId();
    }

    public void setFlagB(boolean flagB) {
        this.flagB = flagB;
    }

    public void setFlagS(boolean flagS) {
        this.flagS = flagS;
    }

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

    public String getName() {

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
}
