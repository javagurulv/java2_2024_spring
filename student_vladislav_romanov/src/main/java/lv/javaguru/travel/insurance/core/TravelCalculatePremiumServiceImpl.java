package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumUtilities;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();
        TravelCalculatePremiumUtilities utilities = new TravelCalculatePremiumUtilities();

        response.setPersonFirstName(request.getPersonFirstName());
        response.setPersonLastName(request.getPersonLastName());
        response.setAgreementDateFrom(request.getAgreementDateFrom());
        response.setAgreementDateTo(request.getAgreementDateTo());
        response.setAgreementPrice(new BigDecimal(utilities.calculateTravelPeriod(request.getAgreementDateFrom(), request.getAgreementDateTo())));

        return response;
    }

}
