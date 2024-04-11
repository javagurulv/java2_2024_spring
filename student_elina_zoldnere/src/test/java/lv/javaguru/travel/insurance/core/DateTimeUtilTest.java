package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeUtilTest {

    private DateTimeUtil timeUtil = new DateTimeUtil();

    @Test
    public void calculateDifferenceBetweenDays_ShouldCalculateCorrectResult() {
        Date sampleDateFrom = new Date(2025 - 1900, 2, 10); // March 10, 2025
        Date sampleDateTo = new Date(2025 - 1900, 2, 11); // March 11, 2025
        long difference = timeUtil.calculateDifferenceBetweenDays(sampleDateFrom, sampleDateTo);
        assertEquals(difference, 1);
    }

}
