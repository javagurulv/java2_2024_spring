package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;

import java.math.BigDecimal;
import java.util.Date;

public class DateTimeService {
    public long calculateDaysBetweenDates(Date startDate, Date endDate) {
        return (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24);
    }
}
