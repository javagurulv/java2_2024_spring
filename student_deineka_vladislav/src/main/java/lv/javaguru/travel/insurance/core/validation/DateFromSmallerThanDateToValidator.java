package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
class DateFromSmallerThanDateToValidator implements TravelRequestValidation {

    @Autowired
    private ValidationErrorFactory validationErrorFactory;


    @Override
    public Optional<ValidationErrors> execute(TravelCalculatePremiumRequest request) {
        Date dateFrom = request.getAgreementDateFrom();
        Date dateTo = request.getAgreementDateTo();
        return (dateFrom != null && dateTo != null
                && (dateFrom.equals(dateTo) || dateTo.before(dateFrom)))
                ? Optional.of(validationErrorFactory.createError("ERROR_CODE_7"))
                : Optional.empty();
    }

}
