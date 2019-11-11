package com.example.hangman;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.*;
import java.util.*;

import logisk.Galgelogik;


public class spillet extends AppCompatActivity {
    String bogstav, synligtord1;
    int point = 1,point2=1;
    datasingleton list = datasingleton.getInstance();
    //
    Galgelogik spil = new Galgelogik();

    //det der bliver sendt ind
    Button send, tilbage;
    EditText gættetbogstav;
    TextView synligtord, opdateringstekst;
    ImageView hangman, back, reset;





    int antalforkertbogstaver = spil.getAntalForkerteBogstaver();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spillet);

        //henter ord fra internettet
        class getwordsfrominternet extends AsyncTask{
            @Override
            protected Object doInBackground(Object[] objects) {
                try{
                    spil.hentOrdFraDr();
                } catch (Exception e){
                    e.printStackTrace();
                }
                return null;
            }
        } new getwordsfrominternet().execute();


        //gemmer actionbar for at appen ser lækker ud
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //hangman billede
        hangman = (ImageView) findViewById(R.id.hangman1);
        back = (ImageView) findViewById(R.id.back);
        reset = (ImageView) findViewById(R.id.reset);

        //initiere knapperne også videre.dddd
        synligtord = (TextView) findViewById(R.id.textView7);
        synligtord.setText(spil.getSynligtOrd());

        opdateringstekst = (TextView) findViewById(R.id.hangmanview);


        //initiere gættebogstav
        gættetbogstav = (EditText) findViewById(R.id.editText);

        //nu starter spillet rigtigt
        send = (Button) findViewById(R.id.gæt);

        send.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                /*det første der g'res er at gætte bogstaver*/
                bogstav = gættetbogstav.getText().toString();
                spil.gætBogstav(bogstav);

                if (spil.erSpilletSlut()==false){
                    point++;
                    point2++;
                }
                synligtord.setText(spil.getSynligtOrd());

                //foretager animationen
                antalforkertbogstaver = spil.getAntalForkerteBogstaver();
                billede(antalforkertbogstaver);

                ///hvis man vinder eller taber
                Intent tilwinning = new Intent(spillet.this, winning.class);
                if(spil.erSpilletVundet()==true) {
                    tilwinning.putExtra("point", point);
                    startActivity(tilwinning);
                    spil.nulstil();
                    billede(0);
                    synligtord.setText(spil.getSynligtOrd());
                }

                Intent tilloosing = new Intent(spillet.this,losingscreen.class);
                if(spil.erSpilletTabt()==true){
                    list.addvalue(point);
                    tilloosing.putExtra("point1",point2);
                    startActivity(tilloosing);
                    spil.nulstil();
                    billede(0);
                    point2=0;
                    point2=0;
                    synligtord.setText(spil.getSynligtOrd());
                }

            }
        });



        reset.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                spil.nulstil();
                billede(0);
                synligtord.setText(spil.getSynligtOrd());
            }
        });

        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                spil.nulstil();
                billede(0);
                point=0;
                synligtord.setText(spil.getSynligtOrd());
            }
        });

        //
    }

    public void billede(int spil){
        switch (spil) {
            case 0:
                hangman.setImageResource(R.drawable.galge);
                break;
            case 1:
                hangman.setImageResource(R.drawable.forkert1);
                break;
            case 2:
                hangman.setImageResource(R.drawable.forkert2);
                break;
            case 3:
                hangman.setImageResource(R.drawable.forkert3);
                break;
            case 4:
                hangman.setImageResource(R.drawable.forkert4);
                break;
            case 5:
                hangman.setImageResource(R.drawable.forkert5);
                break;
            case 6:
                hangman.setImageResource(R.drawable.forkert6);
                break;
        }
    }



}
