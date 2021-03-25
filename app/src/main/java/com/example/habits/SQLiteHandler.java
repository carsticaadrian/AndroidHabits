package com.example.habits;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHandler extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME="CalendarHabit.db";
    private static final int DATABASE_VERSION=1;

    private static final String TABLE_NAME="TimeHabit";
    private static final String COLUMN_NAME="habit_name";
    private static final String COLUMN_DATE="habit_date";
    private static final String COLUMN_TIME="habit_time";




    public SQLiteHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query=
                "CREATE TABLE "  + TABLE_NAME +
                        " (" + COLUMN_NAME + " TEXT, " +
                        COLUMN_DATE + " TEXT, " +
                        COLUMN_TIME + " TEXT " + " )";
        db.execSQL(query);


    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);



    }

    void addHabitTime(String name,String date,String time){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_NAME,name);
        cv.put(COLUMN_DATE,date);
        cv.put(COLUMN_TIME,time);

        long result=db.insert(TABLE_NAME,null,cv);
        if(result==-1){
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
        }

    }

    String readDataBase(String name,String date){
        String[] projection={
                COLUMN_TIME
        };
        String selection = COLUMN_NAME + " = ? AND " + COLUMN_DATE + " = ?";
        String[] selectionArgs={name,date};
        try{
            SQLiteDatabase db=this.getReadableDatabase();
            Cursor cursor=db.query(
                    TABLE_NAME,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null

            )    ;
            cursor.moveToFirst();
            return cursor.getString(0);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    void updateHabitTime(String name,String date,String time){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_TIME,time);
        String selection = COLUMN_NAME + " LIKE ? AND " + COLUMN_DATE + " LIKE ?";
        String[] selectionArgs={name,date};

        int count=db.update(
                TABLE_NAME,
                cv,
                selection,
                selectionArgs
        );


    }

    void deleteHabitTime(String name,String date){
        SQLiteDatabase db=this.getWritableDatabase();
        String selection = COLUMN_NAME + " = ? AND " + COLUMN_DATE + " = ?";
        String[] selectionArgs={name,date};
        int deleteRows=db.delete(
                TABLE_NAME,
                selection,
                selectionArgs
        );
    }

    int getDateCount(String name){
        String[] projection={
                COLUMN_TIME
        };
        String selection = COLUMN_NAME + " = ? ";
        String[] selectionArgs={name};

            SQLiteDatabase db=this.getReadableDatabase();
            Cursor cursor=db.query(
                    TABLE_NAME,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null

            )    ;
            int count=cursor.getCount();
            cursor.close();
            return  count;

    }

    List<String> getDates(){
        ArrayList<String> dates=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor  cursor = db.rawQuery("select * from "+TABLE_NAME,null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_DATE));

                dates.add(name);
                cursor.moveToNext();
            }
        }
        return dates;

    }



    }

