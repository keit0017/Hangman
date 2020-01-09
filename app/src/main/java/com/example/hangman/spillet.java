package com.example.hangman;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import logisk.Galgelogik;


public class spillet extends AppCompatActivity {
    /*
    dette er spillet.
     */

    String bogstav, synligtord1;
    int point = 1, point2 = 1;
    datasingleton list = datasingleton.getInstance();
    Galgelogik spil = new Galgelogik();

    //det der bliver sendt ind
    Button send, tilbage;
    EditText gættetbogstav;
    TextView synligtord, opdateringstekst, gættetord;
    ImageView hangman, back, reset;

    int antalforkertbogstaver = spil.getAntalForkerteBogstaver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spillet);
        //initiere knapperne også videre.dddd
        synligtord = (TextView) findViewById(R.id.textView7);

        //lyde der benyttes på lyde
        final MediaPlayer correctSound = MediaPlayer.create(this, R.raw.rightchoice);
        final MediaPlayer wrongSound = MediaPlayer.create(this, R.raw.wrongchoise);
        final MediaPlayer winnersound = MediaPlayer.create(this, R.raw.victory);
        final MediaPlayer losersound = MediaPlayer.create(this, R.raw.losss);

        //henter ord fra internettet
        class getwordsfrominternet extends AsyncTask {
            @Override
            protected Object doInBackground(Object[] objects) {
                try {
                    spil.hentOrdFraDr();
                    synligtord.setText(spil.getSynligtOrd());

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
        new getwordsfrominternet().execute();

        //gemmer actionbar for at appen ser lækker ud
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //hangman billede
        hangman = (ImageView) findViewById(R.id.hangman1);
        back = (ImageView) findViewById(R.id.back);
        reset = (ImageView) findViewById(R.id.reset);

        //henter det synlige ord
        synligtord.setText(spil.getSynligtOrd());
        opdateringstekst = (TextView) findViewById(R.id.hangmanview);

        //henter gættede bogstaver
        gættetord= (TextView) findViewById(R.id.gættedebogstaver);
        gættetord.setText(spil.getBrugteBogstaver().toString());

        //initiere gættebogstav
        gættetbogstav = (EditText) findViewById(R.id.editText);

        //nu starter spillet rigtigt
        send = (Button) findViewById(R.id.gæt);

        synligtord.setText(spil.getSynligtOrd());



        send.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                /*det første der g'res er at gætte bogstaver*/
                bogstav = gættetbogstav.getText().toString();
                spil.gætBogstav(bogstav);
                gættetord.setText(spil.getBrugteBogstaver().toString());


                if (spil.erSpilletSlut() == false) {
                    if (spil.erSidsteBogstavKorrekt() == false) {
                        wrongSound.start();
                        point--;
                        point2--;
                    } else {
                        correctSound.start();
                        point+=10;
                        point2+=10;
                    }
                }

                synligtord.setText(spil.getSynligtOrd());

                //foretager animationen
                antalforkertbogstaver = spil.getAntalForkerteBogstaver();
                billede(antalforkertbogstaver);

                ///hvis man vinder eller taber
                Intent tilwinning = new Intent(spillet.this, winning.class);
                if (spil.erSpilletVundet() == true) {
                    list.addvalue(point);
                    tilwinning.putExtra("point", point);
                    startActivity(tilwinning);
                    winnersound.start();
                    spil.nulstil();
                    billede(0);
                    gættetord.setText(spil.getBrugteBogstaver().toString());
                    synligtord.setText(spil.getSynligtOrd());
                }


                Intent tilloosing = new Intent(spillet.this, losingscreen.class);
                if (spil.erSpilletTabt() == true) {
                    list.addvalue(point);
                    //taber ting
                    tilloosing.putExtra("point1", point2);
                    startActivity(tilloosing);
                    losersound.start();
                    spil.nulstil();
                    billede(0);
                    point2 = 0;
                    point2 = 0;
                    gættetord.setText(spil.getBrugteBogstaver().toString());
                    synligtord.setText(spil.getSynligtOrd());
                }

            }
        });


        reset.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                spil.nulstil();
                billede(0);
                gættetord.setText(spil.getBrugteBogstaver().toString());
                synligtord.setText(spil.getSynligtOrd());
            }
        });

        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                spil.nulstil();
                billede(0);
                point = 0;
                gættetord.setText(spil.getBrugteBogstaver().toString());
                synligtord.setText(spil.getSynligtOrd());
            }
        });

        //
    }

    public void billede(int spil) {
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
