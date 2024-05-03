package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;

import java.util.List;
import java.util.Optional;

public interface AgreementFieldValidation {

    Optional<ValidationErrorDTO> validateSingle(AgreementDTO agreement);

    List<ValidationErrorDTO> validateList(AgreementDTO agreement);

}
