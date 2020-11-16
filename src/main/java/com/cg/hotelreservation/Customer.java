package com.cg.hotelreservation;

public class Customer extends Hotel {

    public String Type;
    public int bill;
    public int weekDayRate;
    public int weekEndRate;

    public Customer(String hotelName, int bill) {
        super(hotelName);
        this.bill = bill;
    }

    public void showBill() {
        System.out.println("Hotel Found: " + hotelName);
        System.out.println("Total Bill: " + bill);
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


    public int getBill() {
        return bill;
    }

    public void setBill(int bill) {
        this.bill = bill;
    }
}
