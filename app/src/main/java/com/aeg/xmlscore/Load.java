package com.aeg.xmlscore;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class Load extends Activity {

    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> al;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        ListView loadList = (ListView)findViewById(R.id.loadableList);
        refresh();
        loadList.setAdapter(arrayAdapter);

        loadList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((TextView)findViewById(R.id.filename)).setText(arrayAdapter.getItem(position));
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_load, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void delete(View v) {
        this.deleteFile(((TextView)findViewById(R.id.filename)).getText().toString());
        arrayAdapter.notifyDataSetChanged();
        refresh();
    }

    public void load(View v) {
        Writer.getmWriter().populate(((TextView)findViewById(R.id.filename)).getText().toString(), this);
        Intent intent = new Intent(this, Stave.class);
        startActivity(intent);
    }

    private void refresh() {
        al = Writer.getmWriter().loadStaves(this);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, al);

        ListView loadList = (ListView)findViewById(R.id.loadableList);
        loadList.setAdapter(arrayAdapter);
    }
}
