package com.example.trzewa.bandrider;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class PlanRiderActivity extends Activity  {
    private AlertDialog alertDialog;
    private static final String TAG = "DialogDemo";
    private Context mContext;
    ListView listView;
    final String[] choiceList =
            {"Instrumenty", "Sprzęt" , "Akcesoria" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_plan_rider);
        final Context context = getApplicationContext();
        mContext = this;
        Intent intent = getIntent();
        int selected  = intent.getIntExtra(Constans.CATEGORY_SELECTED, 0);
        boolean networkstate = Utilities.getConnectivityStatus(context);
        //int selected = showDialogButtonClick();

        //listView.setVisibility(View.INVISIBLE);
        if (networkstate == true) {

            Toast.makeText(getApplicationContext(), "nawiazano połączenie", Toast.LENGTH_LONG).show();

            switch(choiceList[selected]) {
                case "Instrumenty":
                    setContentView(R.layout.activity_plan_rider);
                    listView = (ListView) findViewById(R.id.listViewRaider);
                    Toast.makeText( mContext,"Select aaaa "+choiceList[selected],Toast.LENGTH_SHORT).show();
                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Instrument");
                    query.findInBackground(new FindCallback<ParseObject>() {

                        public void done(List<ParseObject> instrumentList, com.parse.ParseException e) {
                            if (e == null) {
                                Log.d("score", "Retrieved " + instrumentList.size() + "instrumentów");
                                if(instrumentList.size() > 0)
                                {
                                    for(int i=0;i<instrumentList.size();i++)
                                    {
                                        ParseObject ob = instrumentList.get(i);
                                        String instName = ob.getString("name");
                                        Toast.makeText( mContext,instName,Toast.LENGTH_SHORT).show();
                                    }
                                }

                            } else {
                                Log.d("score", "Error: " + e.getMessage());
                            }
                        }



                    });

                    break;
                case "Sprzęt":
                    setContentView(R.layout.listviewstuff);
                    listView = (ListView) findViewById(R.id.listViewStuff);
                    Toast.makeText( mContext,"Select aaaa "+choiceList[selected],Toast.LENGTH_SHORT).show();
                    break;
                case "Akcesoria":

                    Toast.makeText( mContext,"Select aaaa "+choiceList[selected],Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText( mContext,"brak ",Toast.LENGTH_SHORT).show();



            }

        } else {
            alertDialog = new AlertDialog.Builder(PlanRiderActivity.this)
                    .setTitle("Brak Internetu")
                    .setMessage("brak połączenia z siecią Internet")
                    .setNegativeButton("Ok", new AlertDialog.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            alertDialog.cancel();
                            finish();
                        }
                    })
                    .create();
            alertDialog.show();


        }

   ; }

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
