package com.aeg.xmlscore;

import android.content.Context;
import android.util.Log;
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
    private ArrayList<NotePos> notePoses = new ArrayList<NotePos>();
    private Note inTransaction;

    private mNoteManager() {
        this.noteList = new ArrayList<Note>();
    }

    public static mNoteManager getNoteManager() {
        if(mNM == null) {
            mNM = new mNoteManager();
        }

        return mNM;
    }

    public void loadPos(NotePos np) {
        this.notePoses.add(np);
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

    public float stageWeight(int stage) {
        Iterator<Note> it = notesAtStage(stage).iterator();
        float total = 0;

        Note dummy = null;
        while(it.hasNext()) {
            dummy = it.next();
            total += dummy.getWeight();
        }

        return total;
    }

    private float search(String pNom) {
        Iterator<NotePos> it = this.notePoses.iterator();
        float result = 0;
        boolean found = false;
        NotePos np = null;
        while(it.hasNext() && !found) {
            np = it.next();
            //Log.v("Flag",np.getName() + " " + this.notePoses.size() + " " + np.getPosition());
            if(np.getName() == pNom) {
                found = true;
                result = np.getPosition();
            }
        }
        return result;
    }

    public float getNoteY(String pNote) {
        float result = 0;
        Log.v("Flag", pNote);
        switch(pNote) {
            case "am":
                result = this.search("am");
                break;
            case "aM":
                result = this.search("aM");
                break;
            case "bM":
                result = this.search("bM");
                break;
            case "bm":
                result = this.search("bm");
                break;
            case "cm":
                result = this.search("cm");
                break;
            case "cM":
                result = this.search("cM");
                break;
            case "cMM":
                result = this.search("cMM");
                break;
            case "dm":
                result = this.search("dm");
                break;
            case "dM":
                result = this.search("dM");
                break;
            case "eM":
                result = this.search("eM");
                break;
            case "em":
                result = this.search("em");
                break;
            case "fm":
                result = this.search("fm");
                break;
            case "fM":
                result = this.search("fM");
                break;
            case "gM":
                result = this.search("gM");
                break;
            case "gm":
                result = this.search("gm");

                break;
        }
        return result;
    }

    public Note getNoteById(int id) {
        Note note = null;
        Iterator<Note> it = noteList.iterator();
        boolean found = false;
        while(it.hasNext() && !found) {
            note = it.next();
            if(note.getId() == id) {
                found = true;
            }
        }
        inTransaction = note;
        return note;
    }

    public Note getInTransaction() {return this.inTransaction;}
}
