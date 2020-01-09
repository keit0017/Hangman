package com.example.hangman;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class hjelp extends AppCompatActivity implements OnClickListener {

    Button menu;

    /*hjælp skærm*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hjelp);

        //gemmer actionbar for at appen ser lækker ud
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        menu =(Button)findViewById(R.id.tilbagetilmenu);
        menu.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v == menu) {
            this.finish();
        }

    }



}
