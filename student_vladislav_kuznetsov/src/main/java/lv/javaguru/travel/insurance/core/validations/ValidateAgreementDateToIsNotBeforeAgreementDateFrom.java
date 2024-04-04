package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
class ValidateAgreementDateToIsNotBeforeAgreementDateFrom implements TravelRequestValidation{
    @Override
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request){
        if (request.getAgreementDateFrom() != null && request.getAgreementDateTo() != null) {
            return (request.getAgreementDateTo().before(request.getAgreementDateFrom()) || request.getAgreementDateTo().equals(request.getAgreementDateFrom()))
                    ? Optional.of(new ValidationError("agreementDateTo", "Invalid date !"))
                    : Optional.empty();
        }
        else {
            return Optional.of(new ValidationError("agreementDateTo and agreementDateTo", "Must not be empty!"));
        }
    }
}
