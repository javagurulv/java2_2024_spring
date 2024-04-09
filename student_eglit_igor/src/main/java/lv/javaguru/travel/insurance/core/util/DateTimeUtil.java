package lv.javaguru.travel.insurance.core.util;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

@Component
public class DateTimeUtil {

    public long daysCalculator(LocalDate dateFrom, LocalDate dateTo) {

        return (DAYS.between(dateFrom, dateTo) + 1);
    }
}
