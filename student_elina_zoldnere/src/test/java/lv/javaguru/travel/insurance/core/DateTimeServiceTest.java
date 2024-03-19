package lv.javaguru.travel.insurance.core;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeServiceTest {

    private DateTimeService service = new DateTimeService();

    @Test
    public void calculateDifferenceBetweenDays_ShouldCalculateCorrectResult() {
        Date sampleDateFrom = new Date(2024 - 1900, 2, 10); // March 10, 2024
        Date sampleDateTo = new Date(2024 - 1900, 2, 11); // March 11, 2024
        long difference = service.calculateDifferenceBetweenDays(sampleDateFrom, sampleDateTo);
        assertEquals(difference, 1);
    }

}