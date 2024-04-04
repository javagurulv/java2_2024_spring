package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;
@Component
public class ValidateAgreementDateFromIsNotBeforeCurrentDate {
    public Optional<ValidationError> validateAgreementDateFromIsNotBeforeCurrentDate(TravelCalculatePremiumRequest request){
        Date currentDate = new Date(System.currentTimeMillis());
        if (request.getAgreementDateFrom() != null) {
            return (request.getAgreementDateFrom().before(currentDate))
                    ? Optional.of(new ValidationError("agreementDateFrom", "Invalid date !"))
                    : Optional.empty();

        } else {
            return Optional.empty();
        }
    }
}
