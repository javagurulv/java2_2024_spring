package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeServiceTest {

    private DateTimeService service = new DateTimeService();

    @Test
    public void daysBetweenDates() {
        Date dateFrom = new Date(2005, 05, 15);
        Date dateTo = new Date(2005, 05, 20);
        long numberOfDays = service.calculateAgreementDaysBetweenDates(dateFrom, dateTo);
        assertEquals(numberOfDays, 5);


    }

}