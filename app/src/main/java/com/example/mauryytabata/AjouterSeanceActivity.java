package com.example.mauryytabata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mauryytabata.db.Tabata;

import java.util.ArrayList;
import java.util.HashMap;

public class AjouterSeanceActivity extends AppCompatActivity {

    private LinearLayout ajouterSeanceLayout;
    private LinearLayout ajouterSeanceTemplate;
    private View custom;
    private int count;
    private HashMap<String, EditText> editText;
    private Tabata tabata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout parent = (LinearLayout) inflater.inflate(R.layout.activity_ajouter_seance, null);
        this.count = 0;
        this.tabata = new Tabata();
        this.editText = new HashMap<>();

        ArrayList<String> tabata = new ArrayList<>();
        tabata.add("preparation");
        tabata.add("serie");
        tabata.add("repetition");
        tabata.add("travail");
        tabata.add("repos");
        tabata.add("repos_long");

        HashMap<String, String> tabataName = new HashMap<>();
        tabataName.put("preparation", "Préparation");
        tabataName.put("serie", "Série");
        tabataName.put("repetition", "Répétition");
        tabataName.put("travail", "Travail");
        tabataName.put("repos", "Repos");
        tabataName.put("repos_long", "Repos long");

        for (int i = 0; i < tabata.size(); i++) {
            this.custom = inflater.inflate(R.layout.ajouter_seance_template, null);

            TextView tv = (TextView) custom.findViewById(R.id.seance_nom);
            tv.setText(tabataName.get(tabata.get(i)));

            ImageView ivP = (ImageView) custom.findViewById(R.id.seance_plus);
            ivP.setTag(tabata.get(i));

            ImageView ivM = (ImageView) custom.findViewById(R.id.seance_moins);
            ivM.setTag(tabata.get(i));

            EditText et = (EditText) custom.findViewById(R.id.seance_edit);
            et.setTag(tabata.get(i));

            this.editText.put(et.getTag().toString(), et);

            parent.addView(custom);
        }

        setContentView(parent);
    }

    public void add(View view){
        String tag = view.getTag().toString();
        int number = tabata.add(tag);
        update(tag, number);
    }

    public void remove(View view){
        String tag = view.getTag().toString();
        int number = tabata.remove(tag);
        update(tag, number);
    }

    public void update(String tag, int number){
        EditText editText = this.editText.get(tag);
        editText.setText(String.valueOf(number));
    }
}
