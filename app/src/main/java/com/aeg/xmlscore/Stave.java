package com.aeg.xmlscore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;


public class Stave extends FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stave);
        setTitle("Key " + mMeasureCounter.getmMC().getKey() + ", M " + mMeasureCounter.getmMC().getNum() + "/" + mMeasureCounter.getmMC().getDen() + " , " + mTools.getTools().getName());

        ViewPageAdapter pAdapter;
        NotesAdapter nAdapter;
        ViewPager vpager, notesPager;

        pAdapter = new ViewPageAdapter(getSupportFragmentManager(), this);

        vpager = (ViewPager) findViewById(R.id.lStave);
        vpager.setAdapter(pAdapter);


        nAdapter = new NotesAdapter(getSupportFragmentManager());
        notesPager = (ViewPager) findViewById(R.id.lNotes);
        notesPager.setAdapter(nAdapter);

        AdapterManager.getMaM().loader(pAdapter, nAdapter, vpager, notesPager, this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.save, menu);

        //getMenuInflater().inflate(R.menu.actions, menu);


        return true;
    }


    private void openSave() {
        JSONArray noteList = new JSONArray();
        try {
            JSONObject jsid = new JSONObject();
            jsid.put("KEY", mMeasureCounter.getmMC().getKey());
            jsid.put("NUM", mMeasureCounter.getmMC().getNum());
            jsid.put("DEN", mMeasureCounter.getmMC().getDen());
            noteList.put(jsid);
            for(int i = 0; i < mNoteManager.getNoteManager().size(); i++) {
                JSONObject json = new JSONObject();
                json.put("NAME", mNoteManager.getNoteManager().getNote(i).getName());
                json.put("TYPE", mNoteManager.getNoteManager().getNote(i).getName());
                json.put("WEIGHT", mNoteManager.getNoteManager().getNote(i).getName());
                json.put("REST", mNoteManager.getNoteManager().getNote(i).getName());
                json.put("STAGE", mNoteManager.getNoteManager().getNote(i).getName());
                json.put("FLAGS", mNoteManager.getNoteManager().getNote(i).getName());
                json.put("FLAGF", mNoteManager.getNoteManager().getNote(i).getName());
                json.put("FLAGD", mNoteManager.getNoteManager().getNote(i).getName());
                json.put("FLAGN", mNoteManager.getNoteManager().getNote(i).getName());
                json.put("OCTAVE", mNoteManager.getNoteManager().getNote(i).getName());
                json.put("BITMAP", mNoteManager.getNoteManager().getNote(i).getName());
                noteList.put(json);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Writer.getmWriter().saveJson(noteList, mTools.getTools().getName(), this);

        Writer.getmWriter().export(mTools.getTools().getName(), this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.action_saving:
                openSave();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void addStage(View v) {
        AdapterManager.getMaM().getpAdapter().addStage();
        //AdapterManager.getMaM().getVpager().setCurrentItem(AdapterManager.getMaM().getpAdapter().getCount() - 1);
        //Toast.makeText(getApplicationContext(), "Button clicked", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onDestroy() {
        openSave();
        super.onDestroy();
    }

    public void saveState(View v) {
        CheckBox cDotted = (CheckBox)findViewById(R.id.dotted);
        CheckBox cNatural = (CheckBox)findViewById(R.id.natural);
        Note note = mNoteManager.getNoteManager().getInTransaction();

        if(cDotted.isChecked() && !note.isFlagD()) {
            Toast.makeText(this, String.valueOf(note.getWeight()) + " " + String.valueOf(mNoteManager.getNoteManager().stageWeight(note.getStage())), Toast.LENGTH_SHORT).show();
            if(mNoteManager.getNoteManager().stageWeight(note.getStage()) <= mNoteManager.getNoteManager().stageWeight(note.getStage()) + (note.getWeight()/2)) {
                note.setFlagD(true);
            } else {
                Toast.makeText(this, "No room for this note", Toast.LENGTH_SHORT).show();
            }
        } else if(!cDotted.isChecked() && note.isFlagD()) {
            note.setFlagD(false);
        }
        if(cNatural.isChecked() && !note.isFlagN()) {
            note.setFlagN(true);
        } else if(!cNatural.isChecked() && note.isFlagN()) {
            note.setFlagN(false);
        }

        ViewGroup vg = ((Stage)AdapterManager.getMaM().getpAdapter().getItem(AdapterManager.getMaM().getVpager().getCurrentItem())).getNotePlace();
        for(int i = 0; i < vg.getChildCount(); i++) {
            vg.getChildAt(i).setBackground(null);
        }
        AdapterManager.getMaM().getNotesPager().setCurrentItem(0);

        mTools.getTools().relocate(AdapterManager.getMaM().getVpager().getCurrentItem(), vg);
    }

    public void removeNote(View v) {
        ViewGroup vg = (ViewGroup)findViewById(R.id.notePlace);
        for(int i = 0; i < vg.getChildCount(); i++) {
            if(vg.getChildAt(i).getId() == mNoteManager.getNoteManager().getInTransaction().getId()) {
                vg.removeViewAt(i);
            }
        }
        int stage = mNoteManager.getNoteManager().getInTransaction().getStage();
        mNoteManager.getNoteManager().removeNote();

        mTools.getTools().relocate(stage, vg);
    }
}
