package com.example.mauryytabata.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TabataDao {

    @Insert
    void insert(Tabata tabata);

    @Update
    void update(Tabata tabata);

    @Delete
    void delete(Tabata tabata);

    @Query("DELETE FROM tabata_table")
    void deleteAllTabatas();

    @Query("SELECT * FROM tabata_table")
    LiveData<List<Tabata>> getAllTabatas();
}
