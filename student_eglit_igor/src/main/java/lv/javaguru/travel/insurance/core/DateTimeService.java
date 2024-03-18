package lv.javaguru.travel.insurance.core;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

@Component
class DateTimeService {

    public long daysCalculator(LocalDate dateFrom, LocalDate dateTo) {

        return (DAYS.between(dateFrom, dateTo) + 1);
    }
}
