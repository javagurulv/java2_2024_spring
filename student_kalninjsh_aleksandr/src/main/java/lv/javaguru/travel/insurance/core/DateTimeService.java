package lv.javaguru.travel.insurance.core;

import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class DateTimeService {

    public long calculateAgreementDaysBetweenDates(Date dateFrom, Date dateTo) {
        long difference = dateTo.getTime() - dateFrom.getTime();
        return TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
    }

    Date currentDate() {
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Europe/Riga"));
        return Date.from(zonedDateTime.toInstant());
    }

}
