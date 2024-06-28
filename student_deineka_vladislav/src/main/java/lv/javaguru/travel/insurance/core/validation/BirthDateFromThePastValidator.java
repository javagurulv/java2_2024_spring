package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
class BirthDateFromThePastValidator extends TravelRequestValidationImpl{

    @Autowired
    private DateTimeUtil dateTimeUtil;
    @Autowired
    private ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationErrors> validate(TravelCalculatePremiumRequest request) {
        Date personBirthDate = request.getPersonBirthDate();
        Date today = dateTimeUtil.getTodayDateTime();
        return (personBirthDate != null && personBirthDate.after(today))
                ? Optional.of(validationErrorFactory.createError("ERROR_CODE_12"))
                : Optional.empty();
    }
}
