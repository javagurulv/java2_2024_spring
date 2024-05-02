package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.ValidationError;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class ValidateAgreementDateToNotNull extends RequestAgreementFieldValidationImpl {

    @Autowired
    private ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationError> validateSingle(TravelCalculatePremiumRequestV1 request) {
        return (request.getAgreementDateTo() == null)
                ? Optional.of(validationErrorFactory.buildError("ERROR_CODE_4"))
                : Optional.empty();
    }

}
