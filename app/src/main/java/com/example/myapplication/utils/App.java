package com.example.myapplication.utils;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.example.myapplication.room.NoteDatabase;

public class App extends Application {
    public static Context context;
    public static NoteDatabase database = null;

    public static void getDataBase() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        initDatabase();
    }

    public static NoteDatabase initDatabase() {
        if (database == null) {
            database = Room.databaseBuilder(context, NoteDatabase.class, "database-note")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }
    public static NoteDatabase getDatabase(){
        return database;
    }
}
