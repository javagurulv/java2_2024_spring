package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TravelCalculatePremiumRequestValidatorInterface {
    public List<ValidationError> validate(TravelCalculatePremiumRequest request);
}
