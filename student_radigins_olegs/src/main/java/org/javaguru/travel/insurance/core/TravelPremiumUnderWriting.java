package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class TravelPremiumUnderWriting {
    @Autowired
    private DateTimeService dateTimeService;

    BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        long daysBetween = dateTimeService.daysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo());
        return new BigDecimal(daysBetween);
    }
}
