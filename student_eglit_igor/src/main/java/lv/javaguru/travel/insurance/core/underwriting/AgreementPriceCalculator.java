package lv.javaguru.travel.insurance.core.underwriting;

import lombok.AllArgsConstructor;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@AllArgsConstructor

@Component
class AgreementPriceCalculator implements AgreementPriceCalculatorInterface{
    @Autowired
    private DateTimeUtil dateTimeUtil;


    public  BigDecimal calculateAgreementPrice(TravelCalculatePremiumRequest request) {

        return new BigDecimal(dateTimeUtil.daysCalculator(request.getAgreementDateFrom(), request.getAgreementDateTo()));

    }
}
