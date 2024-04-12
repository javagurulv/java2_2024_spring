package lv.javaguru.travel.insurance.core.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import lv.javaguru.travel.insurance.core.underwriting.AgreementPriceCalculatorInterface;
import lv.javaguru.travel.insurance.core.validation.TravelCalculatePremiumRequestValidatorInterface;
import lv.javaguru.travel.insurance.dto.CoreResponse;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    @Autowired
    private AgreementPriceCalculatorInterface agreementPriceCalculator;
    @Autowired
    private TravelCalculatePremiumRequestValidatorInterface validate;

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        CoreResponse coreResponse = new CoreResponse(validate.validate(request));
        if (coreResponse.hasErrors()) {
            return new TravelCalculatePremiumResponse(coreResponse.getErrors());
        } else {
            return getResponse(request);
        }
    }

    private TravelCalculatePremiumResponse getResponse(TravelCalculatePremiumRequest request) {
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();
        response.setPersonFirstName(request.getPersonFirstName());
        response.setPersonLastName(request.getPersonLastName());
        response.setAgreementDateFrom(request.getAgreementDateFrom());
        response.setAgreementDateTo(request.getAgreementDateTo());
        response.setAgreementPrice(calculateAgreementPrice(request));
        response.setSelected_risks(request.getInsuranceRisks());
        return response;
    }

    public BigDecimal calculateAgreementPrice(TravelCalculatePremiumRequest request) {
        return agreementPriceCalculator.calculateAgreementPrice(
                request);
    }
}