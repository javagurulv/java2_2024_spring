package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.AgreementFieldValidation;

import java.util.List;
import java.util.Optional;

abstract class AgreementFieldValidationImpl implements AgreementFieldValidation {

    @Override
    public Optional<ValidationErrorDTO> validateSingle(AgreementDTO agreement) {
        return Optional.empty();
    }

    @Override
    public List<ValidationErrorDTO> validateList(AgreementDTO agreement) {
        return null;
    }

}
