package lv.javaguru.travel.insurance.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateTimeServiceTest {

    DateTimeService dateTimeService;
    Date dateFrom, dateTo;
    @BeforeEach
    void setUp() {
        Calendar calendarFrom = new GregorianCalendar(2024, Calendar.MARCH , 8);
        Calendar calendarTo = new GregorianCalendar(2024, Calendar.MARCH , 18);
        dateFrom = calendarFrom.getTime();
        dateTo = calendarTo.getTime();
        dateTimeService = new DateTimeService();
    }

    @Test
    void calculateTravelPeriodTest() {
        int expected = 10;
        int actual = dateTimeService.calculateTravelPeriod(dateFrom, dateTo);

        assertEquals(expected, actual);
    }

    @Test
    void calculateTravelPeriodNegativeTest() {
        int expected = -10;
        int actual = dateTimeService.calculateTravelPeriod(dateTo, dateFrom);

        assertEquals(expected, actual);
    }

    @Test
    void calculateTravelPeriodEqualTest() {
        int expected = 0;
        int actual = dateTimeService.calculateTravelPeriod(dateFrom, dateFrom);

        assertEquals(expected, actual);
    }

    @Test
    void convertToLocalDateViaInstantTest() {
        LocalDate expected = LocalDate.of(2024, 3, 8);
        LocalDate actual = dateTimeService.convertToLocalDateViaInstant(dateFrom);

        assertEquals(expected, actual);
    }
}
