package com.creeds_code.padi_20;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ScheduleRecyclerAdapter extends RecyclerView.Adapter<ScheduleRecyclerAdapter.ViewHolder>{

    private ArrayList<Schedule> schedules;
    private Context context;



    public ScheduleRecyclerAdapter( Context context, ArrayList<Schedule> schedules) {
        this.schedules = schedules;
        this.context = context;
    }

    @NonNull
    @Override
    public ScheduleRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_content,parent,false);
        return new ViewHolder(v,parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleRecyclerAdapter.ViewHolder holder, int position) {
        Schedule schedule = schedules.get(position);
        holder.title.setText(schedule.getTitle());
        holder.time.setText(schedule.getTime());
        //get position
        holder.notePosition = holder.getAdapterPosition();

    }

    @Override
    public int getItemCount() {
        return schedules.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView time;
        ImageView delete;
        int notePosition;

        public ViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            title = itemView.findViewById(R.id.text_title);
            time = itemView.findViewById(R.id.time_text);
            delete = itemView.findViewById(R.id.delete_icon);
            //set recyclerview listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,ScheduleActivity.class);
                    intent.putExtra(Constants.NOTE_POSITION,notePosition);
                    context.startActivity(intent);
                    Toast.makeText(context,"Opening note",Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
