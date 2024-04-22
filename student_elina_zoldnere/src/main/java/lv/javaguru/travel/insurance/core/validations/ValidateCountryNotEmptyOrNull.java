package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class ValidateCountryNotEmptyOrNull extends RequestFieldValidationImpl {

    @Autowired
    private ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationError> validateSingle(TravelCalculatePremiumRequest request) {
        return (containsRisk("TRAVEL_MEDICAL", request)
                && (request.getCountry() == null || request.getCountry().isBlank()))
                ? Optional.of(validationErrorFactory.buildError("ERROR_CODE_6"))
                : Optional.empty();
    }

    private boolean containsRisk(String riskType, TravelCalculatePremiumRequest request) {
        if (request.getSelectedRisks() == null || request.getSelectedRisks().isEmpty()) {
            return false;
        }
        return request.getSelectedRisks().contains(riskType);
    }

}