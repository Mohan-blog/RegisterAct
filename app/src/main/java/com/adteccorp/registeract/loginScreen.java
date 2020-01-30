package com.adteccorp.registeract;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class loginScreen extends AppCompatActivity {
    EditText etuserEmail, etuserPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.login_screen );
        init();
    }

    private void init() {
        etuserEmail = findViewById( R.id.et_userlogin );
        etuserPassword = findViewById( R.id.et_password );
    }

    public void login(View view) {
        String sEmail = MainActivity.getValueFromEditText( etuserEmail );
        String sPassword = MainActivity.getValueFromEditText( etuserPassword );
        SharedPreferences sharedPreferences = getSharedPreferences( "info", 0 );
        String s_userdata = sharedPreferences.getString( "usersData", "" );

        if (sEmail.equals( "" )) {
            Toast.makeText( this, "Please fill email address", Toast.LENGTH_SHORT ).show();
        } else if (sPassword.equals( "" )) {
            Toast.makeText( this, "Please fill password address", Toast.LENGTH_SHORT ).show();

        } else if (s_userdata == null) {
            Toast.makeText( this, "Please register..", Toast.LENGTH_SHORT ).show();
        } else if (!isUserExist( s_userdata )) {
//please register
            Toast.makeText( this, "Please register..", Toast.LENGTH_SHORT ).show();

        }


    }

    private boolean isUserExist(String s_userdata) {
        boolean isExist = false;
        try {
            JSONArray jsonArray = new JSONArray( s_userdata );
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject( i );
                String sMailid = jsonObject.getString( "useremail" );
                String sPswd = jsonObject.getString( "userpassword" );
                if (sMailid.equals( MainActivity.getValueFromEditText( etuserEmail ) ) && sPswd.equals( MainActivity.getValueFromEditText( etuserPassword ) )) {
                    ///intent
                    Toast.makeText( this, " registered  user..", Toast.LENGTH_SHORT ).show();

                    break;
                } else if (sMailid.equals( MainActivity.getValueFromEditText( etuserEmail ) ) && !sPswd.equals( MainActivity.getValueFromEditText( etuserPassword ) )) {
                    //email pleaseeneter valid credientials
                    Toast.makeText( this, "pleaseeneter valid credientials..", Toast.LENGTH_SHORT ).show();

                    isExist=true;
                    break;
                }


            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


        return isExist;
    }
}
