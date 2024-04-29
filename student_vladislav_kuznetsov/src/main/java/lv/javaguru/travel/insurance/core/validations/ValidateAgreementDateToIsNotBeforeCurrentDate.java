package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;
@Component
class ValidateAgreementDateToIsNotBeforeCurrentDate extends TravelRequestValidationImpl{

    @Autowired private ValidationErrorFactory errorFactory;

        public Optional<ValidationError> validate(TravelCalculatePremiumRequest request){
            Date currentDate = new Date(System.currentTimeMillis());
            if (request.getAgreementDateTo() != null) {
                return (request.getAgreementDateTo().before(currentDate))
                        ? Optional.of(errorFactory.buildError("ERROR_CODE_6"))
                        : Optional.empty();

            } else {
                return Optional.empty();
            }
        }
}
