package com.aeg.xmlscore;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;


public class Start extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Spinner sClef = (Spinner) findViewById(R.id.spinnerClef);
        Spinner sMeasure = (Spinner) findViewById(R.id.spinnerMeasure);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.spinnerC, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        sClef.setAdapter(adapter1);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.spinnerM, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        sMeasure.setAdapter(adapter2);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.start, menu);
        return true;
    }

    private void openSettings() {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            openSettings();
            return true;
        } else {

        }
        return super.onOptionsItemSelected(item);
    }

    public void newStave(View view) {

        Intent intent = new Intent(this, Stave.class);
        Spinner sClef = (Spinner) findViewById(R.id.spinnerClef);
        Spinner sMeasure = (Spinner) findViewById(R.id.spinnerMeasure);
        String pClef = (String) sClef.getSelectedItem();
        String pMeas = (String) sMeasure.getSelectedItem();
        intent.putExtra("CLEF", pClef);
        intent.putExtra("MEASURE", pMeas);
        intent.putExtra("POSITION", 1);


        startActivity(intent);

    }

    public void swapLang(String lng) {

        if(lng == "ES") {
            Button btn = (Button) findViewById(R.id.bNewStave);
            btn.setText(getText(R.string.button_main_es));

            TextView tvw = (TextView) findViewById(R.id.textClef);
            tvw.setText(getText(R.string.select_text_main_clef_es));

            tvw = (TextView) findViewById(R.id.textMeasure);
            tvw.setText(getText(R.string.select_text_main_measure_es));
        } else {
            Button btn = (Button) findViewById(R.id.bNewStave);
            btn.setText(getText(R.string.button_main));

            TextView tvw = (TextView) findViewById(R.id.textClef);
            tvw.setText(getText(R.string.select_text_main_clef));

            tvw = (TextView) findViewById(R.id.textMeasure);
            tvw.setText(getText(R.string.select_text_main_measure));
        }
    }


}
