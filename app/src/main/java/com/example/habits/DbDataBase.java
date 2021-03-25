package com.example.habits;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Habit.class,version = 1)
public abstract class DbDataBase extends RoomDatabase {
    public abstract DbDAO dbDAO();

    private static DbDataBase dbDataBaseInstance;

    public static synchronized DbDataBase getInstance(Context context){
        if(dbDataBaseInstance==null){
            dbDataBaseInstance= Room.databaseBuilder(context.getApplicationContext(),DbDataBase.class,"HabitsDb").build();
        }
        return dbDataBaseInstance;

    }
}