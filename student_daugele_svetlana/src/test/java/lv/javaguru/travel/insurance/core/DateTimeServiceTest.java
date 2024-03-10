package lv.javaguru.travel.insurance.core;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeServiceTest {

    @Test
    public void testGetDaysBetweenTwoDates() {
        DateTimeService dateTimeService = new DateTimeService();

        // Test case 1: Dates in ascending order
        Date date1 = new Date(2024, 2, 1); // February 1, 2024
        Date date2 = new Date(2024, 2, 10); // February 10, 2024
        long expectedDays1 = 9; // Difference is 9 days
        long actualDays1 = dateTimeService.getDaysBetweenTwoDates(date1, date2);
        assertEquals(expectedDays1, actualDays1, "Test case 1 failed");

        // Test case 2: Dates in descending order
        Date date3 = new Date(2024, 2, 5); // February 5, 2024
        Date date4 = new Date(2024, 2, 15); // February 15, 2024
        long expectedDays2 = 10; // Difference is 10 days
        long actualDays2 = dateTimeService.getDaysBetweenTwoDates(date3, date4);
        assertEquals(expectedDays2, actualDays2, "Test case 2 failed");

        // Test case 3: Dates in the same month
        Date date5 = new Date(2024, 2, 20); // February 20, 2024
        Date date6 = new Date(2024, 2, 25); // February 25, 2024
        long expectedDays3 = 5; // Difference is 5 days
        long actualDays3 = dateTimeService.getDaysBetweenTwoDates(date5, date6);
        assertEquals(expectedDays3, actualDays3, "Test case 3 failed");

        // Test case 4: Dates across months
        Date date7 = new Date(2024, 1, 20); // February 20, 2024 (Month index is zero-based)
        Date date8 = new Date(2024, 2, 5); // March 5, 2024
        long expectedDays4 = 14; // Difference is 14 days
        long actualDays4 = dateTimeService.getDaysBetweenTwoDates(date7, date8);
        assertEquals(expectedDays4, actualDays4, "Test case 4 failed");
    }
}