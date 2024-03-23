package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    @Autowired
    private DateTimeService dateTimeService;
    @Autowired
    private TravelCalculatePremiumRequestValidator requestValidator = new TravelCalculatePremiumRequestValidator();


    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = requestValidator.validate(request);

        if (!errors.isEmpty()) {
            return new TravelCalculatePremiumResponse(errors);
        }

        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();

        response.setPersonFirstName(request.getPersonFirstName());
        response.setPersonLastName(request.getPersonLastName());

        response.setAgreementDateFrom(request.getAgreementDateFrom());
        response.setAgreementDateTo(request.getAgreementDateTo());

        response.setAgreementPrice(BigDecimal.valueOf(dateTimeService.calculateDaysBetweenDates(request.getAgreementDateFrom(),request.getAgreementDateTo())));
        return response;
    }

}

