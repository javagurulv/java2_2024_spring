package lv.javaguru.travel.insurance.core;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateTimeService {

    public long calculateDifferenceBetweenDays(Date dateFrom, Date dateTo) {
            long differenceInMillis = dateTo.getTime() - dateFrom.getTime();
            long differenceInDays = TimeUnit.DAYS.convert(differenceInMillis, TimeUnit.MILLISECONDS);
            return differenceInDays;
    }
}
