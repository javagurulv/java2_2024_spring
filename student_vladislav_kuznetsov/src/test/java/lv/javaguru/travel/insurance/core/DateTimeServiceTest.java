package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.web.format.DateTimeFormatters;

import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeServiceTest {
    DateTimeService dateTimeService = new DateTimeService();

    @Test
    public void checkCalculatedDays(){
        long daysBetween = dateTimeService.calculateDaysBetweenDates(createDate("11.03.2023"),createDate("20.03.2023"));
        assertEquals(daysBetween,9L);
    }
    @Test
    public void checkCalculatedDaysAreNegative(){
        long daysBetween = dateTimeService.calculateDaysBetweenDates(createDate("20.03.2023"),createDate("11.03.2023"));
        assertEquals(daysBetween,-9L);
    }
@Test
    public void checkCalculatedDaysAreNull(){
        long daysBetween = dateTimeService.calculateDaysBetweenDates(createDate("20.03.2023"),createDate("20.03.2023"));
        assertEquals(daysBetween,0L);
    }


        public Date createDate(String dateString) {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
            try {
                dateFormatter.setLenient(false);
                return dateFormatter.parse(dateString);
            } catch (ParseException e) {
                System.out.println("Error parsing date string: " + e.getMessage());
                return null;
            }
        }
}
