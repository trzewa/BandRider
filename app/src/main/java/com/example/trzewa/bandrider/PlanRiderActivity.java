package com.example.trzewa.bandrider;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.parse.ParseObject;


public class PlanRiderActivity extends ActionBarActivity {
    private AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_rider);
        final Context context = getApplicationContext();
        boolean networkstate = Utilities.getConnectivityStatus(context);

        if (networkstate == true)
        {

            Toast.makeText(getApplicationContext(), "nawiazano połączenie", Toast.LENGTH_LONG).show();

        }
        else
        {
            alertDialog = new AlertDialog.Builder(PlanRiderActivity.this)
                    .setTitle("Brak Internetu")
                    .setMessage("brak połączenia z siecią Internet")
                    .setNegativeButton("Ok", new AlertDialog.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            alertDialog.cancel();
                        }
                    })
                    .create();
            alertDialog.show();


        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_plan_rider, menu);
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
