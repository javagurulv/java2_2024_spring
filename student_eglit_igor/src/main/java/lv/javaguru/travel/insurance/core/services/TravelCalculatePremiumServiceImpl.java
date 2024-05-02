package lv.javaguru.travel.insurance.core.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumCalculationResult;
import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import lv.javaguru.travel.insurance.core.validation.TravelCalculatePremiumRequestValidatorInterface;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumResponseV1;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Component
public class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {


    @Autowired
    private TravelCalculatePremiumRequestValidatorInterface requestValidator;
    @Autowired
    private TravelPremiumUnderwriting premiumUnderwriting;

    @Override
    public TravelCalculatePremiumResponseV1 calculatePremium(TravelCalculatePremiumRequestV1 request) {
        List<ValidationError> errors = requestValidator.validate(request);
        return errors.isEmpty()
                ? buildResponse(request, premiumUnderwriting.calculateAgreementPremium(request))
                : buildResponse(errors);
    }

    private TravelCalculatePremiumResponseV1 buildResponse(List<ValidationError> errors) {
        return new TravelCalculatePremiumResponseV1(errors);
    }

    private TravelCalculatePremiumResponseV1 buildResponse(TravelCalculatePremiumRequestV1 request, TravelPremiumCalculationResult travelPremiumCalculationResult) {
        TravelCalculatePremiumResponseV1 response = new TravelCalculatePremiumResponseV1();
        response.setPersonFirstName(request.getPersonFirstName());
        response.setPersonLastName(request.getPersonLastName());
        response.setPersonBirthDate(request.getPersonBirthDate());
        response.setAgreementDateFrom(request.getAgreementDateFrom());
        response.setAgreementDateTo(request.getAgreementDateTo());
        response.setCountry(request.getCountry());
        response.setAgreementPremium(travelPremiumCalculationResult.getTotalPremium());
        response.setSelected_risks(travelPremiumCalculationResult.getRiskPremiums());
        return response;
    }
}