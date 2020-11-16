package com.cg.hotelreservation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class HotelReservationTest {

    @Test
    public void whenHotelsWith_RegularRatesAdded_ShouldReturnTrue() {
        HotelReservation hotelReservation = new HotelReservation();
        assertTrue(hotelReservation.addHotel_withRegularRates("Lakewood", 110, 90));
        assertTrue(hotelReservation.addHotel_withRegularRates("Bridgewood", 160, 50));
        assertTrue(hotelReservation.addHotel_withRegularRates("Ridgewood", 220, 150));
    }

}
