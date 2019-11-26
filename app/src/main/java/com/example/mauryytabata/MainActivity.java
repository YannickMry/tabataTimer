package com.example.mauryytabata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button listeSeance;
    private Button ajouterSeance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listeSeance = findViewById(R.id.main_liste);
        ajouterSeance = findViewById(R.id.main_add);

        listeSeance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListeSeanceActivity.class);
                startActivity(intent);
            }
        });

        ajouterSeance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AjouterSeanceActivity.class);
                startActivity(intent);
            }
        });
    }
}
