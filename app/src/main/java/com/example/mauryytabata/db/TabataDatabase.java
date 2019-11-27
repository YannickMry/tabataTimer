package com.example.mauryytabata.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Tabata.class}, version = 1)
public abstract class TabataDatabase extends RoomDatabase {

    private static TabataDatabase instance;

    public abstract TabataDao tabataDao();

    public static synchronized TabataDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), TabataDatabase.class, "tabata_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private TabataDao tabataDao;

        private PopulateDbAsyncTask(TabataDatabase db){
            tabataDao = db.tabataDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            tabataDao.insert(new Tabata("Ma première séance", 10,5,3,20,5,10));
            tabataDao.insert(new Tabata("Ma deuxième séance", 10,5,3,20,5,10));
            tabataDao.insert(new Tabata("Ma troisième séance", 10,5,3,20,5,10));
            return null;
        }
    }
}
