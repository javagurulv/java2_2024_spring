package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.core.DateTimeService;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
class FutureDateFromValidator implements TravelRequestValidation {

    @Autowired
    DateTimeService dateTimeService;
    @Autowired
    ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationErrors> execute(TravelCalculatePremiumRequest request) {
        Date dateFrom = request.getAgreementDateFrom();
        Date today = dateTimeService.getTodayDateTime();
        return (dateFrom != null && dateFrom.before(today))
                ? Optional.of(validationErrorFactory.createError("ERROR_CODE_5"))
                : Optional.empty();
    }

}