package com.example.trzewa.bandrider;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class AddActivity extends ActionBarActivity {

    private RadioGroup radioChoiceGroup;
    private RadioButton radioItemButton;
    private Button btnDalej;
    private Button btnWstecz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        addListenerOnButton();
    }

    public void addListenerOnButton() {

        radioChoiceGroup = (RadioGroup) findViewById(R.id.radioChoice);
        btnDalej = (Button) findViewById(R.id.button4);
        btnWstecz = (Button) findViewById(R.id.buttonWstecz);

        btnDalej.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int selectedId = radioChoiceGroup.getCheckedRadioButtonId();

                radioItemButton = (RadioButton) findViewById(selectedId);
                String test = (String) radioItemButton.getText();
                Toast.makeText(AddActivity.this, test, Toast.LENGTH_SHORT).show();


                if (radioItemButton.getText().equals("INSTRUMENT"))
                {
                    startActivity(new Intent(AddActivity.this, AddInstrumemtActivity.class));
                }
                else
                {
                   // startActivity(new Intent(AddActivity.this, AddStuffActivity.class));
                    startActivity(new Intent(AddActivity.this, AddStuffActivity.class));
                }
            }


        });

        btnWstecz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChoiceActivity.startChoiceActivity(AddActivity.this);
                finish();

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

