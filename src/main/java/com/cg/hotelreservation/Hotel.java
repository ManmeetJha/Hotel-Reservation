package com.cg.hotelreservation;

public class Hotel {
    public String hotelName;
    public int regularWeekdayRate;
    public int regularWeekendRate;

    public Hotel(String hotelName) {
        this.hotelName = hotelName;
    }

    public void setRegularRates(int regularWeekdayRate, int regularWeekendRate) {
        this.regularWeekdayRate = regularWeekdayRate;
        this.regularWeekendRate = regularWeekendRate;
    }

    public void display() {
        System.out.println("Hotel Name: " + hotelName);
        System.out.println("Regular Weekday Rate: " + regularWeekdayRate);
        System.out.println("Regular Weekend Rate: " + regularWeekendRate);
        System.out.println();
    }

}

