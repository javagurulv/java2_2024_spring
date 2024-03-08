package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

class AgreementPriceCalculator {
    public static BigDecimal calculateAgreementPrice(TravelCalculatePremiumRequest request) {
        LocalDate dateFrom = request.getAgreementDateFrom();
        LocalDate dateTo = request.getAgreementDateTo();

        return BigDecimal.valueOf(ChronoUnit.DAYS.between(dateFrom, dateTo)+1);

    }
}
