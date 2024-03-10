package lv.javaguru.travel.insurance.core;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@Component
class DateTimeService {

    public int calculateTravelPeriod(Date agreementDateFrom, Date agreementDateTo) {
        return Period.
                between(convertToLocalDateViaInstant(agreementDateFrom), convertToLocalDateViaInstant(agreementDateTo))
                .getDays();
    }

    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
