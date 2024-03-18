package lv.javaguru.travel.insurance.core;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class DateTimeService {

    public long calculateDifferenceBetweenDays(Date dateFrom, Date dateTo) {
        long differenceInMillis = dateTo.getTime() - dateFrom.getTime();
        long differenceInDays = TimeUnit.DAYS.convert(differenceInMillis, TimeUnit.MILLISECONDS);
        return differenceInDays;
    }
}
