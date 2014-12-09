package com.aeg.xmlscore;

import android.graphics.Bitmap;

/**
 * Created by nemo on 9/11/14.
 *
 * Image size: 175x321
 */
public class Note {

    private String name;
    private double weight;
    private int stage;
    private long id;
    private Bitmap image;
    private boolean flagB, flagS;
    private float posX, posY;

    public Note(String name, double weight, int stage, Bitmap image, float posy) {
        this.name = name;
        this.weight = weight;
        flagB = false;
        flagS = false;
        this.stage = stage;
        this.image = image;
        posY = posy;
        id = mId.getmId().generateId();
    }

    public void setFlagB(boolean flagB) {
        this.flagB = flagB;
    }

    public void setFlagS(boolean flagS) {
        this.flagS = flagS;
    }

    public void setPosX(float posx) { this.posX = posx;}

    public String getName() {

        return name;
    }

    public double getWeight() {
        return weight;
    }

    public boolean getFlagB() {
        return flagB;
    }

    public boolean getFlagS() {
        return flagS;
    }

    public float getPosx() {
        return posX;
    }

    public float getPosy() {
        return posY;
    }

    public int getStage() {
        return stage;
    }

    public Bitmap getImage() {
        return image;
    }

    public long getId() { return id;}
}
