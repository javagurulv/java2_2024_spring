package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
@Component
public class DateTimeService {
    public long calculateDaysBetweenDates(Date startDate, Date endDate) {

        return (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24);
    }
}
