package com.aeg.xmlscore;

import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by will on 24/11/14.
 *
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

    public void addNote(Note pNote, int index) { this.noteList.add(index, pNote);}

    public ArrayList<Note> notesAtStage(int stage) {
        ArrayList<Note> result = new ArrayList<>();
        Iterator<Note> it = this.noteList.iterator();
        Note note;

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
        Note note;

        while(it.hasNext()) {
            note = it.next();
            if(note.getStage() == stage) {
                res++;
            }
        }

        return res;
    }


    public int howManyStages() {
        int res = 1;
        int actual = 1;
        Iterator<Note> it = this.noteList.iterator();
        Note note = null;
        while(it.hasNext()) {
            note = it.next();
            if(note.getStage() != actual) {
                res++;
                actual = note.getStage();
            }
        }

        if(stageWeight(res) == mMeasureCounter.getmMC().getMax()) {
            res++;
        }

        return res;
    }

    public float stageWeight(int stage) {
        Iterator<Note> it = notesAtStage(stage).iterator();
        float total = 0;

        Note dummy;
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
        NotePos np;
        while(it.hasNext() && !found) {
            np = it.next();
            if(np.getName().equals(pNom)) {
                found = true;
                result = np.getPosition();
            }
        }
        return result;
    }

    public float getNoteY(String pNote, int oct) {
        float result = 0;
        switch(pNote + "|" + oct) {
            case ("A|4"):
                result = this.search("am");
                break;
            case ("A|5"):
                result = this.search("aM");
                break;
            case ("B|5"):
                result = this.search("bM");
                break;
            case ("B|4"):
                result = this.search("bm");
                break;
            case ("C|4"):
                result = this.search("cm");
                break;
            case ("C|5"):
                result = this.search("cM");
                break;
            case ("C|6"):
                result = this.search("cMM");
                break;
            case ("D|4"):
                result = this.search("dm");
                break;
            case ("D|5"):
                result = this.search("dM");
                break;
            case ("E|5"):
                result = this.search("eM");
                break;
            case ("E|4"):
                result = this.search("em");
                break;
            case ("F|4"):
                result = this.search("fm");
                break;
            case ("F|5"):
                result = this.search("fM");
                break;
            case ("G|5"):
                result = this.search("gM");
                break;
            case ("G|4"):
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

    public int size() {return this.noteList.size();}

    public Note getNote(int i){
        return this.noteList.get(i);
    }

    public void removeNote() { this.noteList.remove(this.inTransaction);}

    public void reset() {
        mNM = null;
    }
}
