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

    public static Customer cheapestHotel(String Type, String InDate, String OutDate) {
        Customer customer = new Customer("", 0);

        //Conversion of string to date
        Date dateArr[] = stringToDate(InDate, OutDate);
        Date inDate = dateArr[0];
        Date outDate = dateArr[1];
        System.out.println(inDate + " " + outDate);

        //counting number of weekdays and weekends
        int[] countDays = countWeekDaysEnds(inDate, outDate);
        int weekDaysNo = countDays[0];
        int weekEndsNo = countDays[1];

        int minBill = Integer.MAX_VALUE;
        Hotel cheapestHotel = null;

        for (Hotel hotel : hotelList) {
            customer.setRateOnType(Type, hotel);
            int Bill = weekDaysNo * customer.weekDayRate + weekEndsNo * customer.weekEndRate;
            hotel.bill = Bill;
            if (Bill < minBill) {
                minBill = Bill;
                cheapestHotel = hotel;
            }
        }
        customer.hotelName = cheapestHotel.hotelName;
        customer.setBill(cheapestHotel.bill);
        return customer;
    }

    //String to Date Conversion
    public static Date[] stringToDate(String InDate, String OutDate) {
        try {
            Date dateArr[] = new Date[2];
            dateArr[0] = new SimpleDateFormat("dd/MM/yyyy").parse(InDate);
            dateArr[1] = new SimpleDateFormat("dd/MM/yyyy").parse(OutDate);
            return dateArr;
        } catch (ParseException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    private static int[] countWeekDaysEnds(Date InDate, Date OutDate) {
        int Count[] = {0, 0};
        Calendar start = Calendar.getInstance();
        start.setTime(InDate);
        Calendar end = Calendar.getInstance();
        end.setTime(OutDate);

        if (start.getTimeInMillis() > end.getTimeInMillis()) {
            System.out.println("Incorrect format-- Inter changing start and end date.");
            start.setTime(OutDate);
            end.setTime(InDate);
        }
        do {
            if (start.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && start.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                Count[0]++;
            } else Count[1]++;
            start.add(Calendar.DAY_OF_MONTH, 1);
        } while (start.getTimeInMillis() <= end.getTimeInMillis());
        return Count;
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
        System.out.println("1. Add new Hotel with Regular Rates");
        System.out.println("2. Find Cheapest Hotel in given Date Range");
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
            case 2: {
                System.out.println("Enter customer type");
                String type = input.next().toLowerCase();
                System.out.println("Enter Check-in and Check-out dates to find hotel (dd/mm/yyyy)");
                System.out.println("Check-In date: ");
                String InDate = input.next();
                System.out.println("Check-Out date: ");
                String OutDate = input.next();
                Customer customer = cheapestHotel(type, InDate, OutDate);
                customer.showBill();
            }

        }
    }


}
