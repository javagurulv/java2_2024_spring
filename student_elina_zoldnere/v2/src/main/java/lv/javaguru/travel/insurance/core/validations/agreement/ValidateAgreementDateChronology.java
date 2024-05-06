package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
class ValidateAgreementDateChronology extends AgreementFieldValidationImpl {

    @Autowired
    private ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationErrorDTO> validateSingle(AgreementDTO agreement) {
        Date agreementDateFrom = agreement.agreementDateFrom();
        Date agreementDateTo = agreement.agreementDateTo();

        return (agreementDateFrom != null && agreementDateTo != null
                && !agreementDateFrom.before(agreementDateTo))
                ? Optional.of(validationErrorFactory.buildError("ERROR_CODE_13"))
                : Optional.empty();
    }

}
