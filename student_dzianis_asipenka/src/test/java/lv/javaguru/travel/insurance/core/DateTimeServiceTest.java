package lv.javaguru.travel.insurance.core;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeServiceTest {
    DateTimeService dateTimeService = new DateTimeService();

    @ParameterizedTest
    @CsvSource({"01.01.2000, 10.01.2000, 9",
            "10.01.2023, 20.01.2023, 10", "10.01.2023, 30.01.2023, 20"})
    public void expectTrue(String date1, String date2, BigDecimal expected) {
        Date a = parseDate(date1);
        Date b = parseDate(date2);
        BigDecimal actualResult = dateTimeService.differenceBetweenDates(a, b);
        assertEquals(expected, actualResult);
    }

    private Date parseDate(String dateString) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateString);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format: " + dateString, e);
        }
    }
}