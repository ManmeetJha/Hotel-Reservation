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

    public static HashMap<String, Hotel> cheapestHotel(String Type, String InDate, String OutDate) {
        HashMap<String, Hotel> answer = new HashMap<>();
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
        System.out.println("Days: " + Arrays.toString(countDays));

        int minBill = Integer.MAX_VALUE;
        Hotel cheapestHotel = null;
        int minBill_WeekDay = Integer.MAX_VALUE;
        Hotel cheapestHotel_WeekDay = null;
        int minBill_WeekEnd = Integer.MAX_VALUE;
        Hotel cheapestHotel_WeekEnd = null;

        for (Hotel hotel : hotelList) {
            customer.setRateOnType(Type, hotel);
            int WeekDayBill = weekDaysNo * customer.weekDayRate;
            if (WeekDayBill < minBill_WeekDay) {
                minBill_WeekDay = WeekDayBill;
                answer.put("Weekday", new Hotel(hotel.getHotelName(), minBill_WeekDay));
            }

            int WeekEndBill = weekEndsNo * customer.weekEndRate;
            if (WeekEndBill < minBill_WeekEnd) {
                minBill_WeekEnd = WeekEndBill;
                answer.put("Weekend", new Hotel(hotel.getHotelName(), minBill_WeekEnd));
            }

            int combinedBill = weekDaysNo * customer.weekDayRate + weekEndsNo * customer.weekEndRate;
            if (combinedBill < minBill) {
                minBill = combinedBill;
                answer.put("Combined", new Hotel(hotel.getHotelName(), minBill));
            }

        }


//        customer.hotelName = cheapestHotel.hotelName;
//        customer.setBill(cheapestHotel.bill);
//        return customer;
        return answer;
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
        System.out.println("3. Add rating to hotels");
        int choice = input.nextInt();

        switch (choice) {
            case 1: {
                System.out.println("Enter Hotel Name:");
                String hotelName = input.next();

                //UC3-WeekDay-End Rates
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
                HashMap<String, Hotel> hotels = cheapestHotel(type, InDate, OutDate);
                //customer.showBill();
                System.out.println("Combined: " + hotels.get("Combined").getHotelName() + " at the cost of :" + hotels.get("Combined").bill);
                System.out.println("Weekday: " + hotels.get("Weekday").getHotelName() + " at the cost of :" + hotels.get("Weekday").bill);
                System.out.println("Weekend: " + hotels.get("Weekend").getHotelName() + " at the cost of :" + hotels.get("Weekend").bill);
                int totalBill = hotels.get("Weekday").bill + hotels.get("Weekend").bill;
                System.out.println("Total bill :" + totalBill);
            }

            case 3: {
                System.out.println("Enter Hotel Name:");
                String hotelName = input.next();
                System.out.println("Specify Rating of the hotel:");
                int rating = input.nextInt();
                hotelReservation.addRatingtoHotel(hotelName, rating);
            }
        }
    }

    public boolean addRatingtoHotel(String hotelName, int rating) {
        for (Hotel hotel : hotelList) {
            if (hotel.getHotelName().equals(hotelName)) {
                hotel.setRating(rating);

            }
        }
        return true;

    }
}



