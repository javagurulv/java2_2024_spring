package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import lv.javaguru.travel.insurance.core.underwriting.calculators.TravelPremiumCalculationResult;
import lv.javaguru.travel.insurance.core.validation.TravelCalculatePremiumRequestValidator;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.dto.ValidationErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    @Autowired
    private TravelCalculatePremiumRequestValidator travelCalculatePremiumRequestValidator;
    @Autowired
    private TravelPremiumUnderwriting travelPremiumUnderwriting;


    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        List<ValidationErrors> validationErrors = travelCalculatePremiumRequestValidator.validation(request);
        return validationErrors.isEmpty()
                ? makeResponse(request, travelPremiumUnderwriting.calculationPremium(request)) : makeResponse(validationErrors);
    }

    private TravelCalculatePremiumResponse makeResponse(List<ValidationErrors> validationErrors) {
        return new TravelCalculatePremiumResponse(validationErrors);
    }

    private TravelCalculatePremiumResponse makeResponse(TravelCalculatePremiumRequest request, TravelPremiumCalculationResult premiumCalculationResult) {
        TravelCalculatePremiumResponse travelCalculatePremiumResponse = new TravelCalculatePremiumResponse();
        travelCalculatePremiumResponse.setPersonFirstName(request.getPersonFirstName());
        travelCalculatePremiumResponse.setPersonLastName(request.getPersonLastName());
        travelCalculatePremiumResponse.setAgreementDateFrom(request.getAgreementDateFrom());
        travelCalculatePremiumResponse.setAgreementDateTo(request.getAgreementDateTo());
        travelCalculatePremiumResponse.setAgreementPremium(premiumCalculationResult.getTotalPremium());
        travelCalculatePremiumResponse.setRisks(premiumCalculationResult.getRiskPremiumList());
        travelCalculatePremiumResponse.setCountry(request.getCountry());
        return travelCalculatePremiumResponse;
    }


}