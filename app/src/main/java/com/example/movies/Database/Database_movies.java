package com.example.movies.Database;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Favourit_movies.class}, version = 1, exportSchema = false)
public abstract class Database_movies extends RoomDatabase {
    private static final String LOG_TAG = Database_movies.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "MovieFav1";
    private static Database_movies sInstance;

    public static Database_movies getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                Log.d(LOG_TAG, "Creating new database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        Database_movies.class, Database_movies.DATABASE_NAME)
                        .build();
            }
        }
        Log.d(LOG_TAG, "Getting the database instance");
        return sInstance;
    }

    public abstract Dao_movies taskDao();


}
