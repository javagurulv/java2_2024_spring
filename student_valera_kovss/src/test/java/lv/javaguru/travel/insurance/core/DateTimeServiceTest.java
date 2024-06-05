
package lv.javaguru.travel.insurance.core;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
public class DateTimeServiceTest {


    private DateTimeService dateTimeService = new DateTimeService();

    @Test
    public void shouldDaysBetweenBeZero() {
        Date date1 = createDate("04.01.2024");
        Date date2 = createDate("04.01.2024");
        var daysBetween = dateTimeService.getDaysBetween(date1, date2);
        assertEquals(daysBetween, 0L);
    }

    @Test
    public void shouldDaysBetweenBePositive() {
        Date date1 = createDate("04.01.2024");
        Date date2 = createDate("05.01.2024");
        var daysBetween = dateTimeService.getDaysBetween(date1, date2);
        assertEquals(daysBetween, 1L);
    }

    @Test
    public void shouldDaysBetweenBeNegative() {
        Date date1 = createDate("05.01.2024");
        Date date2 = createDate("04.01.2024");
        var daysBetween = dateTimeService.getDaysBetween(date1, date2);
        assertEquals(daysBetween, -1L);
    }

    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
