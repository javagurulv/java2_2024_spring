package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    @Autowired
    private DateTimeService dateTimeService;

    @Autowired
    private TravelCalculatePremiumRequestValidator requestValidator;

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        List<ValidationError> requestErrors = requestValidator.validate(request);
        if (!requestErrors.isEmpty()) {
            return new TravelCalculatePremiumResponse(requestErrors);
        }

        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();

        Date agreementDateFrom = request.getAgreementDateFrom();
        Date agreementDateTo = request.getAgreementDateTo();

        response.setPersonFirstName(request.getPersonFirstName());
        response.setPersonLastName(request.getPersonLastName());
        response.setAgreementDateFrom(agreementDateFrom);
        response.setAgreementDateTo(agreementDateTo);

        long differenceBetweenDays = dateTimeService.calculateDifferenceBetweenDays(agreementDateFrom, agreementDateTo);
        response.setAgreementPrice(BigDecimal.valueOf(differenceBetweenDays));

        return response;
    }

}
