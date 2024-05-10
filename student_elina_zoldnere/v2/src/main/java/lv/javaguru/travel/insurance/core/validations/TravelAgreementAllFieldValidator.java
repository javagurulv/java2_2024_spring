package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Component
class TravelAgreementAllFieldValidator {

    @Autowired
    List<AgreementFieldValidation> agreementFieldValidation;

    List<ValidationErrorDTO> collectAgreementErrors(AgreementDTO agreement) {
        List<ValidationErrorDTO> singleAgreementErrors = collectSingleAgreementErrors(agreement);
        List<ValidationErrorDTO> listAgreementErrors = collectListAgreementErrors(agreement);
        return concatenateLists(singleAgreementErrors, listAgreementErrors);
    }

    private List<ValidationErrorDTO> collectSingleAgreementErrors(AgreementDTO agreement) {
        return agreementFieldValidation.stream()
                .map(validation -> validation.validateSingle(agreement))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    private List<ValidationErrorDTO> collectListAgreementErrors(AgreementDTO agreement) {
        return agreementFieldValidation.stream()
                .map(validation -> validation.validateList(agreement))
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