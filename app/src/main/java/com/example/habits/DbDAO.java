package com.example.habits;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DbDAO {
    @Insert
    void insert(Habit...habits);

    @Query("SELECT * FROM Habit")
    LiveData<List<Habit>> getAllHabits();

    @Query("SELECT COUNT(*) FROM Habit")
    LiveData<Integer> getHabitsCount();

    @Delete
    void delete(Habit habit);
}