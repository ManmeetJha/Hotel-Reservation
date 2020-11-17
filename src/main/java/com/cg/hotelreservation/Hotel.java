package com.cg.hotelreservation;

public class Hotel {
    public String hotelName;
    public int rating;
    public int regularWeekdayRate;
    public int regularWeekendRate;
    public int rewardWeekdayRate;
    public int rewardWeekendRate;
    public int bill;
    private int weekDayRate;
    private int weekEndRate;

    public Hotel(String hotelName) {
        this.hotelName = hotelName;
    }

    public Hotel(String hotelName, int bill) {
        this.hotelName = hotelName;
        this.bill = bill;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getHotelName() {
        return hotelName;
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

    public int getRating() {
        return rating;
    }

    public void setRateOnType(String type, Hotel hotel) {
        if (type.equals("regular")) {
            this.weekDayRate = hotel.regularWeekdayRate;
            this.weekEndRate = hotel.regularWeekendRate;
        } else if (type.equals("reward")) {
            this.weekDayRate = hotel.rewardWeekdayRate;
            this.weekEndRate = hotel.rewardWeekendRate;
        }
    }

    public int getWeekDayRateOnType(String type, Hotel hotel) {
        if (type.equals("regular"))
            weekDayRate = hotel.regularWeekdayRate;
        else if (type.equals("reward"))
            weekDayRate = hotel.rewardWeekdayRate;
        return weekDayRate;
    }

    public int getWeekEndRateOnType(String type, Hotel hotel) {
        if (type.equals("regular"))
            weekEndRate = hotel.regularWeekendRate;
        else if (type.equals("reward"))
            weekEndRate = hotel.rewardWeekendRate;
        return weekDayRate;
    }


}

