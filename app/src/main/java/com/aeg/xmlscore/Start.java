package com.aeg.xmlscore;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;


public class Start extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Spinner sClef = (Spinner) findViewById(R.id.spinnerKey);
        Spinner sMeasure = (Spinner) findViewById(R.id.spinnerMeasure);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.spinnerK, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        sClef.setAdapter(adapter1);
        sClef.setSelection(7);

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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if(item.getItemId() == R.id.load) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void newStave(View view) {

        Intent intent = new Intent(this, Stave.class);
        Spinner sKey = (Spinner) findViewById(R.id.spinnerKey);
        Spinner sMeasure = (Spinner) findViewById(R.id.spinnerMeasure);
        String pClef = (String) sKey.getSelectedItem();
        String pMeas = (String) sMeasure.getSelectedItem();
        int check = -1;
        for(int i = 0; i < pMeas.length(); i++) {
            if(pMeas.charAt(i) == '/') {

                String tgt = pMeas.substring(0,i);
                intent.putExtra("NUM" , Integer.valueOf(tgt));
                check = i;
            } else if(i == pMeas.length() - 1) {
                String tgt = pMeas.substring(check + 1);
                intent.putExtra("DEN" , Integer.valueOf(tgt));
            }
        }

        intent.putExtra("CLEF", pClef);
        intent.putExtra("MEASURE", pMeas);
        intent.putExtra("POSITION", 1);


        startActivity(intent);

    }

}
