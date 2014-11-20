package com.aeg.xmlscore;

/**
 * Created by nemo on 9/11/14.
 *
 * Image size: 175x321
 */
public class Note {

    private String name;
    private double weight;
    private int flagB, flagS;

    public Note(String name, double weight) {
        this.name = name;
        this.weight = weight;
        flagB = 0;
        flagS = 0;
    }

    public void setFlagB(int flagB) {
        this.flagB = flagB;
    }

    public void setFlagS(int flagS) {
        this.flagS = flagS;
    }

    public String getName() {

        return name;
    }

    public double getWeight() {
        return weight;
    }

    public int getFlagB() {
        return flagB;
    }

    public int getFlagS() {
        return flagS;
    }
}
