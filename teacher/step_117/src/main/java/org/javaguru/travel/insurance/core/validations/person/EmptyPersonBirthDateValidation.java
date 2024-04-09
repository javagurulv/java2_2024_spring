package org.javaguru.travel.insurance.core.validations.person;

import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class EmptyPersonBirthDateValidation extends TravelPersonFieldValidationImpl {

    @Autowired private ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationErrorDTO> validate(PersonDTO person) {
        return (personBirthDateIsNull(person))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_11"))
                : Optional.empty();
    }

    private boolean personBirthDateIsNull(PersonDTO person) {
        return person.getPersonBirthDate() == null;
    }

}
