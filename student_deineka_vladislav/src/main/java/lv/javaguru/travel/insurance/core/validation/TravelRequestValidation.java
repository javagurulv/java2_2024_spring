package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationErrors;

import java.util.Optional;

public interface TravelRequestValidation {

    Optional<ValidationErrors> execute(TravelCalculatePremiumRequest travelCalculatePremiumRequest);
}
