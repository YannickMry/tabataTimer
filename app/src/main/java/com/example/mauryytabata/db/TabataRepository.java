package com.example.mauryytabata.db;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TabataRepository {

    private TabataDao tabataDao;
    private LiveData<List<Tabata>> allTabatas;

    public TabataRepository(Application application){
        TabataDatabase database = TabataDatabase.getInstance(application);
        tabataDao = database.tabataDao();
        allTabatas = tabataDao.getAllTabatas();
    }

    public void insert(Tabata tabata){
        new InsertTabataAsyncTask(tabataDao).execute(tabata);
    }

    public void update(Tabata tabata){
        new UpdateTabataAsyncTask(tabataDao).execute(tabata);
    }

    public void delete(Tabata tabata){
        new DeleteTabataAsyncTask(tabataDao).execute(tabata);
    }

    public void deleteAllTabatas(){
        new DeleteAllTabatasAsyncTask(tabataDao).execute();
    }

    public LiveData<List<Tabata>> getAllTabatas(){
        return allTabatas;
    }

    private static class InsertTabataAsyncTask extends AsyncTask<Tabata, Void, Void>{

        private TabataDao tabataDao;

        private InsertTabataAsyncTask(TabataDao tabataDao){
            this.tabataDao = tabataDao;
        }

        @Override
        protected Void doInBackground(Tabata... tabatas) {
            tabataDao.insert(tabatas[0]);
            return null;
        }
    }

    private static class UpdateTabataAsyncTask extends AsyncTask<Tabata, Void, Void>{

        private TabataDao tabataDao;

        private UpdateTabataAsyncTask(TabataDao tabataDao){
            this.tabataDao = tabataDao;
        }

        @Override
        protected Void doInBackground(Tabata... tabatas) {
            tabataDao.update(tabatas[0]);
            return null;
        }
    }

    private static class DeleteTabataAsyncTask extends AsyncTask<Tabata, Void, Void>{

        private TabataDao tabataDao;

        private DeleteTabataAsyncTask(TabataDao tabataDao){
            this.tabataDao = tabataDao;
        }

        @Override
        protected Void doInBackground(Tabata... tabatas) {
            tabataDao.delete(tabatas[0]);
            return null;
        }
    }

    private static class DeleteAllTabatasAsyncTask extends AsyncTask<Void, Void, Void>{

        private TabataDao tabataDao;

        private DeleteAllTabatasAsyncTask(TabataDao tabataDao){
            this.tabataDao = tabataDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            tabataDao.deleteAllTabatas();
            return null;
        }
    }
}
