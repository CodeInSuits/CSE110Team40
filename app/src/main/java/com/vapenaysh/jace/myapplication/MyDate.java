package com.vapenaysh.jace.myapplication;

import java.util.Date;

/**
 * Created by Matt on 5/30/16.
 */
public class MyDate {
    private int year, month, day, hrs, min, sec;


    public MyDate(){}

    public MyDate(Date date){
        this.year = date.getYear();
        this.month =date.getMonth();
        this.day = date.getDate();
        this.hrs = date.getHours();
        this.min = date.getMinutes();
        this.sec = date.getSeconds();
    }

    public Date getDate(){
        return new Date(year,month,day,hrs,min,sec);
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int date) {
        this.day = date;
    }

    public int getHrs() {
        return hrs;
    }

    public void setHrs(int hrs) {
        this.hrs = hrs;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getSec() {
        return sec;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }

    public int getYear() {

        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
