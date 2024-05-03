package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
class ValidatePersonBirthDateIsValid extends RequestPersonFieldValidationImpl {

    @Autowired
    private DateTimeUtil dateTimeUtil;
    @Autowired
    private ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationError> validateSingle(TravelCalculatePremiumRequestV1 request) {
        Date birthDate = request.getPersonBirthDate();
        Date currentDate = dateTimeUtil.startOfToday();
        Date minPossibleBirthDate = dateTimeUtil.subtractYears(currentDate, 150);

        return (birthDate != null && (birthDate.after(currentDate) || birthDate.before(minPossibleBirthDate)))
                ? Optional.of(validationErrorFactory.buildError("ERROR_CODE_14"))
                : Optional.empty();
    }

}
