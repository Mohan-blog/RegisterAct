package com.adteccorp.registeract;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText etuser, etemailid, etpassword, etconfirmpassword;
    RadioButton malebtn, femalebtn;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        init();
    }

    private void init() {
        etuser = findViewById( R.id.etusername );
        etemailid = findViewById( R.id.etemail );
        etpassword = findViewById( R.id.etpassword );
        etconfirmpassword = findViewById( R.id.etconfirmpassword );
        malebtn = findViewById( R.id.male );
        femalebtn = findViewById( R.id.female );


    }


    public void register(View view) {
        sharedPreferences = getSharedPreferences( "Info", 0 );
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString( "username", String.valueOf( etuser ) );
        editor.putString( "useremail", String.valueOf( etemailid ) );
        editor.putString( "userpassword", String.valueOf( etpassword ) );
        editor.putString( "userconfirmpassword", String.valueOf( etconfirmpassword ) );
        editor.putString( "malebtn", String.valueOf( malebtn ) );
        editor.putString( "femalebtn", String.valueOf( femalebtn ) );
        editor.apply();
    }
}
