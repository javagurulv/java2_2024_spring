package lv.javaguru.travel.insurance.core.util;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

class DateTimeUtilTest {

    private final DateTimeUtil timeUtil = new DateTimeUtil();

    @Test
    void calculateDifferenceBetweenDays_ShouldCalculateCorrectResult() {
        Date sampleDateFrom = new Date(2025 - 1900, 2, 10); // March 10, 2025
        Date sampleDateTo = new Date(2025 - 1900, 2, 11); // March 11, 2025

        long difference = timeUtil.calculateDifferenceBetweenDatesInDays(sampleDateFrom, sampleDateTo);

        assertThat(difference).isEqualTo(1);
    }

    @Test
    void subtractYears_ShouldCalculateCorrectResult() {
        Date sampleDate = new Date(2025 - 1900, 2, 10); // March 10, 2025

        Date result = timeUtil.subtractYears(sampleDate, 150);

        assertThat(result.getYear()).isEqualTo(1875 - 1900);
    }

}
