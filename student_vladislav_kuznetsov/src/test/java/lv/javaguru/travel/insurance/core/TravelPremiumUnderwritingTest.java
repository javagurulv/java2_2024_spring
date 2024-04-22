package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumUnderwritingImpl;
import lv.javaguru.travel.insurance.core.util.DateTimeService;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelPremiumUnderwritingTest {

    TravelPremiumUnderwritingImpl premiumUnderwriting = new TravelPremiumUnderwritingImpl();

    DateTimeService dateTimeService = new DateTimeService();

    @Test
    public void checkThatResponseHasCorrectAgreementPrice() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(createDate("25.03.2024"));
        when(request.getAgreementDateTo()).thenReturn(createDate("30.03.2024"));
        dateTimeService.calculateDaysBetweenDates(request.getAgreementDateFrom(), request.getAgreementDateTo());
        BigDecimal premium = premiumUnderwriting.calculatePremium(request);
        assertEquals(premium, new BigDecimal(5));

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
