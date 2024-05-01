package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelPremiumUnderwritingTest {

    @Mock
    private DateTimeService service;

    @Mock
    private TravelCalculatePremiumRequest request;

    @InjectMocks
    private TravelPremiumUnderwriting premiumUnderwriting;

    @Test
    public void returnResponseWithCorrectAgreementPrice() {

        when(request.getAgreementDateFrom()).thenReturn(createDate("2005.05.15"));
        when(request.getAgreementDateTo()).thenReturn(createDate("2005.05.20"));
        when(service.calculateAgreementDaysBetweenDates(request.getAgreementDateFrom(), request.getAgreementDateTo())).thenReturn(5L);
        BigDecimal premium = premiumUnderwriting.calculatePremium(request);
        assertEquals(premium, new BigDecimal(5));

    }

    private Date createDate(String dateString) {
        try {
            return new SimpleDateFormat("yyyy.MM.dd").parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}