package com.example.trzewa.bandrider;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
    private AlertDialog alertDialog;
    //private ParseUser mCurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ParseAnalytics.trackAppOpenedInBackground(getIntent());

        boolean networkstate = Utilities.getConnectivityStatus(getApplicationContext());
        if (networkstate) {
            informacjaZapisu();


            if (ParseUser.getCurrentUser() == null) {

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
        else
        {
            alertDialog = new AlertDialog.Builder(ChoiceActivity.this)
                    .setTitle("Brak Internetu")
                    .setMessage("Brak połączenia z siecią Internet")
                    .setNegativeButton("Ok", new AlertDialog.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            alertDialog.cancel();
                            finish();
                        }
                    })
                    .create();
            alertDialog.show();

        }
    }

    private void informacjaZapisu() {
        Intent i = getIntent();
        if (i.hasExtra("dane"))
        {
            Bundle przekazanedane = i.getExtras();
            String przekazanytekst = przekazanedane.getString("dane");
            Toast.makeText(ChoiceActivity.this, przekazanytekst, Toast.LENGTH_SHORT).show();
        }
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
