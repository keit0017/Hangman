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

public class MainActivity extends AppCompatActivity implements OnClickListener {

     Button startspil,hjælp,afslut,topliste;

     /*dette er menuen der introducere spilleren*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //nu laver vi super.oncreate
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //gemmer actionbar for at appen ser lækker ud
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //definere mine knapper således
        startspil = (Button)findViewById(R.id.startspilknap);
        hjælp = (Button)findViewById(R.id.hjelpmeny);
        afslut = (Button)findViewById(R.id.afslut);
        topliste = (Button) findViewById(R.id.topliste);


        // nu til onclick
        startspil.setOnClickListener( this);
        hjælp.setOnClickListener( this);
        afslut.setOnClickListener( this);
        topliste.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v == startspil) {

            Intent tilspil = new Intent(this, splashscreeen.class);
            startActivity(tilspil);

        } else if (v == hjælp) {

            Intent tilhjælp = new Intent(this, hjelp.class);
            startActivity(tilhjælp);

        } else if (v == afslut) {
            this.finish();
            System.exit(0);
        } else if (v == topliste){
            Intent tiltopliste = new Intent(this, toplist.class);
            startActivity(tiltopliste);
        }

    }


}
