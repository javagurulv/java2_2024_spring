package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationErrors;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
class DateFromSmallerThanDateToValidator implements TravelRequestValidation{

    @Override
    public Optional<ValidationErrors> execute(TravelCalculatePremiumRequest request) {
        Date dateFrom = request.getAgreementDateFrom();
        Date dateTo = request.getAgreementDateTo();
        return (dateFrom != null && dateTo != null
                && (dateFrom.equals(dateTo) || dateTo.before(dateFrom)))
                ? Optional.of(new ValidationErrors("dateFrom", "Cannot be bigger or equal to dateTo"))
                : Optional.empty();
    }
}
