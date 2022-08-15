package com.creeds_code.padi_20;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.creeds_code.padi_20.databinding.ActivityScheduleBinding;

public class ScheduleActivity extends AppCompatActivity {

    private ActivityScheduleBinding binding;
    private int notePosition;
    private int defaultValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityScheduleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        displayNote();
        onFabClicked();
    }

    private void displayNote(){
        defaultValue = -1;
        notePosition = getIntent().getIntExtra(Constants.NOTE_POSITION,defaultValue);
        if(notePosition != defaultValue){
            Schedule schedule = DataManager.schedules.get(notePosition);
            binding.scheduleTitle.setText(schedule.getTitle());
            binding.scheduleText.setText(schedule.getText());
        }

    }

   public String getSetTIme(){
        return "Time";
   }

   public String getSetTitle(){
        return binding.scheduleTitle.getText().toString();
   }

   public String getSetText(){
       return  binding.scheduleText.getText().toString();
   }


    private void onFabClicked() {
        binding.createNewScheduleFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(notePosition != defaultValue){
                    authenticateFabAction();
                }else{
                    createNewSchedule();
                }

            }
        });
    }

    private void updatePreviousSchedule(){
        //String time = binding.timePicker.getTime
        String title = getSetTitle();
        String text = getSetText();
        Schedule schedule = DataManager.schedules.get(notePosition);
        schedule.setTitle(title);
        schedule.setText(text);
        //set time
        Toast.makeText(ScheduleActivity.this,"Successfully Updated",Toast.LENGTH_LONG).show();
        startActivity(new Intent(ScheduleActivity.this,MainActivity.class));
    }

    private void createNewSchedule(){
        //String time = binding.timePicker.getTime
        String title = getSetTitle();
        String text = getSetText();
        DataManager.addNewSchedule(title,"Time",text);
        Toast.makeText(ScheduleActivity.this,"Successfully Created",Toast.LENGTH_LONG).show();
        startActivity(new Intent(ScheduleActivity.this,MainActivity.class));
    }

    private void authenticateFabAction(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ScheduleActivity.this);
        LayoutInflater inflater = ScheduleActivity.this.getLayoutInflater();
        builder.setTitle(R.string.new_schedule)
                .setMessage(R.string.new_schedule_dialog_message)
                .setIcon(R.drawable.ic_schedule)
                .setPositiveButton(R.string.action_create_text, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        createNewSchedule();
                    }
                })
                .setNegativeButton(R.string.update, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                            updatePreviousSchedule();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}