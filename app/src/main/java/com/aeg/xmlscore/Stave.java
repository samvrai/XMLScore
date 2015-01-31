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

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            String sClef = extras.getString("CLEF");
            String sMeas = extras.getString("MEASURE");
            if(sClef != null && sMeas != null) {
                setTitle("Key is " + sClef + ", measure is " + sMeas);
            }
        }

        int num = 0;
        int den = 0;
        if(extras != null) {

            num = extras.getInt("NUM");
            den = extras.getInt("DEN");
        }

        ViewPageAdapter pAdapter;
        NotesAdapter nAdapter;
        ViewPager vpager, notesPager;

        mMeasureCounter.getmMC().setArguments(num, den);
        pAdapter = new ViewPageAdapter(getSupportFragmentManager(), this);

        vpager = (ViewPager) findViewById(R.id.lStave);
        vpager.setAdapter(pAdapter);


        nAdapter = new NotesAdapter(getSupportFragmentManager());
        notesPager = (ViewPager) findViewById(R.id.lNotes);
        notesPager.setAdapter(nAdapter);

        AdapterManager.getMaM().loader(pAdapter, nAdapter, vpager, notesPager, this);

        Writer.getmWriter().setContext(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.save, menu);

        //getMenuInflater().inflate(R.menu.actions, menu);


        return true;
    }


    private void openSave() {
        /*Intent intent = new Intent(this, Settings.class);
        startActivity(intent);*/
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.action_save:
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

        JSONArray noteList = new JSONArray();
        try {
            for(int i = 0; i < mNoteManager.getNoteManager().size(); i++) {
                JSONObject json = new JSONObject();
                json.put("Name", mNoteManager.getNoteManager().getNote(i).getName());
                json.put("Name", mNoteManager.getNoteManager().getNote(i).getName());
                json.put("Name", mNoteManager.getNoteManager().getNote(i).getName());
                json.put("Name", mNoteManager.getNoteManager().getNote(i).getName());
                json.put("Name", mNoteManager.getNoteManager().getNote(i).getName());
                noteList.put(json);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        Writer.getmWriter().saveJson(noteList);
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

        ViewGroup vg = (ViewGroup)findViewById(R.id.notePlace);
        for(int i = 0; i < vg.getChildCount(); i++) {
            vg.getChildAt(i).setBackground(null);
        }
        AdapterManager.getMaM().getNotesPager().setCurrentItem(0);
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
