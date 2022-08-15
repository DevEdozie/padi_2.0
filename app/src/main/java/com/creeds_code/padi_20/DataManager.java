package com.creeds_code.padi_20;

import java.util.ArrayList;

class DataManager {
    static ArrayList<Schedule> schedules = new ArrayList<>();

    public static void addNewSchedule(String title,String time, String text){
        schedules.add(new Schedule(title,time,text));
    }
}
