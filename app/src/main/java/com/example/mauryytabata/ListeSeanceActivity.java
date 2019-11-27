package com.example.mauryytabata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.mauryytabata.db.Tabata;
import com.example.mauryytabata.db.TabataViewModel;

import java.util.List;

public class ListeSeanceActivity extends AppCompatActivity {

    private TabataViewModel tabataViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_seance);
        setTitle("Mes séances");

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

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
    }
}
