package com.tejas.mytodos;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Todos.class}, exportSchema = false, version = 1)
public abstract class TodosDatabase extends RoomDatabase {
    private static final String DB_NAME = "todos_db";
    private static TodosDatabase instance;

    public static synchronized TodosDatabase getInstance(Context context){
        if (instance==null){
            instance = Room.databaseBuilder(context, TodosDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
    public abstract TodosDao todosDao();
}
