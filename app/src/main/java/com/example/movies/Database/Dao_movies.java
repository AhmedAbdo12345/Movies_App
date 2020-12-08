package com.example.movies.Database;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface Dao_movies {


    // COMPLETED (2) Wrap the return type with LiveData
    @Query("SELECT * FROM Favourit_movies")
    LiveData<List<Favourit_movies>> loadAllTasks();

    @Insert
    void insertTask(Favourit_movies taskEntry);

    @Delete
    void deleteTask(Favourit_movies taskEntry);


    @Query("SELECT * FROM Favourit_movies")
    List<Favourit_movies> loadAllFavourite();
}
