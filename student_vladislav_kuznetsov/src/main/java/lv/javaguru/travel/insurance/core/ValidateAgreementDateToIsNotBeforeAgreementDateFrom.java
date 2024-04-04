package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class ValidateAgreementDateToIsNotBeforeAgreementDateFrom {
    public Optional<ValidationError> validateAgreementDateToIsNotBeforeAgreementDateFrom(TravelCalculatePremiumRequest request){
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
