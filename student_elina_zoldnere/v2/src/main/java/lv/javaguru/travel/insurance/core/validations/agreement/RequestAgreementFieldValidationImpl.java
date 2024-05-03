package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.RequestAgreementFieldValidation;

import java.util.List;
import java.util.Optional;

abstract class RequestAgreementFieldValidationImpl implements RequestAgreementFieldValidation {

    @Override
    public Optional<ValidationErrorDTO> validateSingle(AgreementDTO agreement) {
        return Optional.empty();
    }

    @Override
    public List<ValidationErrorDTO> validateList(AgreementDTO agreement) {
        return null;
    }

}
