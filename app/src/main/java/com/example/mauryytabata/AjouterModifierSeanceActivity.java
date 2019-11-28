package com.example.mauryytabata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mauryytabata.db.Tabata;
import com.example.mauryytabata.db.TabataViewModel;

import java.util.ArrayList;
import java.util.HashMap;

public class AjouterModifierSeanceActivity extends AppCompatActivity {

    public static final String EXTRA_TABATA = "EXTRA_TABATA";
    public static final String  EXTRA_ID = "EXTRA_ID";

    private View template;
    private LinearLayout ajouterSeanceLayout;

    private Tabata tabata;
    private HashMap<String, EditText> editText;
    private String[] tabataStep;
    private EditText editTextName;
    private Button btnSave;
    private TabataViewModel tabataViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.tabata = new Tabata();
        this.tabataStep = tabata.getTabataStep();
        this.editText = new HashMap<>();
        this.tabataViewModel = ViewModelProviders.of(this).get(TabataViewModel.class);

        setTitle("Ajouter une séance");

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

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTabata();
            }
        });

    }

    private void saveTabata(){

        EditText ajouterSeanceName = findViewById(R.id.ajouter_seance_name);

        if (ajouterSeanceName.getText().toString().trim().isEmpty()){
            ajouterSeanceName.setError("Vous devez renseigner un nom de séance");
            ajouterSeanceName.requestFocus();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TABATA, tabata);

        setResult(RESULT_OK, data);
        finish();

    }

    public void showAllRow(){

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.ajouterSeanceLayout = (LinearLayout) inflater.inflate(R.layout.activity_ajouter_seance, null);

        this.editTextName = this.ajouterSeanceLayout.findViewById(R.id.ajouter_seance_name);

        this.btnSave = new Button(this);
        this.btnSave.setText("Sauvegarder");


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

            switch (tabataStep[i]){
                case "preparation":
                    et.setText(String.valueOf(tabata.getPreparation()));
                    break;
                case "serie":
                    et.setText(String.valueOf(tabata.getSerie()));
                    break;
                case "repetition":
                    et.setText(String.valueOf(tabata.getRepetition()));
                    break;
                case "travail":
                    et.setText(String.valueOf(tabata.getTravail()));
                    break;
                case "repos":
                    et.setText(String.valueOf(tabata.getRepos()));
                    break;
                case "repos_long":
                    et.setText(String.valueOf(tabata.getReposLong()));
                    break;
                default:
                    break;
            }

            this.editText.put(et.getTag().toString(), et);

            ajouterSeanceLayout.addView(template);
        }
        ajouterSeanceLayout.addView(btnSave);

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
    }
}
