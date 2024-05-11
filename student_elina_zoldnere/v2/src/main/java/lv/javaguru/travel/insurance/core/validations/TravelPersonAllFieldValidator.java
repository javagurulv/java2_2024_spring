package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Component
class TravelPersonAllFieldValidator {

    @Autowired
    List<PersonFieldValidation> personFieldValidation;

        List<ValidationErrorDTO> collectPersonErrors(AgreementDTO agreement) {
            return agreement.persons().stream()
                    .map(person -> collectEachPersonErrors(agreement, person))
                    .flatMap(List::stream)
                    .toList();
        }

    private List<ValidationErrorDTO> collectEachPersonErrors(AgreementDTO agreement, PersonDTO person) {
        List<ValidationErrorDTO> singlePersonErrors = collectSinglePersonErrors(agreement, person);
        List<ValidationErrorDTO> listPersonErrors = collectListPersonErrors(person);
        return concatenateLists(singlePersonErrors, listPersonErrors);
    }

    private List<ValidationErrorDTO> collectSinglePersonErrors(AgreementDTO agreement, PersonDTO person) {
        return personFieldValidation.stream()
                .map(validation -> validation.validateSingle(agreement, person))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    private List<ValidationErrorDTO> collectListPersonErrors(PersonDTO person) {
        return personFieldValidation.stream()
                .map(validation -> validation.validateList(person))
                .filter((Objects::nonNull))
                .flatMap(Collection::stream)
                .toList();
    }

    private List<ValidationErrorDTO> concatenateLists(List<ValidationErrorDTO> errorsListOne,
                                                      List<ValidationErrorDTO> errorListTwo) {
        return Stream.concat(errorsListOne.stream(), errorListTwo.stream())
                .toList();
    }

}