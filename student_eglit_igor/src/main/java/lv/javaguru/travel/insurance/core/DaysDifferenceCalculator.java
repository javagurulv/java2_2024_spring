package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

class DaysDifferenceCalculator {
    public static long daysCalculator(TravelCalculatePremiumRequest request) {
        LocalDate dateFrom = request.getAgreementDateFrom();
        LocalDate dateTo = request.getAgreementDateTo();

        return (ChronoUnit.DAYS.between(dateFrom, dateTo) + 1);
    }
}
