package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


public interface RequestValidationInterface {

    Optional<ValidationError> validateReq(TravelCalculatePremiumRequest request);
    List<ValidationError> validateList(TravelCalculatePremiumRequest request);

}