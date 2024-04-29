package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
class ValidateAgreementDateToIsNotBeforeAgreementDateFrom extends TravelRequestValidationImpl{

    @Autowired private ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request){
        if (request.getAgreementDateFrom() != null && request.getAgreementDateTo() != null) {
            return (request.getAgreementDateTo().before(request.getAgreementDateFrom()) || request.getAgreementDateTo().equals(request.getAgreementDateFrom()))
                    ? Optional.of(errorFactory.buildError("ERROR_CODE_6"))
                    : Optional.empty();
        }
        else {
            return Optional.empty();
        }
    }
}
