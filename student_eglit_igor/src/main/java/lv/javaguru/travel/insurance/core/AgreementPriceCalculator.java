package lv.javaguru.travel.insurance.core;

import lombok.AllArgsConstructor;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor

@Component
class AgreementPriceCalculator {
    @Autowired
    private DateTimeUtil dateTimeUtil;


    public  BigDecimal calculateAgreementPrice(LocalDate dateFrom, LocalDate dateTo) {

        return new BigDecimal(dateTimeUtil.daysCalculator(dateFrom, dateTo));

    }
}
