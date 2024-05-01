package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class DateTimeService {

    public long calculateAgreementDaysBetweenDates(Date dateFrom, Date dateTo) {
        long difference = dateTo.getTime() - dateFrom.getTime();
        return TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);


    }
}
