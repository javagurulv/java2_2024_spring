package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelCalculatePremiumServiceImplTest {

    private TravelCalculatePremiumRequest travelCalculatePremiumRequestData;

    private TravelCalculatePremiumService service = new TravelCalculatePremiumServiceImpl();

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
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumServiceImpl().calculatePremium(travelCalculatePremiumRequestData);

        assertEquals(request.getPersonFirstName(), response.getPersonFirstName());
    }

    @Test
    void responseLastNameTest() {
        TravelCalculatePremiumRequest request = travelCalculatePremiumRequestData;
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumServiceImpl().calculatePremium(travelCalculatePremiumRequestData);

        assertEquals(request.getPersonLastName(), response.getPersonLastName());
    }

    @Test
    void responseDateFromTest() {
        TravelCalculatePremiumRequest request = travelCalculatePremiumRequestData;
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumServiceImpl().calculatePremium(travelCalculatePremiumRequestData);

        assertEquals(request.getAgreementDateFrom(), response.getAgreementDateFrom());
    }

    @Test
    void responseDateToTest() {
        TravelCalculatePremiumRequest request = travelCalculatePremiumRequestData;
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumServiceImpl().calculatePremium(travelCalculatePremiumRequestData);

        assertEquals(request.getAgreementDateTo(), response.getAgreementDateTo());
    }

    @Test
    void responseTest() {
        Calendar calendarFrom = new GregorianCalendar(2024, Calendar.MARCH , 8);
        Calendar calendarTo = new GregorianCalendar(2024, Calendar.MARCH , 18);
        Date dateFrom = calendarFrom.getTime();
        Date dateTo = calendarTo.getTime();

        TravelCalculatePremiumResponse expected = new TravelCalculatePremiumResponse("Vladislav", "Romanov", dateFrom, dateTo);
        TravelCalculatePremiumResponse actual = new TravelCalculatePremiumServiceImpl().calculatePremium(travelCalculatePremiumRequestData);

        assertEquals(expected, actual);
    }

}