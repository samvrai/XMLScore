package com.aeg.xmlscore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class Stave extends FragmentActivity {

    private ViewPager vStave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stave);

        TextView pClef = (TextView) findViewById(R.id.clef);
        TextView pMeasure = (TextView) findViewById(R.id.title);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            String sClef = extras.getString("CLEF");
            String sMeas = extras.getString("MEASURE");
            if(sClef != null && sMeas != null) {
                pClef.setText(sClef);
                pMeasure.setText(sMeas);
            }
        }

        vStave = (ViewPager) findViewById(R.id.lStave);
        vStave.setAdapter(new ViewPageAdapter(getSupportFragmentManager()));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.actions, menu);


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
            case R.id.action_lang:
                Language.getLanguage().changeLanguage((String)item.getTitle());
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
