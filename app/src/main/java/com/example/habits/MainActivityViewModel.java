package com.example.habits;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    private HabitRepository habitRepository;
    private LiveData<List<Habit>> habits;
    private LiveData<Integer> habitsCount;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        habitRepository=new HabitRepository(application.getApplicationContext());
        habits=habitRepository.getAllHabits();
        habitsCount=habitRepository.getHabitsCount();
    }

    public void addHabit(Habit habit){
        habitRepository.addNewHabit(habit);
    }

    public void deleteHabit(Habit habit){
        habitRepository.deleteHabit(habit);
    }
    public LiveData<List<Habit>> getHabits(){
        return habits;
    }
    public LiveData<Integer> getHabitsCount(){
        return habitsCount;
    }



}
