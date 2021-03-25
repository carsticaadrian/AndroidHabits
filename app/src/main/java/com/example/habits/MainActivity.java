package com.example.habits;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addButton=findViewById(R.id.addButton);
        TextView txt_count=findViewById(R.id.txt_ct);
        RecyclerView recyclerView=findViewById(R.id.habits_rv);



        MainActivityViewModel viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);


        List<Habit> habits=new ArrayList<>();
        HabitAdapter habitAdapter=new HabitAdapter(habits);
        recyclerView.setAdapter(habitAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.ItemDecoration itemDecoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this,HabitSelectorActivity.class);
                startActivity(intent);


            }
        });
        if(getIntent().getExtras()!=null) {

            Intent intent = getIntent();
            String name = intent.getStringExtra("title");
            Habit habit = new Habit();
            habit.name = name;
            viewModel.addHabit(habit);
        }


       viewModel.getHabits().observe(this, new Observer<List<Habit>>() {
            @Override
            public void onChanged(List<Habit> habits) {
                habitAdapter.setData(habits);
                habitAdapter.notifyDataSetChanged();
            }
        });

        viewModel.getHabitsCount().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                txt_count.setText(String.valueOf(integer));
            }
        });



    }
}
