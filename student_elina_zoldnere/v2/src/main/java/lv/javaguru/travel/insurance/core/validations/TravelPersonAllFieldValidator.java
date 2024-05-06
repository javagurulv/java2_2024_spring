package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
class TravelPersonAllFieldValidator {

    @Autowired
    List<PersonFieldValidation> personFieldValidation;

    List<ValidationErrorDTO> collectPersonErrors(List<PersonDTO> persons) {
        return persons.stream()
                .map(this::collectEachPersonErrors)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private List<ValidationErrorDTO> collectEachPersonErrors(PersonDTO person) {
        List<ValidationErrorDTO> singlePersonErrors = collectSinglePersonErrors(person);
        List<ValidationErrorDTO> listPersonErrors = collectListPersonErrors(person);
        return concatenateLists(singlePersonErrors, listPersonErrors);
    }

    private List<ValidationErrorDTO> collectSinglePersonErrors(PersonDTO person) {
        return personFieldValidation.stream()
                .map(validation -> validation.validateSingle(person))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private List<ValidationErrorDTO> collectListPersonErrors(PersonDTO person) {
        return personFieldValidation.stream()
                .map(validation -> validation.validateList(person))
                .filter((Objects::nonNull))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<ValidationErrorDTO> concatenateLists(List<ValidationErrorDTO> errorsListOne,
                                                      List<ValidationErrorDTO> errorListTwo) {
        return Stream.concat(errorsListOne.stream(), errorListTwo.stream())
                .collect(Collectors.toList());
    }

}