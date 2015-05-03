package com.example.trzewa.bandrider;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
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
        boolean networkstate = Utilities.getConnectivityStatus(context);
        int selected = showDialogButtonClick();

        //listView.setVisibility(View.INVISIBLE);
        if (networkstate == true) {

            Toast.makeText(getApplicationContext(), "nawiazano połączenie", Toast.LENGTH_LONG).show();

            //Toast.makeText( mContext,"Select aaaa "+choiceList[selected],Toast.LENGTH_SHORT).show();
            /*


            tutaj obsługa listy - zmienna selected zwraca indeks z tablicy choiceList
            zależnie co zostało zwrócone możemy zrobić tu obsługę dla odpowiedniej listy np.

            if selected.equals("Instrumenty") // albo może lepiej case
            {
              ładujemy widok dla listy instrumentów
              tworzymy adapter
              na liście głównej najlepiej jakby elementy wyglądały tak samo np.
              nazwa instrumentu właściciel ocena
              dla sprzętu
              nazwa instrumentu właściciel ocena
              a np różne dla item_selected
            }
             */
            switch(choiceList[selected]) {
                case "Instrumenty":
                    setContentView(R.layout.activity_plan_rider);
                    listView = (ListView) findViewById(R.id.listViewRaider);
                    Toast.makeText( mContext,"Select aaaa "+choiceList[selected],Toast.LENGTH_SHORT).show();
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
        /*final Spinner spinner = (Spinner) findViewById(R.id.spinnerCategory);


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Constans.ELEMENTY);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int id, long position) {
                ParseUser currentUser = ParseUser.getCurrentUser();
                String test = currentUser.getUsername();
                Toast.makeText(PlanRiderActivity.this, "Wybrano opcję " + (id + 1), Toast.LENGTH_SHORT).show();
                Toast.makeText(PlanRiderActivity.this, "Wybrano opcję " + (test), Toast.LENGTH_SHORT).show();
                String category = null;

                switch ((int) position) {
                    case 0:
                        category = "strunowe";

                        Toast.makeText(PlanRiderActivity.this, category, Toast.LENGTH_SHORT).show();
                        listaZmiana(category);
                        break;
                    case 1:
                        category = "dęte";
                        Toast.makeText(PlanRiderActivity.this, category, Toast.LENGTH_SHORT).show();
                        listaZmiana(category);
                        break;
                    case 2:
                        category = "perkusyjne";

                        Toast.makeText(PlanRiderActivity.this, category, Toast.LENGTH_SHORT).show();

                        break;
                    case 3:

                        break;
                    case 4:
                        //wybrano piąty element
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });*/





    }

    private int selected = 0;
    private int buffKey = 0; // add buffer value
    private int showDialogButtonClick() {
        Log.i(TAG, "show Dialog ButtonClick");
        AlertDialog.Builder builder =
                new AlertDialog.Builder(mContext);
        builder.setTitle("Wybierz kategorię");



        builder.setSingleChoiceItems(
                choiceList,
                selected,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(
                            DialogInterface dialog,
                            int which) {
                        //set to buffKey instead of selected
                        //(when cancel not save to selected)
                        buffKey = which;
                    }
                })
                .setCancelable(false)
                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                Log.d(TAG,"Which value="+which);
                                Log.d(TAG,"Selected value="+buffKey);

                                //set buff to selected
                                selected = buffKey;
                               // listView.setVisibility(View.VISIBLE);

                            }
                        }

                )
                .setNegativeButton("Anuluj",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {

                            }
                        }
                );

        AlertDialog alert = builder.create();
        alert.show();
        return selected;
    }



    public void listaZmiana (String category) {
        Toast.makeText(PlanRiderActivity.this, category + " OKI", Toast.LENGTH_SHORT).show();
        /*ParseQuery<ParseObject> query = ParseQuery.getQuery(category);
        query.whereEqualTo("category", category);


        query.findInBackground(new FindCallback<ParseObject>()

        {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    Log.d("name", "Retrieved " + scoreList.size() + " scores");

                } else {
                    Log.d("name", "Error: " + e.getMessage());
                }
            }

            @Override
            public void done(List<ParseObject> parseObjects, com.parse.ParseException e) {

            }

        })
*/
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
