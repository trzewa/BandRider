package com.example.trzewa.bandrider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;


public class AddInstrumemtActivity extends ActionBarActivity {

    private Button NextButton;
    private Button ReturnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        String[] elementy = {"strunowe", "dęte", "perkusyjne"};
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_instrumemt);
        NextButton = (Button) findViewById(R.id.ToInstSummary);
        ReturnButton = (Button) findViewById(R.id.Returnnp);
        final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = settings.edit();

        final Spinner spinner = (Spinner)findViewById(R.id.spinner1);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, elementy);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int id, long position) {

                Toast.makeText(AddInstrumemtActivity.this, "Wybrano opcję" + (id + 1), Toast.LENGTH_SHORT).show();

                switch((int)position)
                {
                    case 0:
                        //wybrano pierwszy element
                        break;
                    case 1:
                        //wybrano drugi element
                        break;
                    case 2:
                        //wybrano trzeci element
                        break;
                    case 3:
                        //wybrano czwarty element
                        break;
                    case 4:
                        //wybrano piąty element
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(AddInstrumemtActivity.this, SummaryInstrumentActivity.class));

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
        getMenuInflater().inflate(R.menu.menu_add_instrumemt, menu);
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
