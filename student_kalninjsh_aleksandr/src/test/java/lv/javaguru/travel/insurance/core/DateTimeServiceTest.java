package lv.javaguru.travel.insurance.core;


import org.junit.jupiter.api.Test;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeServiceTest {

    private DateTimeService service = new DateTimeService();

    @Test
    public void daysBetweenDates() {
        Date dateFrom = createDate("2005.05.15");
        Date dateTo = createDate("2005.05.20");
        long numberOfDays = service.calculateAgreementDaysBetweenDates(dateFrom, dateTo);
        assertEquals(numberOfDays, 5);
    }

    private Date createDate(String dateString) {
        try {
            return new SimpleDateFormat("yyyy.MM.dd").parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}