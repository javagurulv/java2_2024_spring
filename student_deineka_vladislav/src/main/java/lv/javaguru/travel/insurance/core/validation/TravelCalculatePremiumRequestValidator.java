package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationErrors;

import java.util.List;

public interface TravelCalculatePremiumRequestValidator {

    List<ValidationErrors> validation(TravelCalculatePremiumRequest request);
}
