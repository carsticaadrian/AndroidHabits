package com.example.habits;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class CalendarActivity extends AppCompatActivity implements DialogTime.DialogListener {

    private CalendarView mCalendarView;
    private TextView habitName,timeAmount,youHave,habitCount;
    private Button addEntry;
    private SQLiteHandler dbHandler;
    private String selectedDate;
    private SQLiteDatabase sqLiteDatabase;
    private ImageView deleteTime;
    private List<Calendar> calendars = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        mCalendarView= findViewById(R.id.calendarView);
        habitName=findViewById(R.id.habitNameCalendar);
        addEntry=findViewById(R.id.btn_addEntry);
        timeAmount=findViewById(R.id.txt_timeEntry);
        deleteTime=findViewById(R.id.delete_time);
        youHave=findViewById(R.id.txt_youHave);
        habitCount=findViewById(R.id.txt_habitCount);

        youHave.setText("You have been practicing ");

        Intent intent=getIntent();
        habitName.setText(intent.getStringExtra("habitName"));


        try {
            dbHandler = new SQLiteHandler(this);
        }catch (Exception e){
            e.printStackTrace();
        }

        mCalendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                Calendar clickedDayCalendar = eventDay.getCalendar();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                try {
                    Date date=clickedDayCalendar.getTime();
                    selectedDate = dateFormat.format(date);
                }catch (Exception e){
                    e.printStackTrace();
                }

                if(checkDataBaseEmpty()){
                    addEntry.setText("Add entry");
                    deleteTime.setVisibility(View.INVISIBLE);

                }else{
                    addEntry.setText("Update");
                    deleteTime.setVisibility(View.VISIBLE);
                }

                applyText(dbHandler.readDataBase(habitName.getText().toString().trim(),selectedDate));
                habitCount.setText(String.valueOf(dbHandler.getDateCount(habitName.getText().toString().trim()))+" times");
                highlightDates();

                mCalendarView.setHighlightedDays(calendars);
            }
        });

        this.deleteTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHandler.deleteHabitTime(habitName.getText().toString().trim(),selectedDate);
            }
        });

        addEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogTime dialog=new DialogTime();
                dialog.show(getSupportFragmentManager(),"Time");
            }
        });

    }

    @Override
    public void addHabitTime(String time) throws ParseException {
        if(checkDataBaseEmpty()) {
            dbHandler.addHabitTime(habitName.getText().toString().trim(), selectedDate, time);
        }else
            dbHandler.updateHabitTime(habitName.getText().toString().trim(),selectedDate,time);

    }

 @Override
    public void applyText(String time) {
        if(time!=null)
            timeAmount.setText(time+" minutes");
        else{
            timeAmount.setText("0 minutes");
        }

    }

    public boolean checkDataBaseEmpty(){
        if (dbHandler.readDataBase(habitName.getText().toString().trim(),selectedDate)==null)
            return true;
        else
            return false;
    }

    public void highlightDates(){
        List<String> dates=dbHandler.getDates();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        for(String date : dates){
            if(date!=null) {
                try {
                    Date helper = format.parse(date);
                    calendars.add(toCalendar(helper));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

        }


    }
    public static Calendar toCalendar(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }



}


