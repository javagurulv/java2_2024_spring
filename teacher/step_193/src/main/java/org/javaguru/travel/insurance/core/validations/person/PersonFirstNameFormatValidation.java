package org.javaguru.travel.insurance.core.validations.person;

import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.javaguru.travel.insurance.core.util.Placeholder;
import org.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
class PersonFirstNameFormatValidation extends TravelPersonFieldValidationImpl {

    @Autowired private ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO agreement, PersonDTO person) {
        if (!isPersonFirstNameNullOrBlank(person) && !isValidFormat(person)) {
            Placeholder placeholder = new Placeholder("PERSON_FIRST_NAME", person.getPersonFirstName());
            return Optional.of(errorFactory.buildError("ERROR_CODE_22", List.of(placeholder)));
        } else {
            return Optional.empty();
        }
    }

    private boolean isPersonFirstNameNullOrBlank(PersonDTO person) {
        return person.getPersonFirstName() == null || person.getPersonFirstName().isBlank();
    }

    private boolean isValidFormat(PersonDTO person) {
        String patternString = "^[A-Za-z]+([ -][A-Za-z]+)*$";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(person.getPersonFirstName());
        return matcher.matches();
    }

}
