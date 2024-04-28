package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelPremiumUnderWritingTest {
    @Mock
    DateTimeService dateTimeService;
    @InjectMocks
    private TravelPremiumUnderWriting premiumUnderwriting;

    @Test
    void calculatePremium() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(new Date(2000,0,1));
        when(request.getAgreementDateTo()).thenReturn(new Date(2000,1,1));
        when(dateTimeService.daysBetween(request.getAgreementDateFrom(),request.getAgreementDateTo())).thenReturn(31L);
        assertEquals(new BigDecimal(31),premiumUnderwriting.calculatePremium(request));
    }
}