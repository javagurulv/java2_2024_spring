package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class TravelPremium {

    @Autowired
    private DateTimeService dateTimeService;

    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        return new BigDecimal(dateTimeService.calculateTravelPeriod(request.getAgreementDateFrom(), request.getAgreementDateTo()));
    }
}
