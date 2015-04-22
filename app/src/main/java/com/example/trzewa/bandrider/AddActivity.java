package com.example.trzewa.bandrider;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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



}
