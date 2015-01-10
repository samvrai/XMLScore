package com.aeg.xmlscore;

/**
 * Created by will on 5/01/15.
 */
public class NotePos {
    private String name;
    private float position;

    public NotePos(String pName, float pPos) {
        this.name = pName;
        this.position = pPos;
    }

    public String getName() {return this.name;}
    public float getPosition() {return this.position;}
}
