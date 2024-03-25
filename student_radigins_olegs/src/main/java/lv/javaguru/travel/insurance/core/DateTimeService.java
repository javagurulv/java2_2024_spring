package lv.javaguru.travel.insurance.core;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateTimeService {
    public long calculateAgreementPrice(Date date1, Date date2) {
        long timeDifferent = date1.getTime() - date2.getTime();

        return TimeUnit.DAYS.convert(timeDifferent, TimeUnit.MILLISECONDS);
    }
}
