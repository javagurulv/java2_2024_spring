package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationErrors;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class NoOneRiskSelectedValidator implements TravelRequestValidation {

    @Override
    public Optional<ValidationErrors> execute(TravelCalculatePremiumRequest request) {
        return (request.getSelectedRisks() == null || request.getSelectedRisks().isEmpty())
                ? Optional.of(new ValidationErrors("selectedRisk", "Field cannot be empty"))
                : Optional.empty();
    }
}
