package com.creeds_code.padi_20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.creeds_code.padi_20.databinding.ActivityScheduleBinding;

public class ScheduleActivity extends AppCompatActivity {

    private ActivityScheduleBinding binding;
    private int notePosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityScheduleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        displayNote();
        onFabClicked();
    }

    private void displayNote(){
        int defaultValue = -1;
        notePosition = getIntent().getIntExtra(Constants.NOTE_POSITION,defaultValue);
        if(notePosition != defaultValue){
            Schedule schedule = DataManager.schedules.get(notePosition);
            binding.scheduleTitle.setText(schedule.getTitle());
            binding.scheduleText.setText(schedule.getText());
        }

    }

    private void onFabClicked() {
        binding.createNewScheduleFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String time = binding.timePicker.getTime
                String title = binding.scheduleTitle.getText().toString();
                String text = binding.scheduleText.getText().toString();
                DataManager.schedules.add(new Schedule(title,"Time",text));
                Toast.makeText(ScheduleActivity.this,"Successfully Created",Toast.LENGTH_LONG).show();
                startActivity(new Intent(ScheduleActivity.this,MainActivity.class));
            }
        });
    }
}