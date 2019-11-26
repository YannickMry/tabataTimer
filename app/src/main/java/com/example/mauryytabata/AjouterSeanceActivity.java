package com.example.mauryytabata;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class AjouterSeanceActivity extends AppCompatActivity {

    private LinearLayout ajouterSeanceLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_seance);

        ArrayList<String> tabata = new ArrayList<>();
        tabata.add("Préparation");
        tabata.add("Série");
        tabata.add("Répétition");
        tabata.add("Travail");
        tabata.add("Repos");
        tabata.add("Repos long");



    }
}
