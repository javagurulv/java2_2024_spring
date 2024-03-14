package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        String personFirstName = request.getPersonFirstName();
        String personLastName = request.getPersonLastName();
        Date agreementDataFrom = request.getAgreementDateFrom();
        Date agreementDateTo = request.getAgreementDateTo();
        return new TravelCalculatePremiumResponse(personFirstName, personLastName, agreementDataFrom, agreementDateTo);
    }

}
