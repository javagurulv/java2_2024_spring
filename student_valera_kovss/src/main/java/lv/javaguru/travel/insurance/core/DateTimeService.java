package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;
@Component
class DateTimeService {

    public long calculateDateFromTo(TravelCalculatePremiumRequest request) {
        long diff = request.getAgreementDateFrom().getTime() - request.getAgreementDateTo().getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);


    }


    public long calculateAgreementDaysBetweenDates(Date agreementDateFrom, Date agreementDateTo) {
        return 0;
    }
}

