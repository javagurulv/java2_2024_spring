package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
class RequestValidationSelectedRisks implements RequestValidationInterface{
    @Override
    public Optional<ValidationError> executeValidation(TravelCalculatePremiumRequest request) {
       return (request.getInsuranceRisks() == null || request.getInsuranceRisks().isEmpty())
                    ? Optional.of(new ValidationError("selected_risks", "Must not be empty!"))
                    : Optional.empty();
    }
}
