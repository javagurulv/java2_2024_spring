package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.PersonFieldValidation;

import java.util.List;
import java.util.Optional;

abstract class PersonFieldValidationImpl implements PersonFieldValidation {

    @Override
    public Optional<ValidationErrorDTO> validateSingle(AgreementDTO agreement, PersonDTO person) {
        return Optional.empty();
    }

    @Override
    public List<ValidationErrorDTO> validateList(PersonDTO person) {
        return null;
    }

}
