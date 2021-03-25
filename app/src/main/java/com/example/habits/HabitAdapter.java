package com.example.habits;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HabitAdapter extends RecyclerView.Adapter<HabitAdapter.ViewHolder> {
    private List<Habit> habits;

    public HabitAdapter(List<Habit> habits){
        this.habits=habits;
    }

    public void setData(List<Habit> habits){
        this.habits=habits;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.habit_row,parent,false);

        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Habit habit=habits.get(position);
        holder.txtHabitName.setText(habit.name);
        String habitName=habit.name;
        if("Meditation".equalsIgnoreCase(habitName)){
            holder.imgLogo.setImageResource(R.drawable.meditation);
        }
        if("Running".equalsIgnoreCase(habitName)){
            holder.imgLogo.setImageResource(R.drawable.running);
        }
        if("Reading".equalsIgnoreCase(habitName)){
            holder.imgLogo.setImageResource(R.drawable.reading);
        }
        if("Workout".equalsIgnoreCase(habitName)){
            holder.imgLogo.setImageResource(R.drawable.workout);
        }
        if("Yoga".equalsIgnoreCase(habitName)){
            holder.imgLogo.setImageResource(R.drawable.yoga);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),CalendarActivity.class);
                intent.putExtra("habitName",habitName);

                v.getContext().startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return habits.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txtHabitName;
        ImageView imgDelete;
        ImageView imgLogo;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.txtHabitName=itemView.findViewById(R.id.txt_habit_view);
            this.imgDelete=itemView.findViewById(R.id.img_delete);
            this.imgLogo = itemView.findViewById(R.id.habitLogo);
            this.imgDelete.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View v) {
            int position=getAdapterPosition();
            Habit habit=habits.get(position);
            HabitRepository habitRepository=new HabitRepository(v.getContext());
            habitRepository.deleteHabit(habit);
        }
    }

}
