package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.validations.RequestAgreementFieldValidation;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.ValidationError;

import java.util.List;
import java.util.Optional;

abstract class RequestAgreementFieldValidationImpl implements RequestAgreementFieldValidation {

    @Override
    public Optional<ValidationError> validateSingle(TravelCalculatePremiumRequestV1 request) {
        return Optional.empty();
    }

    @Override
    public List<ValidationError> validateList(TravelCalculatePremiumRequestV1 request) {
        return null;
    }

}
