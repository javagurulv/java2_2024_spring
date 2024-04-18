package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
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


        response.setAgreementPrice(new BigDecimal(calculateDaysBetween(request)));

        return response;
    }
    private long calculateDaysBetween(TravelCalculatePremiumRequest request){
        long diff =request.getAgreementDateTo().getTime() -  request.getAgreementDateFrom().getTime();
        long daysBetween = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
       return daysBetween;
    }
}
