package com.cg.hotelreservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class HotelReservation {

    static Scanner input = new Scanner(System.in);

    //list to store the hotels
    public static ArrayList<Hotel> hotelList = new ArrayList<>();

    public boolean addHotel_withRegularRates(String hotelName, int regularWeekdayRate, int regularWeekendRate) {
        Hotel hotel = new Hotel(hotelName);
        hotel.setRegularRates(regularWeekdayRate, regularWeekendRate);
        hotelList.add(hotel);
        return true;
    }

    public void display() {
        for (Hotel hotel : hotelList)
            hotel.display();
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the HOTEL RESERVATION program");

        //Given entries to make
        HotelReservation hotelReservation = new HotelReservation();
        hotelReservation.addHotel_withRegularRates("Lakewood", 110, 90);
        hotelReservation.addHotel_withRegularRates("Bridgewood", 160, 60);
        hotelReservation.addHotel_withRegularRates("Ridgewood", 220, 150);
        hotelReservation.display();

        System.out.println("Enter your choice:");
        System.out.println("1. Add new Hotel");
        int choice = input.nextInt();

        switch (choice) {
            case 1: {
                System.out.println("Enter Hotel Name:");
                String hotelName = input.next();
                System.out.println("Enter regular weekday rate:");
                int regularWeekdayRate = input.nextInt();
                System.out.println("Enter regular weekend rate:");
                int regularWeekendRate = input.nextInt();
                hotelReservation.addHotel_withRegularRates(hotelName, regularWeekdayRate, regularWeekendRate);
                System.out.println("Hotel Successfully Added!!!");
                break;
            }

        }
    }


}
