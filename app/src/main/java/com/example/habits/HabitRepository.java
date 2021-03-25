package com.example.habits;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class HabitRepository {
    private Context context;
    public HabitRepository(Context context){
        this.context=context.getApplicationContext();
    }

    public void addNewHabit(Habit habit){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                DbDataBase.getInstance(context).dbDAO().insert(habit);
            }
        });

    }

    public LiveData<List<Habit>> getAllHabits(){
        return DbDataBase.getInstance(context).dbDAO().getAllHabits();
    }
    public LiveData<Integer> getHabitsCount(){
        return DbDataBase.getInstance(context).dbDAO().getHabitsCount();

    }
    public void deleteHabit(Habit habit){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                DbDataBase.getInstance(context).dbDAO().delete(habit);
            }
        });

    }
}
