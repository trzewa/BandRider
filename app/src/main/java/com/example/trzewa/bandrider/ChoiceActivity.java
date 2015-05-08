package com.example.trzewa.bandrider;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.parse.ParseAnalytics;
import com.parse.ParseUser;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ChoiceActivity extends ActionBarActivity {
    Button AddInstrumentButton;
    Button edit_deleteButton;
    Button planRider;

    //private ParseUser mCurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ParseAnalytics.trackAppOpenedInBackground(getIntent());

        //Wyswietla informacje o zapisaniu Instrumentu lub sprzetu
        Intent i = getIntent();
        if (i.hasExtra("dane"))
        {
            Bundle przekazanedane = i.getExtras();
            String przekazanytekst = przekazanedane.getString("dane");
            Toast.makeText(ChoiceActivity.this, przekazanytekst, Toast.LENGTH_SHORT).show();
        }



        //mCurrentUser = ParseUser.getCurrentUser();
        if (ParseUser.getCurrentUser() == null) {
            //Intent intent = new Intent(this, LoginActivity.class);
            //startActivity(intent);
            goToLogin();

        }

        setContentView(R.layout.activity_choice);
        AddInstrumentButton = (Button) findViewById(R.id.buttonAdd);
        AddInstrumentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ChoiceActivity.this, AddActivity.class));
                finish();

            }
        });
        planRider = (Button) findViewById(R.id.buttonRider);
        planRider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ChoiceActivity.this, ShowCategoriesActivity.class));
                finish();

            }
        });

        edit_deleteButton = (Button) findViewById(R.id.buttonEdit);
        edit_deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ChoiceActivity.this, EditionActivity.class));
                finish();

            }
        });
     }

    private void goToLogin() {
        LoginActivity.startLogInActivity(this);
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_choice, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {

            ParseUser.logOut();
            goToLogin();
            return true;
        }

        if (id == R.id.action_about) {

            AboutActivity.Show(ChoiceActivity.this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static void startChoiceActivity(Context context) {
        Intent intent = new Intent(context, ChoiceActivity.class);
        context.startActivity(intent);
    }
}
