package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;
@Component
public class ValidateAgreementDateToIsNotBeforeCurrentDate {
        public Optional<ValidationError> validateAgreementDateToIsNotBeforeCurrentDate(TravelCalculatePremiumRequest request){
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
