package lv.javaguru.travel.insurance.core.util;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class DateTimeUtil {

    public long calculateDifferenceBetweenDatesInDays(Date dateFrom, Date dateTo) {
        long differenceInMillis = dateTo.getTime() - dateFrom.getTime();
        return TimeUnit.DAYS.convert(differenceInMillis, TimeUnit.MILLISECONDS);
    }

    public int calculateDifferenceBetweenDatesInYears(Date dateFrom, Date dateTo) {
        LocalDate localDateFrom = dateFrom.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDateTo = dateTo.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Period period = Period.between(localDateFrom, localDateTo);
        return period.getYears();
    }

    public Date startOfToday() { // today 00:00:00 EET
        Calendar today = Calendar.getInstance();
        today.setTime(new Date());
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);
        return today.getTime();
    }

    public Date currentTimeToday() {
        Calendar today = Calendar.getInstance();
        today.setTime(new Date());
        return today.getTime();
    }

    public Date subtractYears(Date date, int years) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, -years);
        return cal.getTime();
    }

}
