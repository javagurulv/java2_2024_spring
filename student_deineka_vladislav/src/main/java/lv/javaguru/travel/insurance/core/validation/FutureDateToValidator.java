package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.core.DateTimeService;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
class FutureDateToValidator implements TravelRequestValidation {

    @Autowired
    DateTimeService dateTimeService;
    @Autowired
    ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationErrors> execute(TravelCalculatePremiumRequest request) {
        Date dateTo = request.getAgreementDateTo();
        Date today = dateTimeService.getTodayDateTime();
        return (dateTo != null && dateTo.before(today))
                ? Optional.of(validationErrorFactory.createError("ERROR_CODE_6"))
                : Optional.empty();
    }

}
