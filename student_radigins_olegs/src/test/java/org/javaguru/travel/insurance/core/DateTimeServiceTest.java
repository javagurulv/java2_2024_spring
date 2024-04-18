package org.javaguru.travel.insurance.core;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeServiceTest {
    DateTimeService dateTimeService = new DateTimeService();


    @Test
    public void shouldTestZeroDays(){
        Date date1 = new Date(2000,0,1);
        Date date2 = new Date(2000,0,1);
        long expectedResult = 0L;
        long actualResult = dateTimeService.daysBetween(date1,date2);
        assertEquals(expectedResult,actualResult);
    }

    @Test
    public void shouldTestPositiveDays(){
        Date date1 = new Date(2000,0,1);
        Date date2 = new Date(2000,1,1);
        long expectedResult = 31L;
        long actualResult = dateTimeService.daysBetween(date1,date2);
        assertEquals(expectedResult,actualResult);
    }

    @Test
    public void shouldTestNegativeDays(){
        Date date1 = new Date(2000,1,1);
        Date date2 = new Date(2000,0,1);
        long expectedResult = -31L;
        long actualResult = dateTimeService.daysBetween(date1,date2);
        assertEquals(expectedResult,actualResult);
    }

}