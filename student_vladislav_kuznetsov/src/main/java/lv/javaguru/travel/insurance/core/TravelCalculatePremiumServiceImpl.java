package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;



@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    DateTimeService dateTimeService;
    public TravelCalculatePremiumServiceImpl(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();
        response.setPersonFirstName(request.getPersonFirstName());
        response.setPersonLastName(request.getPersonLastName());
        response.setAgreementDateFrom(request.getAgreementDateFrom());
        response.setAgreementDateTo(request.getAgreementDateTo());
        response.setAgreementPrice(BigDecimal.valueOf(dateTimeService.calculateDaysBetweenDates(request.getAgreementDateFrom(),request.getAgreementDateTo())));
        return response;
    }

}

