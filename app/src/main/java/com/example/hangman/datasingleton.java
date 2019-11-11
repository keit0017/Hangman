package com.example.hangman;

import java.util.*;

public class datasingleton {

    private static final datasingleton INSTANCE = new datasingleton();

    ArrayList<String> highscores = new ArrayList<>();

    private datasingleton() {}

    public static datasingleton getInstance() {
        return INSTANCE;
    }

    public void addvalue(int input){
        String a= Integer.toString(input);
        highscores.add(a);
        sort();
    }

    public void sort(){
        Collections.sort(highscores);
        Collections.reverse(highscores);
    }

    public ArrayList<String> getHighscores() {
        return highscores;
    }
}
