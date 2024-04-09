package org.javaguru.travel.insurance.core.validations.agreement;

import org.javaguru.travel.insurance.core.validations.TravelAgreementFieldValidation;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;

import java.util.List;
import java.util.Optional;

abstract class TravelAgreementFieldValidationImpl implements TravelAgreementFieldValidation {

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        return Optional.empty();
    }

    @Override
    public List<ValidationError> validateList(TravelCalculatePremiumRequest request) {
        return null;
    }

}
