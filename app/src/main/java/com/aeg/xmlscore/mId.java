package com.aeg.xmlscore;

/**
 * Created by will on 1/12/14.
 */
public class mId {

    private static mId mId;
    private int id;

    private mId() {
        this.id = 0;
    }

    public static mId getmId() {
        if(mId == null) {
            mId = new mId();
        }

        return mId;
    }

    public int generateId() {
        return ++id;
    }

}
