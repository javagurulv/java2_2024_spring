package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationErrors;

import java.util.List;
import java.util.Optional;

abstract class TravelRequestValidationImpl implements TravelRequestValidation {

    @Override
    public Optional<ValidationErrors> validate(TravelCalculatePremiumRequest premiumRequest) {
        return Optional.empty();
    }

    @Override
    public List<ValidationErrors> listValidation(TravelCalculatePremiumRequest premiumRequest) {
        return List.of();
    }

}
