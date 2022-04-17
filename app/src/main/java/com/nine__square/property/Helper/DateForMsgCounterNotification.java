package com.nine__square.property.Helper;

public class DateForMsgCounterNotification {

    String date, datechp;

    public DateForMsgCounterNotification(String date) {
        this.date = date;
    }


    public String getdateforcounter() {

        String[] parts = date.split("-");
        String yearch = parts[0];
        String monthch = parts[1];
        String datech = parts[2];

        if (datech.equals("01")) {
            datechp = "30";
        } else {
            int chdate;
            chdate = Integer.parseInt(datech);
            datechp = String.valueOf(chdate);
        }

        String ConvertedDate = yearch + "-" + monthch + "-" + datechp;

        return ConvertedDate;

    }
}
