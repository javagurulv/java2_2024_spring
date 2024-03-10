package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelCalculatePremiumServiceImplTest {

    private TravelCalculatePremiumRequest travelCalculatePremiumRequestData;

    private DateTimeService dateTimeService = new DateTimeService();

    private TravelCalculatePremiumService service = new TravelCalculatePremiumServiceImpl(dateTimeService);

    @BeforeEach
    void setUp() {
        Calendar calendarFrom = new GregorianCalendar(2024, Calendar.MARCH , 8);
        Calendar calendarTo = new GregorianCalendar(2024, Calendar.MARCH , 18);
        Date dateFrom = calendarFrom.getTime();
        Date dateTo = calendarTo.getTime();
        travelCalculatePremiumRequestData = new TravelCalculatePremiumRequest("Vladislav", "Romanov", dateFrom, dateTo);
    }

    @Test
    void responseFirstNameTest() {
        TravelCalculatePremiumRequest request = travelCalculatePremiumRequestData;
        TravelCalculatePremiumResponse response = service.calculatePremium(travelCalculatePremiumRequestData);

        assertEquals(request.getPersonFirstName(), response.getPersonFirstName());
    }

    @Test
    void responseLastNameTest() {
        TravelCalculatePremiumRequest request = travelCalculatePremiumRequestData;
        TravelCalculatePremiumResponse response = service.calculatePremium(travelCalculatePremiumRequestData);

        assertEquals(request.getPersonLastName(), response.getPersonLastName());
    }

    @Test
    void responseDateFromTest() {
        TravelCalculatePremiumRequest request = travelCalculatePremiumRequestData;
        TravelCalculatePremiumResponse response = service.calculatePremium(travelCalculatePremiumRequestData);

        assertEquals(request.getAgreementDateFrom(), response.getAgreementDateFrom());
    }

    @Test
    void responseDateToTest() {
        TravelCalculatePremiumRequest request = travelCalculatePremiumRequestData;
        TravelCalculatePremiumResponse response = service.calculatePremium(travelCalculatePremiumRequestData);

        assertEquals(request.getAgreementDateTo(), response.getAgreementDateTo());
    }

    @Test
    void responseAgreementPriceTest() {
        TravelCalculatePremiumRequest request = travelCalculatePremiumRequestData;
        TravelCalculatePremiumResponse response = service.calculatePremium(travelCalculatePremiumRequestData);

        assertEquals(new BigDecimal(10), response.getAgreementPrice());
    }

    @Test
    void responseTest() {
        Calendar calendarFrom = new GregorianCalendar(2024, Calendar.MARCH , 8);
        Calendar calendarTo = new GregorianCalendar(2024, Calendar.MARCH , 18);
        Date dateFrom = calendarFrom.getTime();
        Date dateTo = calendarTo.getTime();

        TravelCalculatePremiumResponse expected = new TravelCalculatePremiumResponse("Vladislav", "Romanov", dateFrom, dateTo, new BigDecimal(10));
        TravelCalculatePremiumResponse actual = service.calculatePremium(travelCalculatePremiumRequestData);

        assertEquals(expected, actual);
    }

}