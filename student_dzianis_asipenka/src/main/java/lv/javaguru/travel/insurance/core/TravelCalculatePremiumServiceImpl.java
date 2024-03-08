package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        TravelCalculatePremiumResponse travelCalculatePremiumResponse = new TravelCalculatePremiumResponse();
        travelCalculatePremiumResponse.setPersonFirstName(request.getPersonFirstName());
        travelCalculatePremiumResponse.setPersonLastName(request.getPersonLastName());
        travelCalculatePremiumResponse.setAgreementDateTo(request.getAgreementDateTo());
        travelCalculatePremiumResponse.setAgreementDateFrom(request.getAgreementDateFrom());
        long diffInMillies = Math.abs(request.getAgreementDateTo().getTime()-request.getAgreementDateFrom().getTime());
        long daysBetween = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        travelCalculatePremiumResponse.setAgreementPrice(BigDecimal.valueOf(daysBetween));
        return travelCalculatePremiumResponse;
    }
}