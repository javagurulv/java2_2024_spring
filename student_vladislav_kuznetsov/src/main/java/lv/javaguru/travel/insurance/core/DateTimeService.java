package lv.javaguru.travel.insurance.core;

import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class DateTimeService {
    public long calculateDaysBetweenDates(Date startDate, Date endDate) {

        return (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24);
    }
}
