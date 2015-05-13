package com.example.trzewa.bandrider;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.HashMap;


public class ChangeItem extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_item);
        //Przypisanie przyciskow i pol z activity_change_item
        final TextView dateUpdate=(TextView) findViewById(R.id.textViewChangeItemUpdate);
        final TextView dateCreated=(TextView) findViewById(R.id.textViewChangeItemCreated);
        final TextView objectId=(TextView) findViewById(R.id.textViewChangeItemId);
        final TextView owner=(TextView) findViewById(R.id.textViewChangeItemOwner);
        final EditText nazwa_inst = (EditText) findViewById(R.id.editViewChangeItemName);
        final Spinner spinnerCategory = (Spinner)findViewById(R.id.spinnerChangeItemCategory);
        final Spinner spinnerStatus = (Spinner)findViewById(R.id.spinnerChangeItemStatus);
        final String[] Status = new String[]{"dostępny", "niedostępny"};
        final Bundle przekazaneDane = getIntent().getExtras();
        Button buttonSave = (Button) findViewById(R.id.buttonChangeItemSave);

        //zapytanie do bazy z przekazana kategoria jako parametr
        ParseQuery<ParseObject> query = ParseQuery.getQuery(przekazaneDane.getString("kategoria"));
        query.getInBackground(przekazaneDane.getString("objectId"), new GetCallback<ParseObject>() {


            public void done(ParseObject parseObject, ParseException e) {
                if (e == null) {

                    HashMap<String, String> map = new HashMap<>();
                    map.put("nazwa_inst", parseObject.getString(Constans.INST_NAME));
                    map.put("kategoria_inst", parseObject.getString(Constans.INST_CATEGORY));
                    map.put("wlasciciel_inst", parseObject.getString(Constans.INST_OWNER));
                    map.put("status_inst", parseObject.getString(Constans.INS_SWITCH_STATUS));
                    map.put("objectId", parseObject.getObjectId());
                    map.put("data_modyfikacji", parseObject.getUpdatedAt().toString());
                    map.put("data_utworzenia", parseObject.getCreatedAt().toString());

                    //Ustawienie pobranych danych w widoku
                    dateUpdate.setText(map.get("data_modyfikacji"));
                    dateCreated.setText(map.get("data_utworzenia"));
                    objectId.setText(map.get("objectId"));
                    owner.setText(map.get("wlasciciel_inst"));
                    nazwa_inst.setText(map.get("nazwa_inst"));

                    //W zależności od kategori jaką otrzymaliśmy ustawia odpowiedni spinner kategorii
                    if((przekazaneDane.getString("kategoria")).equals("Instrument"))
                    {
                        for(int i=0;i < Constans.ELEMENTY_INSTRUMENTS.length;i++ )
                     {
                        if(Constans.ELEMENTY_INSTRUMENTS[i].equals(map.get("kategoria_inst")))
                        { ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spineritem, Constans.ELEMENTY_INSTRUMENTS);
                            spinnerCategory.setAdapter(adapter);
                            spinnerCategory.setSelection(i);
                            break;
                        }
                     }
                    } else  if(przekazaneDane.getString("kategoria").equals("Stuff"))
                    {for(int i=0;i < Constans.ELEMENTY_STUFF.length;i++ )
                        {
                            if(Constans.ELEMENTY_STUFF[i].equals(map.get("kategoria_inst")))
                            { ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spineritem, Constans.ELEMENTY_STUFF);
                                spinnerCategory.setAdapter(adapter);
                                spinnerCategory.setSelection(i);
                                break;
                            }
                        }
                    }
                    else if (przekazaneDane.getString("kategoria").equals("Accessories"))
                    {
                        for(int i=0;i < Constans.ELEMENTY_INSTRUMENTS.length;i++ )
                        {
                            if(Constans.ELEMENTY_ACCESSORIES[i].equals(map.get("kategoria_inst")))
                            { ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spineritem, Constans.ELEMENTY_ACCESSORIES);
                                spinnerCategory.setAdapter(adapter);
                                spinnerCategory.setSelection(i);
                                break;
                            }
                        }

                    }

                    //Sprawdza spinner dla statusu i ustawia na aktualny
                    ArrayAdapter<String> adapterStatus = new ArrayAdapter<>(getApplicationContext(), R.layout.spineritem, Status);
                    spinnerStatus.setAdapter(adapterStatus);
                    if(Status[0].equals("dostępny"))
                    {
                        spinnerStatus.setSelection(0);
                    }
                    else {spinnerStatus.setSelection(1);}


                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });

        //Obsługa kliknięcia przycisku zapisu, aktualizacja danych w bazie
        buttonSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> query = ParseQuery.getQuery(przekazaneDane.getString("kategoria"));
                query.getInBackground(objectId.getText().toString(), new GetCallback<ParseObject>() {
                    public void done(ParseObject Instrument, ParseException e) {
                        if (e == null) {
                            // Now let's update it with some new data. In this case, only cheatMode and score
                            // will get sent to the Parse Cloud. playerName hasn't changed.
                            Instrument.put("name", nazwa_inst.getText().toString());
                            Instrument.put(Constans.INS_SWITCH_STATUS, spinnerStatus.getSelectedItem().toString());
                            Instrument.put("category", spinnerCategory.getSelectedItem().toString());
                            Instrument.saveInBackground();
                            Toast.makeText(getApplicationContext(), "Zapisano", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ChangeItem.this, EditionActivity.class));
                            finish();
                        }
                    }
                });

            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_change_item, menu);
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
