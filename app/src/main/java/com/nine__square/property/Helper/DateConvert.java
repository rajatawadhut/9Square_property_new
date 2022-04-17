package com.nine__square.property.Helper;

import androidx.appcompat.app.AppCompatActivity;

public class DateConvert extends AppCompatActivity {

    String date, month;

    public DateConvert(String date) {
        this.date = date;
    }




    public String getdateconvert(){

        String[] parts = date.split("-");
        String yearch = parts[0];
        String monthch = parts[1];
        String datech = parts[2];

        if(monthch.equals("01")){
            month = "Jan";
        }else if(monthch.equals("02")){
            month = "Feb";
        }else if(monthch.equals("03")){
            month = "Mar";
        }else if(monthch.equals("04")){
            month = "Apr";
        }else if(monthch.equals("05")){
            month = "May";
        }else if(monthch.equals("06")){
            month = "Jun";
        }else if(monthch.equals("07")){
            month = "Jul";
        }else if(monthch.equals("08")){
            month = "Aug";
        }else if(monthch.equals("09")){
            month = "Sep";
        }else if(monthch.equals("10")){
            month = "Oct";
        }else if(monthch.equals("11")){
            month = "Nov";
        }else if(monthch.equals("12")){
            month = "Dec";
        }

        String ConvertedDate = month + " "+datech+","+" "+yearch;

        return ConvertedDate;
    }
}
