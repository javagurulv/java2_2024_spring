package lv.javaguru.travel.insurance.core;

import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public
class DateTimeService {

    public long calculateDifferenceBetweenDays(Date dateFrom, Date dateTo) {
        long differenceInMillis = dateTo.getTime() - dateFrom.getTime();
        return TimeUnit.DAYS.convert(differenceInMillis, TimeUnit.MILLISECONDS);
    }

    public Date midnightToday() { // today 00:00:00 EET
        Calendar today = Calendar.getInstance();
        today.setTime(new Date());
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);
        return today.getTime();
    }

}
