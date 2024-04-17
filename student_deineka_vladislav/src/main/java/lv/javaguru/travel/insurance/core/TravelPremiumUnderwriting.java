package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class TravelPremiumUnderwriting {

    @Autowired
    private DateTimeService dateTimeService;

    BigDecimal calculatedPremium(TravelCalculatePremiumRequest premiumRequest) {
        long daysFromTo = dateTimeService.calculateDateFromTo(premiumRequest.getAgreementDateFrom(), premiumRequest.getAgreementDateTo());
        return new BigDecimal(daysFromTo);

    }

}
