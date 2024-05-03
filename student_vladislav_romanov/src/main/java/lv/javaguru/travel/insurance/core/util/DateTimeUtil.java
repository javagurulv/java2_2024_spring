package lv.javaguru.travel.insurance.core.util;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public
class DateTimeUtil {

    public int calculateTravelPeriod(LocalDate agreementDateFrom, LocalDate agreementDateTo) {
        if (agreementDateFrom == null || agreementDateTo == null) {
            return -1;
        }
        return Period.between(agreementDateFrom, agreementDateTo).getDays();
    }
}