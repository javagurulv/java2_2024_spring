package lv.javaguru.travel.insurance.core;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor

@Component
class AgreementPriceCalculator {
    @Autowired
    private DateTimeService dateTimeService;


    public  BigDecimal calculateAgreementPrice(LocalDate dateFrom, LocalDate dateTo) {

        return new BigDecimal(dateTimeService.daysCalculator(dateFrom, dateTo));

    }
}
