package lv.javaguru.travel.insurance.core;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
class DateTimeService {

    public int calculateTravelPeriod(LocalDate agreementDateFrom, LocalDate agreementDateTo) {
        return Period.between(agreementDateFrom, agreementDateTo).getDays();
    }
}
