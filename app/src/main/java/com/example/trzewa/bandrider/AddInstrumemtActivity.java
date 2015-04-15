package com.example.trzewa.bandrider;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class AddInstrumemtActivity extends Activity {
    
    private Button NextButton;
    private Button ReturnButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] elementy = {"strunowe", "dęte", "perkusyjne"};
        setContentView(R.layout.activity_add_instrumemt);
        final EditText instname = (EditText)findViewById(R.id.editTextname);
        final EditText instowner = (EditText)findViewById(R.id.editTextowner);

        final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = settings.edit();
        NextButton = (Button) findViewById(R.id.ToInstSummary);
        ReturnButton = (Button) findViewById(R.id.Returnnp);
        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String InstName = instname.getText().toString();
                String InstOwner = instowner.getText().toString();
                editor.putString(Constans.INST_NAME, InstName);
                editor.putString(Constans.INST_OWNER, InstOwner);
                editor.commit();
                startActivity(new Intent(AddInstrumemtActivity.this, SummaryInstrumentActivity.class));

            }
        });
        ReturnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });

        final Spinner spinner = (Spinner)findViewById(R.id.spinner1);


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, elementy);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int id, long position) {

                Toast.makeText(AddInstrumemtActivity.this, "Wybrano opcję" + (id + 1), Toast.LENGTH_SHORT).show();
                String category=null;
                switch((int)position)
                {
                    case 0:
                        //wybrano pierwszy element
                        break;
                    case 1:
                        category = "strunowe";
                        editor.putString(Constans.INST_CATEGORY, category);
                        editor.commit();
                        break;
                    case 2:
                        category = "dęte";
                        editor.putString(Constans.INST_CATEGORY, category);
                        editor.commit();
                        break;
                    case 3:
                        category = "perkusyjne";
                        editor.putString(Constans.INST_CATEGORY, category);
                        editor.commit();
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

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_stuff, menu);
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
