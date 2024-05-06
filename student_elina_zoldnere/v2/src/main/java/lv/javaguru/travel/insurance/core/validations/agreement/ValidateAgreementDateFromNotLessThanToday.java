package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
class ValidateAgreementDateFromNotLessThanToday extends AgreementFieldValidationImpl {

    @Autowired
    private DateTimeUtil dateTimeUtil;
    @Autowired
    private ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationErrorDTO> validateSingle(AgreementDTO agreement) {
        Date agreementDateFrom = agreement.agreementDateFrom();
        Date agreementDateTo = agreement.agreementDateTo();
        Date currentDate = dateTimeUtil.startOfToday();

        return (agreementDateFrom != null && agreementDateTo != null
                && agreementDateFrom.before(currentDate))
                ? Optional.of(validationErrorFactory.buildError("ERROR_CODE_11"))
                : Optional.empty();
    }

}
