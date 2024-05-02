package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TravelCalculatePremiumRequestValidatorInterface {
    public List<ValidationError> validate(TravelCalculatePremiumRequestV1 request);
}
