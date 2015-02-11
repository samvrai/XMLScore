package com.aeg.xmlscore;


import android.content.Context;
import android.os.Environment;
import android.util.JsonReader;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
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
            File project = new File(home, name + ".xml");
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
            bw.write("\t<part id=\"P1\">\n");
            boolean done = false;
            for(int i = 1; i <= mNoteManager.getNoteManager().howManyStages() && mNoteManager.getNoteManager().notesAtStage(i).size() > 0; i++) {
                bw.write("\t\t<measure number=\"" + i + "\">\n");
                if(!done) {
                    bw.write("\t\t\t<attributes>\n");
                    bw.write("\t\t\t\t<divisions>" + _div + "</divisions>\n");
                    bw.write("\t\t\t\t<key>\n");
                    if (mMeasureCounter.getmMC().getKey() == 0) {
                        bw.write("\t\t\t\t\t<fifths>0</fifths>\n");
                    } else {
                        bw.write("\t\t\t\t\t<fifths>" + mMeasureCounter.getmMC().getKey() + "</fifths>\n");
                        bw.write("\t\t\t\t\t<mode>major</mode>\n");
                    }
                    bw.write("\t\t\t\t</key>\n");
                    bw.write("\t\t\t\t<time>\n");
                    bw.write("\t\t\t\t\t<beats>" + mMeasureCounter.getmMC().getNum() + "</beats>\n");
                    bw.write("\t\t\t\t\t<beat-type>" + mMeasureCounter.getmMC().getDen() + "</beat-type>\n");
                    bw.write("\t\t\t\t</time>\n");
                    bw.write("\t\t\t\t<clef>\n");
                    bw.write("\t\t\t\t\t<sign>G</sign>\n");
                    bw.write("\t\t\t\t\t<line>2</line>\n");
                    bw.write("\t\t\t\t</clef>\n");
                    bw.write("\t\t\t</attributes>\n");
                    done = true;
                }
                Iterator<Note> it = mNoteManager.getNoteManager().notesAtStage(i).iterator();
                Note note = null;
                double xarray = 109.14;
                while(it.hasNext()) {
                    note = it.next();
                    bw.write("\t\t<note default-x=\"" + xarray + "\" default-y=\"" + toY(note.getOctave(), note.getName()) + "\">\n");
                    if(!note.isRest()) {
                        bw.write("\t\t\t<pitch>\n");
                        bw.write("\t\t\t\t<step>" + note.getName() + "</step>\n");
                        bw.write("\t\t\t\t<octave>" + note.getOctave() + "</octave>\n");
                        bw.write("\t\t\t</pitch>\n");
                     } else {
                        bw.write("\t\t\t<rest/>\n");
                    }
                    if(note.getOctave() == 4 && note.getName() != "B" && !note.isRest()) {
                        bw.write("\t\t\t<stem>up</stem>\n");
                    } else if(note.getOctave() > 4 && !note.isRest()){
                        bw.write("\t\t\t<stem>down</stem>\n");
                    }
                    float dur = _div * note.getWeight();
                    int durat = (int) dur;
                    bw.write("\t\t\t<duration>" + durat + "</duration>\n");
                    bw.write("\t\t\t<type>" + note.getType() + "</type>\n");
                    if(note.isFlagD()) {
                        bw.write("\t\t\t<dot/>\n");
                    }
                    if(note.isFlagS()) {
                        bw.write("\t\t\t<accidental>sharp</accidental>\n");
                    }
                    if(note.isFlagF()) {
                        bw.write("\t\t\t<accidental>flat</accidental>\n");
                    }
                    if(note.isFlagN()) {
                        bw.write("\t\t\t<accidental>natural</accidental>\n");
                    }
                    bw.write("\t\t</note>\n");
                    xarray += 25.52;
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
            fos = ctx.openFileOutput(name + ".json", Context.MODE_PRIVATE);
            fos.write(json.toString().getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void populate(String name, Context ctx) {
        File home = ctx.getFilesDir();
        File project = new File(home, name);
        if(project.exists())  {
            loadJson(project, name, ctx);

        }else{
            Toast.makeText(ctx, "File not found", Toast.LENGTH_SHORT).show();
        }
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

    public ArrayList<String> loadStaves(Context ctx) {
        ArrayList<String> mylist = new ArrayList<String>();

        File home = ctx.getFilesDir();

        File[] fs = home.listFiles();
        for(int i = 0; i < fs.length; i++) {
            mylist.add(fs[i].getName());
        }
        return mylist;
    }

    public void loadJson(File pf, String name, Context ctx) {
        String json = null;
        try {
            FileReader fr = new FileReader(pf);
            BufferedReader br = new BufferedReader(fr);
            json = br.readLine();
            JSONArray jsonArray = new JSONArray(json);
            JSONObject js = new JSONObject();
            int end = 0;
            boolean breakf = false;
            while(end < name.length() && !breakf) {
                if(name.charAt(end) == '.'){
                    breakf = true;
                } else {
                    end++;
                }
            }
            int i = 0;
            while(js != null) {
                js = jsonArray.getJSONObject(i);
                if(i == 0) {
                    mMeasureCounter.getmMC().setArguments(js.getInt("NUM"), js.getInt("DEN"), js.getInt("KEY"));
                    mTools.getTools().setName(name.substring(0, end));
                } else {
                    int w = js.getInt("WEIGHT");
                    float weight = w;
                    Note note = new Note(js.getString("NAME"), weight, js.getInt("STAGE"), js.getInt("OCTAVE"), js.getString("TYPE"), js.getBoolean("REST"));
                    note.defaultFlags(js.getBoolean("FLAGS"), js.getBoolean("FLAGF"), js.getBoolean("FLAGO"), js.getBoolean("FLAGD"), js.getBoolean("FLAGN"));
                    mNoteManager.getNoteManager().addNote(note);
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException je) {
            je.fillInStackTrace();
        }
    }

    private double toY(int oct, String n) {
        double ymeas = 0;
        if (oct == 4) {
            switch (n) {
                case "C":
                    ymeas = -20.00;
                    break;
                case "D":
                    ymeas = -15.00;
                    break;
                case "E":
                    ymeas = -10.00;
                    break;
                case "F":
                    ymeas = -5.00;
                    break;
                case "G":
                    ymeas = 0.00;
                    break;
                case "A":
                    ymeas = 5.00;
                    break;
                case "B":
                    ymeas = 10.00;
                    break;
                default:
                    ymeas = 0;
                    break;
            }
        }
        else if (oct == 5) {
            switch (n) {
                case "C":
                    ymeas = 15.00;
                    break;
                case "D":
                    ymeas = 20.00;
                    break;
                case "E":
                    ymeas = 25.00;
                    break;
                case "F":
                    ymeas = 30.00;
                    break;
                case "G":
                    ymeas = 35.00;
                    break;
                case "A":
                    ymeas = 40.00;
                    break;
                case "B":
                    ymeas = 45.00;
                    break;
                default:
                    ymeas = 0;
                    break;
            }
        }
        else if (oct == 6) {
            switch (n) {
                case "C":
                    ymeas = 50.00;
                    break;
                default:
                    ymeas = 0;
                    break;
            }
        }
        return ymeas;
    }

}
