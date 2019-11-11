package com.example.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.*;

public class winning extends AppCompatActivity {

    spillet spil = new spillet();
    int poin;
    TextView pointshow;
    Button prøvigen;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_winning);

        Intent intent= getIntent();
        poin = intent.getIntExtra("point", 0);


        prøvigen =(Button) findViewById(R.id.tryagain);
        pointshow=(TextView) findViewById(R.id.textView9);
        pointshow.setText(String.valueOf(poin));


        prøvigen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tilspil = new Intent(winning.this, spillet.class);
                startActivity(tilspil);
            }
        });
    }
}
