package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();
        response.setPersonFirstName(request.getPersonFirstName());
        response.setPersonLastName(request.getPersonLastName());
        response.setAgreementDateFrom(request.getAgreementDateFrom());
        response.setAgreementDateTo(request.getAgreementDateTo());
        response.setAgreementPrice(daysBetweenDates(request));

        return response;
    }

    public BigDecimal daysBetweenDates(TravelCalculatePremiumRequest request) {
        if (request.getAgreementDateFrom() != null && request.getAgreementDateTo() != null) {
            long difference = request.getAgreementDateTo().getTime() - request.getAgreementDateFrom().getTime();
            long daysBetween = TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
            return BigDecimal.valueOf(daysBetween);
        }
        else {
            return null;
        }
    }


}