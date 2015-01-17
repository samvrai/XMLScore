package com.aeg.xmlscore;


import android.os.Environment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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

    public void export(String name) {
        if(this.isExternalStorageWritable()) {
            File docDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            File dir;
            if(!docDir.exists()) {
             dir = new File(Environment.getExternalStorageDirectory(), "Documents");
                docDir = dir;
            }

            File project = new File(docDir, "ScoreXML_" + name + ".mxl");
            this.writeMusic(project);
        }
    }

    /* Checks if external storage is available for read and write
    * Taken from http://developer.android.com/training/basics/data-storage/files.html */
    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    private void writeMusic(File pFile) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(pFile.getName()));

            /**
             * TODO Aqui toca meter la fiesta!.
             */
            bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>");
            bw.newLine();
            bw.write("<!DOCTYPE score-partwise PUBLIC");
            bw.newLine();
            bw.write("\t\"-//Recordare//DTD MusicXML 3.0 Partwise//EN\"");
            bw.newLine();
            bw.write("\t\"http://www.musicxml.org/dtds/partwise.dtd\">");
            bw.newLine();
            bw.write("<score-partwise version=\"3.0\">");
            bw.newLine();
            bw.write("<part-list>");
            bw.newLine();
            bw.write("\t<score-part id=\"P1\">");
            bw.newLine();
            bw.write("\t<part-name>Music</part-name>");
            bw.newLine();
            bw.write("\t</score-part>\n");
            bw.newLine();
            bw.write("</part-list>");
            bw.newLine();
            for(int i = 0; i < mNoteManager.getNoteManager().howManyStages(); i++) {
                bw.write("<part id=\"P" + i + "\">");

                bw.write("</part>");
            }
            bw.newLine();
            bw.write("</score-partwise>");


        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}
