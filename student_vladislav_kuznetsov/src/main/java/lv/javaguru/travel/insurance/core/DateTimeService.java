package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;

import java.math.BigDecimal;

public class DateTimeService {
    public BigDecimal calculatePremiumPriceByDates(TravelCalculatePremiumRequest request) {
        long agreementTimeFrom =request.getAgreementDateFrom().getTime();
        long agreementTimeTo = request.getAgreementDateTo().getTime();
        long differenceInMillis = Math.abs(agreementTimeFrom - agreementTimeTo);
        long daysBetween = differenceInMillis / (1000 * 60 * 60 * 24);
        return BigDecimal.valueOf(daysBetween);
    }
}
