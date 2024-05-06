package lv.javaguru.travel.insurance.core.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeUtilTest {

    @Test
    public void shouldReturnCorrectDaysBetween() {
        DateTimeUtil dateTimeUtil = new DateTimeUtil();
        long daysBetween = dateTimeUtil.getDaysBetween(
                LocalDate.of(2021, 1, 1),
                LocalDate.of(2021, 1, 10)
        );
        assertEquals(9, daysBetween);
    }

    @Test
    public void shouldReturnCurrentDateTime() {
        DateTimeUtil dateTimeUtil = new DateTimeUtil();
        LocalDate currentDateTime = dateTimeUtil.getCurrentDateTime();
        assertEquals(LocalDate.now(), currentDateTime);
    }

}