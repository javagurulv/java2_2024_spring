package lv.javaguru.travel.insurance.core.validator;

import lv.javaguru.travel.insurance.core.DateTimeService;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TravelPeriodIsValid {

    @Autowired
    public DateTimeService dateTimeService;

    public Optional<ValidationError> validateTravelPeriod(TravelCalculatePremiumRequest request) {
        return (dateTimeService.calculateTravelPeriod(request.getAgreementDateFrom(), request.getAgreementDateTo()) < 1)
                ? Optional.of(new ValidationError("Travel Period", "contain incorrect data!"))
                : Optional.empty();
    }

}