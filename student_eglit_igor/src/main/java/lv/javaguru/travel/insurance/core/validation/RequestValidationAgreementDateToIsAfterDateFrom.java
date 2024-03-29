package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class RequestValidationAgreementDateToIsAfterDateFrom implements RequestValidationInterface {

    @Override
    public Optional<ValidationError> executeValidation(TravelCalculatePremiumRequest request) {

        if (request.getAgreementDateTo() == null || request.getAgreementDateFrom() == null) {
            return Optional.empty();
        }
        return (request.getAgreementDateTo().isBefore(request.getAgreementDateFrom()))
                ? Optional.of(new ValidationError("agreementDateTo", "agreementDateTo must be after agreementDateFrom!"))
                : Optional.empty();
    }
}

