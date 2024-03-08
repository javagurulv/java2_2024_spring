package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;

import java.math.BigDecimal;

import static lv.javaguru.travel.insurance.core.DaysDifferenceCalculator.daysCalculator;

class AgreementPriceCalculator {
    public static BigDecimal calculateAgreementPrice(TravelCalculatePremiumRequest request) {

        return new BigDecimal(daysCalculator(request));

    }
}
