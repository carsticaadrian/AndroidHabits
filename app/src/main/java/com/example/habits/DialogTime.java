package com.example.habits;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.text.ParseException;

public class DialogTime extends AppCompatDialogFragment {
    private EditText editText_time;
    private DialogListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener=(DialogListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+"Must implement DialogListner");
        }

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.dialog,null);
        builder.setView(view)
                .setTitle("How long was your session?")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String time=editText_time.getText().toString();
                        listener.applyText(time);
                        try {
                            listener.addHabitTime(time);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                    }
                });
        editText_time=view.findViewById(R.id.edit_time);

                return builder.create();
    }
    public interface DialogListener{
        void applyText(String time);
        void addHabitTime(String time) throws ParseException;
    }
}
