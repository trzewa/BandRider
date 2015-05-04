package com.example.trzewa.bandrider;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
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


public class PlanRiderActivity extends ListActivity {
    private AlertDialog alertDialog;
    private static final String TAG = "DialogDemo";
    private Context mContext;
    final String[] choiceList =
            {"Instrumenty", "Sprzęt" , "Akcesoria" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Context context = getApplicationContext();
        mContext = this;
        final ArrayList<HashMap<String, String>> instrumentItems = new ArrayList<>();
        final ArrayList<HashMap<String, String>> stuffItems = new ArrayList<>();
        Intent intent = getIntent();
        int selected  = intent.getIntExtra(Constans.CATEGORY_SELECTED, 0);
        boolean networkstate = Utilities.getConnectivityStatus(context);
        if (networkstate == true) {

            Toast.makeText(getApplicationContext(), "nawiazano połączenie", Toast.LENGTH_LONG).show();

            switch(choiceList[selected]) {
                case "Instrumenty":

                    Toast.makeText( mContext,"Select aaaa "+choiceList[selected],Toast.LENGTH_SHORT).show();
                    ParseQuery<ParseObject> queryInstruments = ParseQuery.getQuery("Instrument");
                    queryInstruments.findInBackground(new FindCallback<ParseObject>() {

                        public void done(List<ParseObject> instrumentList, com.parse.ParseException e) {
                            if (e == null) {
                                Log.d("score", "Retrieved " + instrumentList.size() + "instrumentów");
                                if(instrumentList.size() > 0)
                                {
                                    for(int i=0;i<instrumentList.size();i++)
                                    {
                                        HashMap<String, String> map = new HashMap<>();
                                        ParseObject ob = instrumentList.get(i);
                                        map.put("nazwa_inst", ob.getString("name"));
                                        map.put("kategoria_inst", ob.getString("category"));
                                        map.put("wlasciciel_inst", ob.getString("owner"));
                                        map.put("status_inst", ob.getString("Switchstatus"));
                                        instrumentItems.add(map);
                                        Toast.makeText( mContext,map.get("nazwa_inst"),Toast.LENGTH_SHORT).show();
                                    }
                                }

                            } else {
                                Log.d("score", "Error: " + e.getMessage());
                            }
                        }



                    });
                    setContentView(R.layout.activity_plan_rider);
                    //stView = (ListView) findViewById(R.id.list);
                    ListAdapter adapter = new SimpleAdapter(this, instrumentItems,
                            R.layout.item,
                            new String[]{"nazwa_inst", "wlasciciel_inst"}, new int[]{
                            R.id.nazwa, R.id.wlasciciel});

                    setListAdapter(adapter);


                    break;
                case "Sprzęt":

                    Toast.makeText( mContext,"Select aaaa "+choiceList[selected],Toast.LENGTH_SHORT).show();
                    ParseQuery<ParseObject> queryStuff = ParseQuery.getQuery("Stuff");
                    queryStuff.findInBackground(new FindCallback<ParseObject>() {

                        public void done(List<ParseObject> stuffList, com.parse.ParseException e) {
                            if (e == null) {
                                Log.d("score", "Retrieved " + stuffList.size() );
                                if(stuffList.size() > 0)
                                {
                                    for(int i=0;i<stuffList.size();i++)
                                    {
                                        HashMap<String, String> map = new HashMap<>();
                                        ParseObject ob = stuffList.get(i);
                                        map.put("nazwa_sprzet", ob.getString("name"));
                                        map.put("kategoria_sprzet", ob.getString("category"));
                                        map.put("wlasciciel_sprzet", ob.getString("owner"));
                                        stuffItems.add(map);
                                        Toast.makeText( mContext,map.get("nazwa_sprzet"),Toast.LENGTH_SHORT).show();
                                    }
                                }

                            } else {
                                Log.d("score", "Error: " + e.getMessage());
                            }
                        }



                    });
                    setContentView(R.layout.listviewstuff);
                    ListAdapter adapter_stuff = new SimpleAdapter(this, stuffItems,
                           R.layout.item,
                            new String[]{"nazwa_sprzet", "wlasciciel_sprzet"}, new int[]{
                            R.id.nazwa, R.id.wlasciciel});

                    setListAdapter(adapter_stuff);


                    break;
                case "Akcesoria":

                    Toast.makeText( mContext,"Select aaaa "+choiceList[selected],Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText( mContext,"brak danych - nieznana kategoria",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(PlanRiderActivity.this, ShowCategoriesActivity.class));
                    finish();



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
