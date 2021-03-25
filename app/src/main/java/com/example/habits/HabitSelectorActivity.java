package com.example.habits;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class HabitSelectorActivity extends AppCompatActivity {
    ListView listView;
    String habitsTitle[]={"Workout","Meditation","Running","Reading","Yoga"};
    int habitsImages[]={R.drawable.workout,R.drawable.meditation,R.drawable.running,R.drawable.reading,R.drawable.yoga};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_selector);
        listView=findViewById(R.id.list_buttons);

        MyAdapter adapter=new MyAdapter(this,habitsTitle,habitsImages);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putInt("image",habitsImages[0]);
                    intent.putExtras(bundle);
                    intent.putExtra("title", habitsTitle[0]);
                    intent.putExtra("position",""+0);
                    startActivity(intent);
                }
                if(position==1){
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putInt("image",habitsImages[1]);
                    intent.putExtras(bundle);
                    intent.putExtra("title", habitsTitle[1]);
                    intent.putExtra("position",""+1);
                    startActivity(intent);
                }
                if(position==2){
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putInt("image",habitsImages[2]);
                    intent.putExtras(bundle);
                    intent.putExtra("title", habitsTitle[2]);
                    intent.putExtra("position",""+2);
                    startActivity(intent);
                }
                if(position==3){
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putInt("image",habitsImages[3]);
                    intent.putExtras(bundle);
                    intent.putExtra("title", habitsTitle[3]);
                    intent.putExtra("position",""+3);
                    startActivity(intent);
                }
                if(position==4){
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putInt("image",habitsImages[4]);
                    intent.putExtras(bundle);
                    intent.putExtra("title", habitsTitle[4]);
                    intent.putExtra("position",""+4);
                    startActivity(intent);
                }
            }
        });

    }

    class MyAdapter extends ArrayAdapter<String>{
        Context context;
        String hTitle[];
        int hImages[];

        MyAdapter(Context c,String title[],int imgs[]){
            super(c,R.layout.row,R.id.habitName,title);
            this.context=c;
            this.hTitle=title;
            this.hImages=imgs;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater=(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row=layoutInflater.inflate(R.layout.row,parent,false);
            ImageView images=row.findViewById(R.id.imageHabit);
            TextView titles=row.findViewById(R.id.habitName);

            titles.setText(hTitle[position]);
            images.setImageResource(hImages[position]);



            return row;
        }
    }
}
