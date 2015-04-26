package com.example.trzewa.bandrider;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.ParseObject;


public class SummaryStuffActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_stuff);
        TextView Stuff_name = (TextView)findViewById(R.id.textView6);
        TextView Stuff_owner = (TextView)findViewById(R.id.textView8);
        TextView Stuff_category = (TextView)findViewById(R.id.textView9);
        TextView Stuff_status = (TextView)findViewById(R.id.textView11);
        Button SaveButton = (Button) findViewById(R.id.button2);
        Button ReturnButton = (Button) findViewById(R.id.button);
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        final String stuff_name = settings.getString(Constans.STUFF_NAME, "");
        final String stuff_owner = settings.getString(Constans.STUFF_OWNER, "");
        final String stuff_category = settings.getString(Constans.STUFF_CATEGORY, "");
        final String stuff_status = settings.getString(Constans.STUFF_SWITCH_STATUS, "");
        Stuff_name.setText("Nazwa:  " + stuff_name);
        Stuff_owner.setText("Właściciel:  " + stuff_owner);
        Stuff_category.setText("Kategoria :" + stuff_category);
        Stuff_status.setText("Dostępność :" + stuff_status);

        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseObject Stuff = new ParseObject("Stuff");
                Stuff.put(Constans.STUFF_NAME, stuff_name);
                Stuff.put(Constans.STUFF_OWNER, stuff_owner);
                Stuff.put(Constans.STUFF_CATEGORY, stuff_category);
                Stuff.put(Constans.STUFF_SWITCH_STATUS, stuff_status);
                Stuff.saveInBackground();


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
