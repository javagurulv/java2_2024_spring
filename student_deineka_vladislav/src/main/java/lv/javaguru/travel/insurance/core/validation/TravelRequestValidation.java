package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationErrors;

import java.util.List;
import java.util.Optional;

interface TravelRequestValidation {

    Optional<ValidationErrors> validate(TravelCalculatePremiumRequest travelCalculatePremiumRequest);
    List<ValidationErrors> listValidation(TravelCalculatePremiumRequest premiumRequest);


}
