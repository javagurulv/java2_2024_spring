package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateTimeService {


    public BigDecimal calculateAgreementDaysBetweenDates(TravelCalculatePremiumRequest request) {
        if (request.getAgreementDateFrom() != null && request.getAgreementDateTo() != null) {
            long difference = request.getAgreementDateTo().getTime() - request.getAgreementDateFrom().getTime();
            long numberOfDaysBetweenDates = TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
            return BigDecimal.valueOf(numberOfDaysBetweenDates);
        } else {
            return null;
        }

    }
}
