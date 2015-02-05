package com.aeg.xmlscore;

/**
 * Created by will on 9/12/14.
 */
public class mMeasureCounter {

    private static mMeasureCounter mMC;
    private int num, den, max;
    private int keyNum;

    private mMeasureCounter() {

    }

    public static mMeasureCounter getmMC() {
        if(mMC == null) {
            mMC = new mMeasureCounter();
        }
        return mMC;
    }

    private void setMax() {
        if(den == 4) {
            max = num;
        }
        else {
            max = num / 2;
        }
    }

    public boolean check(float weight, float acum) {
        boolean res = false;
        if(weight + acum <= max) {
            res = true;
        }
        return res;
    }

    public void setArguments(int num, int den, int pkey) {
        this.num = num;
        this.den = den;
        this.keyNum = pkey;
        this.setMax();
    }

    public float getMax() { return this.max;}

    public int getKey() { return this.keyNum;}
    public int getNum() { return num;}
    public int getDen() { return den;}

}
