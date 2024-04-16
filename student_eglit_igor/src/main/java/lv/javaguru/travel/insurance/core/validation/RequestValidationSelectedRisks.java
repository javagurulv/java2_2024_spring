package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class RequestValidationSelectedRisks extends RequestValidationIntImpl{

    @Autowired
    private ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationError> validateReq(TravelCalculatePremiumRequest request) {
        String errorCode = "ERROR_CODE_8";
        return (request.getInsuranceRisks() == null || request.getInsuranceRisks().isEmpty())
                ? Optional.of(validationErrorFactory.buildError(errorCode))
                : Optional.empty();
    }
}
