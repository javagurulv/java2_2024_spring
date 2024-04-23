package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;

import java.util.List;
import java.util.Optional;

abstract class RequestValidationIntImpl implements RequestValidationInterface{
    @Override
    public Optional<ValidationError> validateSingle(TravelCalculatePremiumRequest request) {
        return Optional.empty();
    }

    @Override
    public List<ValidationError> validateList(TravelCalculatePremiumRequest request) {
        return List.of();
    }
}

