package com.example.trzewa.bandrider;

import android.app.ActionBar;
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
    private static final String TAG = "DialogDemo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       wybranaKategoria("Instrument");

    }
    private Context mContext;
    final String[] choiceList =
            {"Instrumenty", "Sprzęt" , "Akcesoria" };
    private int selected = 0;
    private int buffKey = 0; // add buffer value
                    private void showDialogButtonClick() {

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
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog,
                                                                int which) {
                                                Log.d(TAG, "Which value=" + which);
                                                Log.d(TAG, "Selected value=" + buffKey);

                                                //set buff to selected
                                                selected = buffKey;
                                                //  Intent intent = new Intent(ShowCategoriesActivity.this, PlanRiderActivity.class);
                                                // intent.putExtra(Constans.CATEGORY_SELECTED, selected);
                                                //  startActivity(intent);
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

                               Toast.makeText(getApplicationContext(), "nawiazano połączenie", Toast.LENGTH_LONG).show();


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
                                                   map.put("nazwa_inst", ob.getString("name"));
                                                   map.put("kategoria_inst", ob.getString("category"));
                                                   map.put("wlasciciel_inst", ob.getString("owner"));
                                                   map.put("status_inst", ob.getString("Switchstatus"));
                                                   map.put("objectId", ob.getObjectId());
                                                   map.put("data_modyfikacji", ob.getUpdatedAt().toString());
                                                   map.put("data_utworzenia", ob.getCreatedAt().toString());
                                                   instrumentItems.add(map);
                                               }
                                               Toast.makeText(getApplicationContext(), "Liczba pobranych instrumentów/sprzętu "  + instrumentItems.size(), Toast.LENGTH_SHORT).show();
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





                /*
              case "Sprzęt":

                    Toast.makeText( mContext,"Select aaaa "+Constans.choiceList[selected],Toast.LENGTH_SHORT).show();
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
                    setContentView(R.layout.listviewaccesories);

                    Toast.makeText( mContext,"Select aaaa "+Constans.choiceList[selected],Toast.LENGTH_SHORT).show();
                    break;
*/


                           }

                           else

                           {
                               alertDialog = new AlertDialog.Builder(EditionActivity.this)
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
                       }
                       catch(Exception e)
                       {

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
                return true;
            case R.id.sprzet:
                wybranaKategoria("Stuff");
                return true;
            case R.id.akcesoria:
                wybranaKategoria("bb");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
                }
