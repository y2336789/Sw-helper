package com.bliss.csc.sw_helper;

public class Dateinfo {

    private String year;
    private String month;
    private String dayOfMonth;
    private String time;
    private String room_num;

    public Dateinfo(String year, String month, String dayOfMonth, String time, String room_num) {
        this.year = year;
        this.month = month;
        this.dayOfMonth = dayOfMonth;
        this.time = time;
        this.room_num = room_num;
    }

    public String getYear() {
        return this.year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return this.month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDayOfMonth() {
        return this.dayOfMonth;
    }

    public void setPhoneNumber(String dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRoom_num() {
        return this.room_num;
    }

    public void setRoom_num(String room_num) {
        this.room_num = room_num;
    }

}