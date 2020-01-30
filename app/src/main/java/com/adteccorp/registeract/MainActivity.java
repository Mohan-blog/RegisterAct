package com.adteccorp.registeract;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText etuser, etemailid, etpassword, etconfirmpassword;

    SharedPreferences sharedPreferences;
    private JSONArray jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        sharedPreferences = getSharedPreferences( "info", 0 );

        init();
    }

    private void init() {
        etuser = findViewById( R.id.etusername );
        etemailid = findViewById( R.id.etemail );
        etpassword = findViewById( R.id.etpassword );
        etconfirmpassword = findViewById( R.id.etconfirmpassword );



    }

    public void register(View view) {
        String value = sharedPreferences.getString( "usersData", null );
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put( "username", getValueFromEditText( etuser ) );
            jsonObject.put( "useremail", getValueFromEditText( etemailid ) );
            jsonObject.put( "userpassword", getValueFromEditText( etpassword ) );
            jsonObject.put( "confirmpass", getValueFromEditText( etconfirmpassword ) );

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (value == null) {
            JSONArray jsonArray = new JSONArray();

            jsonArray.put( jsonObject );

            saveIntoShared( "usersData", jsonArray.toString() );
            navigateToHomeScreen();
        } else if (!isExists( value )) {
            jsonArray.put( jsonObject );

            saveIntoShared( "usersData", jsonArray.toString() );
            navigateToHomeScreen();


        }
    }

    private void navigateToHomeScreen() {
        Toast.makeText( this, " successfully registered", Toast.LENGTH_SHORT ).show();
        startActivity( new Intent( MainActivity.this, loginScreen.class ) );

    }


    public static String getValueFromEditText(EditText editText) {
        return editText.getText().toString().trim();
    }

    private void saveIntoShared(String key, String value) {
        sharedPreferences.edit().putString( key, value ).apply();


    }


    private boolean isExists(String value) {
        boolean isExists = false;
        if (value != null) {
            try {
                jsonArray = new JSONArray( value );
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject( i );
                    if (getValueFromEditText( etemailid ).equals( jsonObject1.getString( "useremail" ) )) {
                        isExists = true;
                        Toast.makeText( this, "email already registred", Toast.LENGTH_SHORT ).show();

                        break;

                    }


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return isExists;
    }
}
