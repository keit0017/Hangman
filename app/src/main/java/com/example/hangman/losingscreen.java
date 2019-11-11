package com.example.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

public class losingscreen extends AppCompatActivity {
    private int poin;
    private TextView pointshow;
    private Button prøvigen;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_losingscreen);

        Intent intent= getIntent();
        poin = intent.getIntExtra("point1", 0);

        prøvigen =(Button) findViewById(R.id.buttonFORSØG);
        pointshow=(TextView) findViewById(R.id.antalforsøg);
        pointshow.setText(String.valueOf(poin));



        prøvigen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                losingscreen.this.finish();
            }
        });
    }
}
