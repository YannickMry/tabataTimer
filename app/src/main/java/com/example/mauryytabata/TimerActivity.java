package com.example.mauryytabata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mauryytabata.db.Tabata;

import java.util.ArrayList;
import java.util.HashMap;

public class TimerActivity extends AppCompatActivity {

    // VIEW
    private Button startButton;
    private Button pauseButton;
    private TextView timerValue;
    private TextView nameValue;
    private RelativeLayout timerLayout;

    // DATA
    private long updatedTime;
    private String updatedName;
    private String updatedColor;
    private CountDownTimer timer;
    private Tabata tabata;
    private int tabataStep = 0;
    private ArrayList<String> sequence;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        tabata = getIntent().getExtras().getParcelable(AjouterModifierSeanceActivity.EXTRA_TABATA);

        setTitle(tabata.getName());

        sequence = tabata.createSeance();

        // Récupérer les view
        timerLayout = (RelativeLayout) findViewById(R.id.timer_layout);
        timerValue = (TextView) findViewById(R.id.timerValue);
        startButton = (Button) findViewById(R.id.startButton);
        pauseButton = (Button) findViewById(R.id.pauseButton);
        nameValue = (TextView) findViewById(R.id.nameValue);
        //
        nextStep();
    }

    public void nextStep(){
        if(tabataStep < sequence.size()){
            updatedTime = (tabata.getCurrentTimer(tabataStep) + 1) * 1000;
            updatedName = tabata.getCurrentName(sequence.get(tabataStep));
            updatedColor = tabata.getCurrentColor(sequence.get(tabataStep));

            if (sequence.get(tabataStep) == "travail"){
                this.mediaPlayer = MediaPlayer.create(this, R.raw.sifflet);
                this.mediaPlayer.start();
            } else if (sequence.get(tabataStep) == "repos" || sequence.get(tabataStep) == "repos_long"){
                this.mediaPlayer = MediaPlayer.create(this, R.raw.cloche);
                this.mediaPlayer.start();
            }

            if (mediaPlayer != null){
                this.mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        timerStart();
                    }
                });
            } else {
                timerStart();
            }

            tabataStep++;
            this.mediaPlayer = null;
        }else{
            this.mediaPlayer = MediaPlayer.create(this, R.raw.applaudissement);
            this.mediaPlayer.start();
            updatedTime = 0;
            updatedName = "Fin !";
            miseAJour();
        }

    }

    public void timerStart(){
        timer = new CountDownTimer(updatedTime, 10) {

            public void onTick(long millisUntilFinished) {
                updatedTime = millisUntilFinished;
                miseAJour();
            }

            public void onFinish() {
                updatedTime = 0;
                miseAJour();
                nextStep();
            }

        }.start();
    }

    // Mettre en pause le compteur
    public void onPause(View view) {
        if (timer != null) {
            timer.cancel(); // Arrete le CountDownTimer
        }
    }

    // Mettre en pause le compteur
    public void onStart(View view) {
        if (timer != null) {
            timer.start(); // Arrete le CountDownTimer
        }
    }


    // Mise à jour graphique
    private void miseAJour() {

        // Décompositions en secondes et minutes
        int secs = (int) (updatedTime / 1000);
        int mins = secs / 60;
        secs = secs % 60;

        // Affichage du "timer"
        timerValue.setText("" + mins + ":"
                + String.format("%02d", secs));
        nameValue.setText(updatedName);
        timerLayout.setBackgroundColor(Color.parseColor(updatedColor));
    }
}
