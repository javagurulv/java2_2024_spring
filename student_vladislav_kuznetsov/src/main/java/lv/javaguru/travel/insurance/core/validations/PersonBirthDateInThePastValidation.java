package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.util.DateTimeService;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
class PersonBirthDateInThePastValidation extends TravelRequestValidationImpl {

    @Autowired
    private DateTimeService dateTimeUtil;
    @Autowired private ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        Date personBirthDate = request.getPersonBirthDate();
        Date currentDateTime = dateTimeUtil.getCurrentDateTime();
        return (personBirthDate != null && personBirthDate.after(currentDateTime))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_12"))
                : Optional.empty();
    }

}