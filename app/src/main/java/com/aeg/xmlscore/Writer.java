package com.aeg.xmlscore;

import android.content.Context;
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
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}
