package com.cg.hotelreservation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

public class HotelReservationTest {
    private Customer customer;

    @Test
    public void whenHotelsWith_RegularRatesAdded_ShouldReturnTrue() {
        HotelReservation hotelReservation = new HotelReservation();
        assertTrue(hotelReservation.addHotel_withRegularRates("Lakewood", 110, 90));
        assertTrue(hotelReservation.addHotel_withRegularRates("Bridgewood", 160, 50));
        assertTrue(hotelReservation.addHotel_withRegularRates("Ridgewood", 220, 150));
    }

    @Test
    public void whenRangeofDatesGiven_ShouldReturn_CheapestHotel() {
        HotelReservation hotelReservation = new HotelReservation();
        hotelReservation.addHotel_withRegularRates("Lakewood", 110, 90);
        hotelReservation.addHotel_withRegularRates("Bridgewood", 160, 50);
        hotelReservation.addHotel_withRegularRates("Ridgewood", 220, 150);
        HashMap<String, Hotel> hotels=HotelReservation.cheapestHotel("regular","10/09/2020","11/09/2020");
        Hotel combinedHotel = hotels.get("Combined");
        assertEquals(220, combinedHotel.bill);
        assertEquals("Lakewood", combinedHotel.getHotelName());

    }

    @Test
    public void whenRangeofDatesGiven_ShouldReturn_CheapestHotel_basedOnWeekEnds_Days() {
        HotelReservation hotelReservation = new HotelReservation();
        hotelReservation.addHotel_withRegularRates("Lakewood", 110, 90);
        hotelReservation.addHotel_withRegularRates("Bridgewood", 160, 50);
        hotelReservation.addHotel_withRegularRates("Ridgewood", 220, 150);
        HashMap<String, Hotel> hotels=HotelReservation.cheapestHotel("regular","01/01/2020","08/01/2020");
        Hotel combinedHotel = hotels.get("Combined");
        assertEquals(840, combinedHotel.bill);
        assertEquals("Lakewood", combinedHotel.getHotelName());

        Hotel combinedHotelDay = hotels.get("Weekday");
        assertEquals(660, combinedHotelDay.bill);
        assertEquals("Lakewood", combinedHotelDay.getHotelName());

        Hotel combinedHotelEnds = hotels.get("Weekend");
        assertEquals(100, combinedHotelEnds.bill);
        assertEquals("Bridgewood", combinedHotelEnds.getHotelName());

    }

    @Test
    public void  whenRatingAdded_ShouldReturnTrue(){
        HotelReservation hotelReservation = new HotelReservation();
        hotelReservation.addHotel_withRegularRates("Lakewood", 110, 90);
        assertTrue(hotelReservation.addRatingtoHotel("Lakewood",3));
    }

    @Test
    public void cheapestBestRatedHotel_shouldMatchExpected() {
        HotelReservation hotelReservation = new HotelReservation();
        hotelReservation.addHotel_withRegularRates("Lakewood", 110, 90);
        hotelReservation.addHotel_withRegularRates("Bridgewood", 160, 50);
        hotelReservation.addHotel_withRegularRates("Ridgewood", 220, 150);
        hotelReservation.addRatingtoHotel("Lakewood",3);
        hotelReservation.addRatingtoHotel("Bridgewood",5);
        hotelReservation.addRatingtoHotel("Ridgewood",5);
        Hotel cheapestBestRatedHotel= HotelReservation.cheapestBestRatedHotel();
        Assert.assertEquals("Bridgewood",cheapestBestRatedHotel.getHotelName());

    }

    @Test
    public void whenRewardRatesAdded_ShouldReturnTure() {
        HotelReservation hotelReservation = new HotelReservation();
        hotelReservation.addHotel_withRegularRates("Lakewood", 110, 90);
        assertTrue(hotelReservation.addRewardRate("Lakewood",80,80));
    }
}
