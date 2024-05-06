package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class DateFromLessThenDateToValidation
        extends TravelAgreementFieldValidationImpl {

    @Autowired private ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO agreement){
        return (dateFromIsAfterDateTo(agreement))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_7"))
                : Optional.empty();
    }

    private boolean dateFromIsAfterDateTo(AgreementDTO agreement) {
        return agreement.getAgreementDateFrom().isAfter(agreement.getAgreementDateTo());
    }
}

