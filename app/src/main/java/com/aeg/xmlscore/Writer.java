package com.aeg.xmlscore;


import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by will on 9/12/14.
 *
 */
public class Writer {
    private static Writer mWriter;


    private Writer() {}

    public static Writer getmWriter() {
        if (mWriter == null) {
            mWriter = new Writer();
        }
        return mWriter;
    }

    public void export(String name, Context ctx) {
        if(this.isExternalStorageWritable()) {
            File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            File home = new File(dir, "XMLScore");
            if(!home.exists()) {
                   home.mkdirs();
            }
            File project = new File(home, name + ".mxl");
            this.writeMusic(project, ctx);
        }
    }

    /* Checks if external storage is available for read and write
    * Taken from http://developer.android.com/training/basics/data-storage/files.html */
    private boolean isExternalStorageWritable() {
        boolean status = false;
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            status = true;
        }
        return status;
    }

    private void writeMusic(File pFile, Context ctx) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(pFile));
            int _div = 16;
            /**
             * TODO Aqui toca meter la fiesta!.
             */
            bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n");
            bw.write("<!DOCTYPE score-partwise PUBLIC\n");
            bw.write("\t\"-//Recordare//DTD MusicXML 3.0 Partwise//EN\"\n");
            bw.write("\t\"http://www.musicxml.org/dtds/partwise.dtd\">\n");
            bw.write("<score-partwise version=\"3.0\">\n");
            bw.write("\t<part-list>\n");
            bw.write("\t\t<score-part id=\"P1\">\n");
            bw.write("\t\t\t<part-name>Music</part-name>\n");
            bw.write("\t\t</score-part>\n");
            bw.write("\t</part-list>\n\n");
            for(int i = 0; i < mNoteManager.getNoteManager().howManyStages(); i++) {
                bw.write("\t<part id=\"P" + i + "\">\n");
                bw.write("\t\t<measure number=\"" + i + "\">\n");
                bw.write("\t\t\t<attributes>\n");
                bw.write("\t\t\t\t<divisions>" + _div + "</divisions>\n");
                bw.write("\t\t\t\t<key>\n");
                if(mMeasureCounter.getmMC().getKey() == 0) {
                    bw.write("\t\t\t\t\t<fifths>0</fifths>\n");
                } else {
                    bw.write("\t\t\t\t\t<fifths>" + mMeasureCounter.getmMC().getKey() + "</fifths>\n");
                    bw.write("\t\t\t\t\t<mode>major</mode>\n");
                }
                bw.write("\t\t\t\t</key>\n");
                bw.write("\t\t\t\t<time>\n");
                bw.write("\t\t\t\t\t<beats>" + mMeasureCounter.getmMC().getNum() + "</beats>\n");
                bw.write("\t\t\t\t\t<beat-type>" + mMeasureCounter.getmMC().getDen() + "</beat-type>\n");
                bw.write("\t\t\t\t<clef>\n");
                bw.write("\t\t\t\t\t<sign>G</sign>\n");
                bw.write("\t\t\t\t\t<line>2</line>\n");
                bw.write("\t\t\t\t</clef>\n");
                bw.write("\t\t\t</attributes>\n");
                Iterator<Note> it = mNoteManager.getNoteManager().notesAtStage(i).iterator();
                Note note = null;
                while(it.hasNext()) {
                    note = it.next();
                    bw.write("\t\t<note>\n");
                    if(!note.isRest()) {
                        bw.write("\t\t\t<pitch>\n");
                        bw.write("\t\t\t\t<step>" + note.getName() + "</step>\n");
                        bw.write("\t\t\t\t<octave>" + note.getOctave() + "</octave>\n");
                        bw.write("\t\t\t</pitch>\n");
                    } else {
                        bw.write("\t\t\t<rest/>\n");
                    }
                    bw.write("\t\t\t<duration>" + _div * note.getWeight() + "</duration>\n");
                    bw.write("\t\t\t<type>" + note.getType() + "</type>\n");
                    bw.write("\t\t</note>\n");
                }
                bw.write("\t\t</measure>\n");
            }
            bw.write("\t</part>\n");
            bw.write("</score-partwise>");
            bw.close();
            Toast.makeText(ctx, "File saved successfully in " + pFile.getCanonicalPath(), Toast.LENGTH_SHORT).show();


        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void saveJson(JSONArray json, String name, Context ctx) {
        FileOutputStream fos;
        try {
            fos = ctx.openFileOutput(name, Context.MODE_PRIVATE);
            fos.write(json.toString().getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void populate() {

    }

    public boolean checkIfExists(String name) {
        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File home = new File(dir, "XMLScore");
        if(!home.exists()) {
            home.mkdirs();
        }
        File project = new File(home, name + ".mxl");
        return project.exists();
    }

}
