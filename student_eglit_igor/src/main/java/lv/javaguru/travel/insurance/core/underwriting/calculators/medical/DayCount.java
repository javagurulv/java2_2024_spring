package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;


import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class DayCount {

    @Autowired private DateTimeUtil dateTimeUtil;

    BigDecimal calculateDayCount(TravelCalculatePremiumRequest request) {
        var daysBetween = dateTimeUtil.daysCalculator(request.getAgreementDateFrom(), request.getAgreementDateTo());
        return new BigDecimal(daysBetween);
    }
}
