package com.example.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.*;

public class toplist extends AppCompatActivity {

    ListView list;
    ArrayList<String> toplist;
    datasingleton data = datasingleton.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toplist);
        getSupportActionBar().hide();

        data.sort();
        toplist=data.getHighscores();

        list = (ListView) findViewById(R.id.listview1);
        ArrayAdapter adapter= new ArrayAdapter<String> ( this, android.R.layout.simple_list_item_1,  android.R.id.text1, toplist);
        list.setAdapter(adapter);

    }
}
