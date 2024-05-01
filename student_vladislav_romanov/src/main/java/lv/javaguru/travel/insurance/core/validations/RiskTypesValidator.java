package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class RiskTypesValidator extends TravelRequestValidatorImpl {

    @Override
    public List<ValidationError> validateList(TravelCalculatePremiumRequest request) {
        return List.of();
    }

}