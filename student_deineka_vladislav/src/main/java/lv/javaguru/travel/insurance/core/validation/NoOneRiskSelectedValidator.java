package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class NoOneRiskSelectedValidator implements TravelRequestValidation {

    @Autowired
    ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationErrors> execute(TravelCalculatePremiumRequest request) {
        return (request.getSelectedRisks() == null || request.getSelectedRisks().isEmpty())
                ? Optional.of(validationErrorFactory.createError("ERROR_CODE_8"))
                : Optional.empty();
    }
}
