package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Period;
import java.util.Optional;

@Component
class TravelPeriodValidator implements TravelRequestValidator {

    @Autowired
    private ValidationErrorFactory validationErrorFactory;

    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateFrom() == null || request.getAgreementDateTo() == null || Period.between(request.getAgreementDateFrom(), request.getAgreementDateTo()).getDays() < 1)
                ? Optional.of(validationErrorFactory.buildError(7))
                : Optional.empty();
    }

}