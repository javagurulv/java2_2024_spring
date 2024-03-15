package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

@Component
class DateTimeService {

    public long daysCalculator(LocalDate dateFrom, LocalDate dateTo) {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        dateFrom = request.getAgreementDateFrom();
        dateTo = request.getAgreementDateTo();

        return (DAYS.between(dateFrom, dateTo) + 1);
    }
}
