package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Component
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
