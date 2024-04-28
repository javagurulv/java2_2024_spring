package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
@Component
public class DateTimeService {

    public long calculateDateFromTo(TravelCalculatePremiumRequest request) {
        long diff = request.getAgreementDateFrom().getTime() - request.getAgreementDateTo().getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);


    }

}
