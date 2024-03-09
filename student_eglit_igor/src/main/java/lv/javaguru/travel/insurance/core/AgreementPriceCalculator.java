package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;


@Component
class AgreementPriceCalculator {
    @Autowired
    private final DateTimeService dateTimeService;

    public AgreementPriceCalculator(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

    public  BigDecimal calculateAgreementPrice(TravelCalculatePremiumRequest request) {

        return new BigDecimal(dateTimeService.daysCalculator(request));

    }
}
