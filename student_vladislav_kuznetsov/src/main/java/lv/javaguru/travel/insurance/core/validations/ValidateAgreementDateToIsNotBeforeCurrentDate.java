package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;
@Component
class ValidateAgreementDateToIsNotBeforeCurrentDate implements TravelRequestValidation{
        public Optional<ValidationError> execute(TravelCalculatePremiumRequest request){
            Date currentDate = new Date(System.currentTimeMillis());
            if (request.getAgreementDateTo() != null) {
                return (request.getAgreementDateTo().before(currentDate))
                        ? Optional.of(new ValidationError("agreementDateTo", "Invalid date !"))
                        : Optional.empty();

            } else {
                return Optional.empty();
            }
        }
}
