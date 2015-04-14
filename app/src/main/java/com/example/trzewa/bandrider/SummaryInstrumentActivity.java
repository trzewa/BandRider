package com.example.trzewa.bandrider;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class SummaryInstrumentActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_stuff);
        TextView Inst_name = (TextView)findViewById(R.id.textView6);
        TextView Inst_owner = (TextView)findViewById(R.id.textView8);
        TextView Inst_category = (TextView)findViewById(R.id.textView9);
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        String inst_name = settings.getString(Constans.INST_NAME, "");
        String inst_owner = settings.getString(Constans.INST_OWNER, "");
        String inst_category = settings.getString(Constans.INST_CATEGORY, "");
        Inst_name.setText("Nazwa:  " + inst_name);
        Inst_owner.setText("Właściciel:  " + inst_owner);
        Inst_category.setText("Kategoria :" + inst_category);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_summary_stuff, menu);
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
}