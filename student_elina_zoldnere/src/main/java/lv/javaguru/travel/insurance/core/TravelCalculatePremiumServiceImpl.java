package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    private DateTimeService dateTimeService;

    public TravelCalculatePremiumServiceImpl(DateTimeService dateTimeService) { // dependency injection
        this.dateTimeService = dateTimeService;
    }

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();

        Date agreementDateFrom = request.getAgreementDateFrom();
        Date agreementDateTo = request.getAgreementDateTo();

        response.setPersonFirstName(request.getPersonFirstName());
        response.setPersonLastName(request.getPersonLastName());
        response.setAgreementDateFrom(agreementDateFrom);
        response.setAgreementDateTo(agreementDateTo);

        long differenceBetweenDays = dateTimeService.calculateDifferenceBetweenDays(agreementDateFrom,agreementDateTo);
        response.setAgreementPrice(BigDecimal.valueOf(differenceBetweenDays));

        return response;
    }

}
