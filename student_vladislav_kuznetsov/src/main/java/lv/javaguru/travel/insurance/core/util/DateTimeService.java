package lv.javaguru.travel.insurance.core.util;

import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class DateTimeService {
    public long calculateDaysBetweenDates(Date startDate, Date endDate) {
        long diff = endDate.getTime() - startDate.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
    public Date getCurrentDateTime() {
        ZoneId zone = ZoneId.of("Europe/Riga");
        ZonedDateTime zonedDateTime = ZonedDateTime.now(zone);
        return Date.from(zonedDateTime.toInstant());
    }
}
