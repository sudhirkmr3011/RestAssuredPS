package com.hsbc.qe.ui.utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtilities {

    public static String getDate(int noOfDays) {
        String selectedDate;
        SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, noOfDays);
        date = cal.getTime();
        selectedDate = dmyFormat.format(date);
        return selectedDate;
    }


    public static String getTodayDate() {
        return getDate(0);
    }
}
