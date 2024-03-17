package lv.javaguru.travel.insurance.core;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeServiceTest {
    DateTimeService dateTimeService = new DateTimeService();

    @Test
    public void checkCalculatedDays(){
        long daysBetween = dateTimeService.calculateDaysBetweenDates(createDate("11.03.2023"),createDate("20.03.2023"));
        assertEquals(9L, daysBetween);
    }
    @Test
    public void checkCalculatedDaysAreNegative(){
        long daysBetween = dateTimeService.calculateDaysBetweenDates(createDate("20.03.2023"),createDate("11.03.2023"));
        assertEquals(-9L, daysBetween);
    }
    @Test
    public void checkCalculatedDaysAreNull(){
        long daysBetween = dateTimeService.calculateDaysBetweenDates(createDate("20.03.2023"),createDate("20.03.2023"));
        assertEquals(0L, daysBetween);
    }


        public Date createDate(String dateString) {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
            try {
                dateFormatter.setLenient(true);
                return dateFormatter.parse(dateString);
            } catch (ParseException e) {
                System.out.println("Error parsing date string: " + e.getMessage());
                return null;
            }
        }
}
