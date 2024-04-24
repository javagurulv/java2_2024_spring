package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.util.DateTimeService;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class TravelPremiumUnderwritingImpl implements TravelPremiumUnderwriting{
    @Autowired
    DateTimeService dateTimeService = new DateTimeService();

    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        return BigDecimal.valueOf(dateTimeService.calculateDaysBetweenDates(request.getAgreementDateFrom(),request.getAgreementDateTo()));
    }
}
