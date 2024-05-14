package lv.javaguru.travel.insurance.core.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateTimeUtilTest {

    DateTimeUtil dateTimeService;

    LocalDate dateFrom, dateTo;

    @BeforeEach
    void setUp() {
        dateFrom = LocalDate.of(2030, 3, 8);
        dateTo = LocalDate.of(2030, 3, 18);
        dateTimeService = new DateTimeUtil();
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
}
