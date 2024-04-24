package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationErrors;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class DateFromValidator implements TravelRequestValidation{

    @Override
    public Optional<ValidationErrors> execute(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateFrom() == null)
                ? Optional.of(new ValidationErrors("dateFrom", "Field cannot be empty"))
                : Optional.empty();

    }
}
