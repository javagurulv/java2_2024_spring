package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;

import java.util.List;
import java.util.Optional;


public interface RequestValidationInterface {

    Optional<ValidationError> validateSingle(TravelCalculatePremiumRequest request);
    List<ValidationError> validateList(TravelCalculatePremiumRequest request);

}