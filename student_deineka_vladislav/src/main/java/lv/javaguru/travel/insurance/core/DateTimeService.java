package lv.javaguru.travel.insurance.core;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
class DateTimeService {

     long calculateDateFromTo(Date dateFrom, Date dateTo) {
        long diff = dateTo.getTime() - dateFrom.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

    }

}
