package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.underwriting.TravelCalculatePremiumUnderwriting;
import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumCalculationResult;
import lv.javaguru.travel.insurance.core.validations.TravelCalculatePremiumRequestValidator;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    @Autowired
    private TravelCalculatePremiumRequestValidator requestValidator;
    @Autowired
    private TravelCalculatePremiumUnderwriting calculateUnderwriting;

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        List<ValidationError> requestErrors = requestValidator.validate(request);
        if (!requestErrors.isEmpty()) {
            return new TravelCalculatePremiumResponse(requestErrors);
        }

        return createPremiumResponse(request);
    }

    private TravelCalculatePremiumResponse createPremiumResponse(TravelCalculatePremiumRequest request) {
        TravelPremiumCalculationResult calculationResult = calculateUnderwriting.calculateAgreementPremium(request);

        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();
        response.setPersonFirstName(request.getPersonFirstName());
        response.setPersonLastName(request.getPersonLastName());
        response.setPersonBirthDate(request.getPersonBirthDate());
        response.setAgreementDateFrom(request.getAgreementDateFrom());
        response.setAgreementDateTo(request.getAgreementDateTo());
        response.setAgreementPremium(calculationResult.getAgreementPremium());
        response.setRiskPremiums(calculationResult.getRiskPremiums());
        response.setCountry(request.getCountry());

        return response;
    }

}
