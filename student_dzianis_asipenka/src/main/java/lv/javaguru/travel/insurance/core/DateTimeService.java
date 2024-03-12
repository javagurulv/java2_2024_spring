package lv.javaguru.travel.insurance.core;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class DateTimeService {
    BigDecimal differenceBetweenDates(Date dataFrom, Date dataTo) {
        long diffInMillies = Math.abs(dataTo.getTime() - dataFrom.getTime());
        long daysBetween = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return new BigDecimal(BigInteger.valueOf(daysBetween));
    }
}