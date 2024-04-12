package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
class RequestValidationAgreementDateFromNotInThePast implements RequestValidationInterface{

    @Autowired
    private ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationError> executeValidation(TravelCalculatePremiumRequest request) {
        String errorCode = "ERROR_CODE_5";
        if (request.getAgreementDateTo() == null || request.getAgreementDateFrom() == null) {
            return Optional.empty();
        }
        return (request.getAgreementDateFrom().isBefore(java.time.LocalDate.now().plusDays(1)))
                ? Optional.of(validationErrorFactory.buildError(errorCode))
                : Optional.empty();
    }
}
