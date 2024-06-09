package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class ValidatePersonLastNameNotNullOrBlank extends PersonFieldValidationImpl {

    @Autowired
    private ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationErrorDTO> validateSingle(AgreementDTO agreement, PersonDTO person) {
        return (person.personLastName() == null || person.personLastName().isBlank())
                ? Optional.of(validationErrorFactory.buildError("ERROR_CODE_2"))
                : Optional.empty();
    }

}
