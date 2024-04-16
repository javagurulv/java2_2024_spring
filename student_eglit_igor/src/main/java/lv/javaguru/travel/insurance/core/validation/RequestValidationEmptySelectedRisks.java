package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class RequestValidationEmptySelectedRisks extends RequestValidationIntImpl {

    @Autowired
    private ClassifierValueRepository classifierValueRepository;
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
