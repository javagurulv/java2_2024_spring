package lv.javaguru.travel.insurance.rest;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TravelCalculatePremiumResponseTest {

    @Test
    void calculateAgreementPrice() {
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();
        Date date1 = new Date(2000,1,1);
        Date date2 = new Date(2000,0,1);
        long expectedResult = 31L;
        assertEquals(expectedResult,response.calculateAgreementPrice(date1,date2));
    }
}