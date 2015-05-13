package com.example.trzewa.bandrider;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class EditionActivity extends ListActivity  {
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       wybranaKategoria("Instrument");

    }


                   private void wybranaKategoria( final String kategoria)
                   {
                       final Context context = getApplicationContext();
                       final ArrayList<HashMap<String, String>> instrumentItems = new ArrayList<>();
                       boolean networkstate = Utilities.getConnectivityStatus(context);
                       ParseUser currentUser = ParseUser.getCurrentUser();
                       String user_name = currentUser.getUsername();

                       final ListAdapter adapter = new SimpleAdapter(getApplicationContext(), instrumentItems,
                               R.layout.activity_edit_item,
                               new String[]{"nazwa_inst", "wlasciciel_inst", "objectId", "kategoria_inst", "status_inst","data_modyfikacji","data_utworzenia"}, new int[]{
                               R.id.textViewNameItem, R.id.textViewOwner, R.id.textVievObjectID, R.id.textViewCategory,R.id.textViewAvailable,R.id.textViewUpdate,R.id.textViewCreated});

                       try {
                           if (networkstate) {

                               //Toast.makeText(getApplicationContext(), "nawiazano połączenie", Toast.LENGTH_LONG).show();


                               //Toast.makeText(getApplicationContext(), "Select ", Toast.LENGTH_SHORT).show();
                               ParseQuery<ParseObject> queryInstruments = ParseQuery.getQuery(kategoria);
                               queryInstruments.whereEqualTo("owner", user_name);
                               queryInstruments.findInBackground(new FindCallback<ParseObject>() {

                                   public void done(List<ParseObject> instrumentList, com.parse.ParseException e) {
                                       if (e == null) {
                                           Log.d("score", "Retrieved " + instrumentList.size() + "instrumentów");
                                           if (instrumentList.size() > 0) {

                                               for (int i = 0; i < instrumentList.size(); i++) {
                                                   HashMap<String, String> map = new HashMap<>();
                                                   ParseObject ob = instrumentList.get(i);
                                                   map.put("nazwa_inst", ob.getString(Constans.INST_NAME));
                                                   map.put("kategoria_inst", ob.getString(Constans.INST_CATEGORY));
                                                   map.put("wlasciciel_inst", ob.getString(Constans.INST_OWNER));
                                                   map.put("status_inst", ob.getString(Constans.INS_SWITCH_STATUS));
                                                   map.put("objectId", ob.getObjectId());
                                                   map.put("data_modyfikacji", ob.getUpdatedAt().toString());
                                                   map.put("data_utworzenia", ob.getCreatedAt().toString());
                                                   instrumentItems.add(map);
                                               }
                                               Toast.makeText(getApplicationContext(), "Liczba pobranych instrumentów/sprzętu "  + instrumentItems.size(), Toast.LENGTH_LONG).show();
                                               //!!!!!!!!! WAŻNE!!!!!!!!///
                                               //Powoduje ponownie przeladowanie adaptera nowo pobranymi danymi///
                                               runOnUiThread(new Runnable() {
                                                   public void run() {
                                                       ((BaseAdapter) adapter).notifyDataSetChanged();
                                                   }
                                               });
                                           }

                                       } else {
                                           Log.d("score", "Error: " + e.getMessage());
                                       }
                                   }


                               });


                               setListAdapter(adapter);

                               ListView w = getListView();
                               w.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                   @Override
                                   public void onItemClick(AdapterView<?> parent, final View view,
                                                           int position, long id) {
                                       CharSequence colors[] = new CharSequence[]{"Usuń", "Edytuj"};

                                       AlertDialog.Builder builder = new AlertDialog.Builder(EditionActivity.this);
                                       builder.setTitle("Co chcesz zrobić?");
                                       builder.setItems(colors, new DialogInterface.OnClickListener() {
                                                   @Override
                                                   public void onClick(DialogInterface dialog, int which) {
                                                       TextView objectIdItem = (TextView) view.findViewById(R.id.textVievObjectID);
                                                       String valueObjectIdItem = objectIdItem.getText().toString();
                                                       switch (which) {
                                                           case 0:

                                                               // obsługa kliknięcia

                                                               ParseQuery<ParseObject> query = ParseQuery.getQuery(kategoria);
                                                               query.getInBackground(valueObjectIdItem, new GetCallback<ParseObject>() {
                                                                   @Override
                                                                   public void done(ParseObject parseObject, ParseException e) {
                                                                       parseObject.deleteInBackground();
                                                                       if (e == null) {
                                                                           Toast.makeText(getApplicationContext(), "Usunięto pomyślnie", Toast.LENGTH_SHORT).show();
                                                                           startActivity(new Intent(EditionActivity.this, EditionActivity.class));
                                                                           finish();


                                                                       } else {
                                                                           Toast.makeText(getApplicationContext(), "Wystąpił błąd podczas usuwania", Toast.LENGTH_SHORT).show();
                                                                       }
                                                                   }

                                                               });

                                                               break;

                                                           case 1:

                                                               // Definiujemy cel
                                                               Intent cel = new Intent(getApplicationContext(), ChangeItem.class);
                                                               // Towrzymy Bundle od razu dodając informacje do Intentu
                                                               cel.putExtra("objectId", valueObjectIdItem);
                                                               cel.putExtra("kategoria",kategoria);
                                                               // Wysyłamy
                                                               startActivity(cel);


                                                               break;
                                                       }
                                                   }
                                               }

                                       );
                                       builder.show();

                                   }
                               });


                           }

                           else

                           {
                               alertDialog = new AlertDialog.Builder(EditionActivity.this)
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
                       catch(Exception e)
                       {
                           Toast.makeText(getApplicationContext(), "Wystąpił błąd: " + e, Toast.LENGTH_SHORT).show();

                       }
                   }

                    @Override
                    public boolean onCreateOptionsMenu(Menu menu) {
                        MenuInflater inflater = getMenuInflater();
                        inflater.inflate(R.menu.menu_edition_activity, menu);
                        return true;
                    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.instrumenty:
                wybranaKategoria("Instrument");
                item.setChecked(true);
                return true;
            case R.id.sprzet:
                wybranaKategoria("Stuff");
                item.setChecked(true);
                return true;
            case R.id.akcesoria:
                wybranaKategoria("Accessories");
                item.setChecked(true);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
                }
