package com.aeg.xmlscore;

import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by will on 24/11/14.
 */
public class mNoteManager {

    private static mNoteManager mNM = null;
    private ArrayList<Note> noteList;

    private mNoteManager() {
        this.noteList = new ArrayList<Note>();
    }

    public static mNoteManager getNoteManager() {
        if(mNM == null) {
            mNM = new mNoteManager();
        }

        return mNM;
    }

    public void addNote(Note pNote) {
        this.noteList.add(pNote);
    }

    public ArrayList<Note> notesAtStage(int stage) {
        ArrayList<Note> result = new ArrayList<Note>();
        Iterator<Note> it = this.noteList.iterator();
        Note note = null;

        while(it.hasNext()) {
            note = it.next();
            if(note.getStage() == stage) {
                result.add(note);
            }
        }

        return result;
    }

    public int getStageSize(int stage) {
        int res = 0;
        Iterator<Note> it = this.noteList.iterator();
        Note note = null;

        while(it.hasNext()) {
            note = it.next();
            if(note.getStage() == stage) {
                res++;
            }
        }

        return res;
    }


    private int howManyStages() {
        int res = 0;
        int actual = 0;
        Iterator<Note> it = this.noteList.iterator();
        Note note = null;
        while(it.hasNext()) {
            note = it.next();
            if(note.getStage() != actual) {
                res++;
                actual = note.getStage();
            }
        }

        return res;
    }
}
