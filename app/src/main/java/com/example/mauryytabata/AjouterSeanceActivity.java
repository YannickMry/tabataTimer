package com.example.mauryytabata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

    private View template;
    private LinearLayout ajouterSeanceLayout;

    private Tabata tabata;
    private HashMap<String, EditText> editText;
    private String[] tabataStep;
    private EditText editTextName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        this.tabata = new Tabata();
        this.tabataStep = tabata.getTabataStep();
        this.editText = new HashMap<>();

        showAllRow();

        // Ajoute un listener pour mettre à jour la propriété name de l'objet tabata
        editTextName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    tabata.setName(editTextName.getText().toString());
                }
            }
        });
    }

    public void showAllRow(){

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.ajouterSeanceLayout = (LinearLayout) inflater.inflate(R.layout.activity_ajouter_seance, null);

        this.editTextName = this.ajouterSeanceLayout.findViewById(R.id.ajouter_seance_name);


        for (int i = 0; i < tabataStep.length; i++) {
            this.template = inflater.inflate(R.layout.ajouter_seance_template, null);

            TextView tv = (TextView) template.findViewById(R.id.seance_nom);
            tv.setText(tabata.getTabataName(tabataStep[i]));

            ImageView ivP = (ImageView) template.findViewById(R.id.seance_plus);
            ivP.setTag(tabataStep[i]);

            ImageView ivM = (ImageView) template.findViewById(R.id.seance_moins);
            ivM.setTag(tabataStep[i]);

            EditText et = (EditText) template.findViewById(R.id.seance_edit);
            et.setTag(tabataStep[i]);

            this.editText.put(et.getTag().toString(), et);

            ajouterSeanceLayout.addView(template);
        }

        setContentView(ajouterSeanceLayout);
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
        Log.d("Objet", "Tabata: " + this.tabata);
    }
}
