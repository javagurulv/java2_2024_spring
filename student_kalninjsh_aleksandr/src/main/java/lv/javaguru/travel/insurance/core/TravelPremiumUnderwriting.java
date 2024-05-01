package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
public class TravelPremiumUnderwriting {

    @Autowired
    private DateTimeService service;

    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        long numberOfDaysBetweenDates = service.calculateAgreementDaysBetweenDates(request.getAgreementDateFrom(), request.getAgreementDateTo());
        return new  BigDecimal(numberOfDaysBetweenDates);
    }
}
