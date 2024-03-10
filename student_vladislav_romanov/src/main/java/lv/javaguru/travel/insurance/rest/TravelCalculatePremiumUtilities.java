package lv.javaguru.travel.insurance.rest;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class TravelCalculatePremiumUtilities {

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
