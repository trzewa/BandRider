package com.example.trzewa.bandrider;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class RegisterActivity extends ActionBarActivity implements View.OnClickListener{

    private EditText NickEditText;
    private EditText LoginEditText;
    private EditText PasswordEditText;
    //private EditText CityEditText;
    private Button CreateAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        bindFields();
    }

    private void bindFields() {
        NickEditText = (EditText) findViewById(R.id.editTextNick);
        LoginEditText = (EditText) findViewById(R.id.editTextLogin);
        PasswordEditText = (EditText) findViewById(R.id.editTextPassword);
        //CityEditText = (EditText) findViewById(R.id.editText);
        CreateAccountButton = (Button) findViewById(R.id.buttonRejestruj);
        CreateAccountButton.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonRejestruj:
                signUpUser();
                break;
        }

    }

    public static void startRegisterActivity(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);

    }

    private void signUpUser() {
        String nick = NickEditText.getText().toString();
        String login = LoginEditText.getText().toString();
        String password = PasswordEditText.getText().toString();
        //String city = CityEditText.getText().toString();

        ParseUser user = new ParseUser();
        user.setUsername(login);
        user.setPassword(password);
        user.setEmail(login);

        user.put(Constans.NICKNAME_PARSE_KEY, nick);


        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {

                    ChoiceActivity.startChoiceActivity(RegisterActivity.this);
                    finish();
                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                    Toast.makeText(getApplicationContext(), "Sign up error: " + e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
