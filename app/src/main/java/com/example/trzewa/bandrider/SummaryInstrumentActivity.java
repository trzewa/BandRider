package com.example.trzewa.bandrider;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.ParseObject;


public class SummaryInstrumentActivity extends ActionBarActivity {

    @Override
      protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_instrument);
        TextView Inst_name = (TextView)findViewById(R.id.textView6);
        TextView Inst_owner = (TextView)findViewById(R.id.textView8);
        TextView Inst_category = (TextView)findViewById(R.id.textView9);
        TextView Inst_status = (TextView)findViewById(R.id.textView10);
        Button SaveButton = (Button) findViewById(R.id.buttonSave);
        Button ReturnButton = (Button) findViewById(R.id.buttonReturn);
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        final String inst_name = settings.getString(Constans.INST_NAME, "");
        final String inst_owner = settings.getString(Constans.INST_OWNER, "");
        final String inst_category = settings.getString(Constans.INST_CATEGORY, "");
        final String inst_status = settings.getString(Constans.INS_SWITCH_STATUS, "");
        Inst_name.setText("Nazwa:  " + inst_name);
        Inst_owner.setText("Właściciel:  " + inst_owner);
        Inst_category.setText("Kategoria :" + inst_category);
        Inst_status.setText("Dostępność :" + inst_status);
        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        ParseObject Instrument = new ParseObject("Instrument");
        Instrument.put(Constans.INST_NAME, inst_name);
        Instrument.put(Constans.INST_OWNER, inst_owner);
        Instrument.put(Constans.INST_CATEGORY, inst_category);
        Instrument.put(Constans.INS_SWITCH_STATUS, inst_status);
        Instrument.saveInBackground();


            }
        });
        ReturnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                finish();
            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_summary_instrument, menu);
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