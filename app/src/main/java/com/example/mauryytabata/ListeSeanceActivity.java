package com.example.mauryytabata;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.mauryytabata.db.Tabata;
import com.example.mauryytabata.db.TabataViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListeSeanceActivity extends AppCompatActivity {

    private static final int ADD_TABATA_REQUEST = 1;
    private static final int EDIT_TABATA_REQUEST = 2;
    private TabataViewModel tabataViewModel;
    private FloatingActionButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_seance);
        setTitle("Mes séances");

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        this.btnAdd = findViewById(R.id.btn_add_item);

        final TabataAdapter adapter = new TabataAdapter();
        recyclerView.setAdapter(adapter);

        tabataViewModel = ViewModelProviders.of(this).get(TabataViewModel.class);
        tabataViewModel.getAllTabatas().observe(this, new Observer<List<Tabata>>() {
            @Override
            public void onChanged(List<Tabata> tabatas) {
                // update RecyclerView
                adapter.setTabatas(tabatas);
            }
        });

        this.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AjouterModifierSeanceActivity.class);
                startActivityForResult(intent, ADD_TABATA_REQUEST);

            }
        });

        // Ajoute un listener sur le swipe d'un item pour le delete
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                tabataViewModel.delete(adapter.getTabataAtPosition(viewHolder.getAdapterPosition()));
                Toast.makeText(getApplicationContext(), "Séance supprimée !", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new TabataAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Tabata tabata) {
                Toast.makeText(getApplicationContext(), "Séance : " + tabata.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_TABATA_REQUEST && resultCode == RESULT_OK) {
            Tabata tabata = data.getParcelableExtra(AjouterModifierSeanceActivity.EXTRA_TABATA);
            tabataViewModel.insert(tabata);

            Toast.makeText(this, "Séance sauvegardée !", Toast.LENGTH_SHORT).show();
        } else if(requestCode == EDIT_TABATA_REQUEST && resultCode == RESULT_OK){
            int id = data.getIntExtra(AjouterModifierSeanceActivity.EXTRA_ID, -1);
            Tabata tabata = data.getParcelableExtra(AjouterModifierSeanceActivity.EXTRA_TABATA);
            tabata.setId(id);
            tabataViewModel.update(tabata);

            Toast.makeText(this, "Séance modifiée !", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Retour", Toast.LENGTH_SHORT).show();
        }
    }
}
