package com.cg.hotelreservation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

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
        customer=HotelReservation.cheapestHotel("regular","10/9/2020","11/9/2020");
        assertEquals(220, customer.getBill());
        assertEquals("Lakewood", customer.getHotelName());

    }
}
