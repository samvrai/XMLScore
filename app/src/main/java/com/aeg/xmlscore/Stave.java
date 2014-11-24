package com.aeg.xmlscore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class Stave extends FragmentActivity {

    private ViewPageAdapter pAdapter;
    private NotesAdapter nAdapter;
    private ViewPager vpager, notesPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stave);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            String sClef = extras.getString("CLEF");
            String sMeas = extras.getString("MEASURE");
            if(sClef != null && sMeas != null) {
                setTitle("Clef " + sClef + ", measure " + sMeas);
            }
        }


        int stageMax = extras.getString("MEASURE").charAt(0) * extras.getString("MEASURE").charAt(2);


        this.pAdapter = new ViewPageAdapter(getSupportFragmentManager(), this);

        this.vpager = (ViewPager) findViewById(R.id.lStave);
        this.vpager.setAdapter(pAdapter);


        this.nAdapter = new NotesAdapter(getSupportFragmentManager());
        this.notesPager = (ViewPager) findViewById(R.id.lNotes);
        this.notesPager.setAdapter(nAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.save, menu);

        //getMenuInflater().inflate(R.menu.actions, menu);


        return true;
    }

    private void openSettings() {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
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
            case R.id.action_settings:
                openSettings();
                return true;
            case R.id.action_save:
                openSave();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void addStage(View view) {
        pAdapter.addStage();
        //Toast.makeText(getApplicationContext(), "Button clicked", Toast.LENGTH_SHORT).show();
    }

}
