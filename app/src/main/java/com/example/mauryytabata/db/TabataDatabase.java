package com.example.mauryytabata.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Tabata.class}, version = 1)
public abstract class TabataDatabase extends RoomDatabase {

    private static TabataDatabase instance;

    public abstract TabataDao tabataDao();

    public static synchronized TabataDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), TabataDatabase.class, "tabata_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
