package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
class DateTimeService {

    public long daysCalculator(LocalDate dateFrom, LocalDate dateTo) {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        dateFrom = request.getAgreementDateFrom();
        dateTo = request.getAgreementDateTo();

        return (ChronoUnit.DAYS.between(dateFrom, dateTo) + 1);
    }
}
