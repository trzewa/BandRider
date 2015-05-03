package com.example.trzewa.bandrider;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class ShowCategoriesActivity extends ActionBarActivity {

    private AlertDialog alertDialog;
    private static final String TAG = "DialogDemo";
    private Context mContext;
    final String[] choiceList =
            {"Instrumenty", "Sprzęt" , "Akcesoria" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_categories);
        int selected = showDialogButtonClick();
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_categories, menu);
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
