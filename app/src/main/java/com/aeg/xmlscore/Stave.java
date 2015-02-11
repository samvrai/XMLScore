package com.aeg.xmlscore;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


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

        for(int i = 1; i <= mNoteManager.getNoteManager().howManyStages(); i++) {
            addStage(null);
        }
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
                json.put("TYPE", mNoteManager.getNoteManager().getNote(i).getType());
                json.put("WEIGHT", mNoteManager.getNoteManager().getNote(i).getWeight());
                json.put("REST", mNoteManager.getNoteManager().getNote(i).isRest());
                json.put("STAGE", mNoteManager.getNoteManager().getNote(i).getStage());
                json.put("FLAGS", mNoteManager.getNoteManager().getNote(i).isFlagS());
                json.put("FLAGF", mNoteManager.getNoteManager().getNote(i).isFlagF());
                json.put("FLAGD", mNoteManager.getNoteManager().getNote(i).isFlagD());
                json.put("FLAGN", mNoteManager.getNoteManager().getNote(i).isFlagN());
                json.put("FLAGO", mNoteManager.getNoteManager().getNote(i).isFlagO());
                json.put("OCTAVE", mNoteManager.getNoteManager().getNote(i).getOctave());
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

    }

    @Override
    protected void onDestroy() {

        mNoteManager.getNoteManager().reset();
        finish();
        super.onDestroy();
    }

    public void saveState(View v) {
        CheckBox cDotted = (CheckBox)findViewById(R.id.dotted);
        CheckBox cNatural = (CheckBox)findViewById(R.id.natural);
        RadioGroup rG = (RadioGroup)findViewById(R.id.rGroup);
        Note note = mNoteManager.getNoteManager().getInTransaction();

        if(cDotted.isChecked() && !note.isFlagD()) {

            if(mNoteManager.getNoteManager().stageWeight(note.getStage()) + (note.getWeight()/2) <= mMeasureCounter.getmMC().getMax()) {
                note.setFlagD(true);
            } else {
                Toast.makeText(this, "No room for this note", Toast.LENGTH_SHORT).show();
            }
        } else if(!cDotted.isChecked() && note.isFlagD()) {
            note.setFlagD(false);
        }
        if(cNatural.isChecked() && !note.isFlagN()) {
            note.setFlagN(true);
            ((RadioButton) findViewById(R.id.normal)).setChecked(true);

        } else if(!cNatural.isChecked() && note.isFlagN()) {
            note.setFlagN(false);
        }

        switch (rG.getCheckedRadioButtonId()) {
            case R.id.normal:
                note.setFlagO();
                break;
            case R.id.sharp:
                note.setFlagS();
                break;
            case R.id.flat:
                note.setFlagF();
                break;
        }

        ViewGroup vg = ((Stage)AdapterManager.getMaM().getpAdapter().getItem(AdapterManager.getMaM().getVpager().getCurrentItem())).getNotePlace();
        for(int i = 0; i < vg.getChildCount(); i++) {
            vg.getChildAt(i).setBackground(null);
        }

        mTools.getTools().relocate(AdapterManager.getMaM().getVpager().getCurrentItem() + 1, vg);
        AdapterManager.getMaM().getNotesPager().setCurrentItem(0);
    }

    public void removeNote(View v) {
        ViewGroup vg = ((Stage)AdapterManager.getMaM().getpAdapter().getItem(AdapterManager.getMaM().getVpager().getCurrentItem())).getNotePlace();
        for(int i = 0; i < vg.getChildCount(); i++) {
            if(vg.getChildAt(i).getId() == mNoteManager.getNoteManager().getInTransaction().getId()) {
                vg.removeViewAt(i);
            }
        }
        int stage = mNoteManager.getNoteManager().getInTransaction().getStage();
        mNoteManager.getNoteManager().removeNote();

        mTools.getTools().relocate(stage, vg);
        AdapterManager.getMaM().getNotesPager().setCurrentItem(0);

        if(mNoteManager.getNoteManager().notesAtStage(AdapterManager.getMaM().getVpager().getCurrentItem() + 1).size() == 0 && mNoteManager.getNoteManager().howManyStages() > 1) {
            AdapterManager.getMaM().getpAdapter().remove();
        }

        AdapterManager.getMaM().updateTexts();
    }
}
