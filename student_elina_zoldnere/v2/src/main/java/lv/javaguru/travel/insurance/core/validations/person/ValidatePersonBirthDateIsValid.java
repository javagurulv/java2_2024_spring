package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
class ValidatePersonBirthDateIsValid extends PersonFieldValidationImpl {

    @Autowired
    private DateTimeUtil dateTimeUtil;
    @Autowired
    private ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationErrorDTO> validateSingle(AgreementDTO agreement, PersonDTO person) {
        Date birthDate = person.personBirthDate();
        Date currentDate = dateTimeUtil.startOfToday();
        Date minPossibleBirthDate = dateTimeUtil.subtractYears(currentDate, 150);

        return (birthDate != null && (birthDate.after(currentDate) || birthDate.before(minPossibleBirthDate)))
                ? Optional.of(validationErrorFactory.buildError("ERROR_CODE_14"))
                : Optional.empty();
    }

}
