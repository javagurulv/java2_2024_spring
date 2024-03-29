package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;


import java.util.Optional;
@Component
public interface RequestValidationInterface {
    public Optional<ValidationError> executeValidation(TravelCalculatePremiumRequest request);

}