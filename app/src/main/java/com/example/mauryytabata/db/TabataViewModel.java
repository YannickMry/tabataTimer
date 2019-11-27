package com.example.mauryytabata.db;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TabataViewModel extends AndroidViewModel {

    private TabataRepository tabataRepository;
    private LiveData<List<Tabata>> allTabatas;

    public TabataViewModel(@NonNull Application application) {
        super(application);

        tabataRepository = new TabataRepository(application);
        allTabatas = tabataRepository.getAllTabatas();
    }

    public void insert(Tabata tabata){
        tabataRepository.insert(tabata);
    }

    public void update(Tabata tabata){
        tabataRepository.update(tabata);
    }

    public void delete(Tabata tabata){
        tabataRepository.delete(tabata);
    }

    public void deleteAllTabatas(){
        tabataRepository.deleteAllTabatas();
    }

    public LiveData<List<Tabata>> getAllTabatas(){
       return allTabatas;
    }
}
