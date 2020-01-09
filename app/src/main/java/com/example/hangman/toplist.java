package com.example.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.*;

public class toplist extends AppCompatActivity implements AdapterView.OnItemClickListener {

    /*
    her gemmes hemmelighederne til toplisten
     */

    ListView listView;
    ArrayList<String> toplist,date;
    datasingleton data = datasingleton.getInstance();
    Button savebutton;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toplist);
        getSupportActionBar().hide();
        toplist=data.getHighscores();

        //loader data
        loaddatatop();

        //får dato
        calendar = Calendar.getInstance();
        String currentdate = DateFormat.getDateInstance().format(calendar.getTime());

        savebutton =(Button) findViewById(R.id.savebutton);
        listView = (ListView) findViewById(R.id.listview1);

        //der tilføjes ting til mit custom array.
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.adapter_view_layout, R.id.placering, toplist){
            @Override
            public View getView(int position, View cachedView, ViewGroup parent) {
                int placering3 = position+1;
                View view = super.getView(position, cachedView, parent);

                TextView beskrivelse = view.findViewById(R.id.datetextview);
                beskrivelse.setText(currentdate);

                TextView placering = view.findViewById(R.id.scoretextview);
                placering.setText("Nr:" + placering3);
                return view;
        }};

        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        //gemmer knappen
        savebutton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savedatatoplist();
            }
        });

        listView.setOnItemClickListener(this);


    }

    public void onItemClick(AdapterView<?> l, View v, int position, long id) { Toast.makeText(this, "du har valgt spiller: " + position, Toast.LENGTH_SHORT).show(); }

    /*
    mine preferances gemmes her
     */

    public void savedatatoplist(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared prefferences", MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(toplist);
        editor.putString("top_listqwer",json);
        editor.apply();
    }

    public void loaddatatop(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared prefferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("top_listqwer",null);
        Type type= new TypeToken<ArrayList<String>>() {}.getType();
        toplist= gson.fromJson(json,type);
        if(toplist==null){
            toplist= data.getHighscores();
        }
    }


}
