package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class ValidateTravelCancellationIfEnabled extends AgreementFieldValidationImpl {

    @Value("${feature.tripCancellation.enabled:true}")
    private Boolean isTripCancellationEnabled;

    @Autowired
    private ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationErrorDTO> validateSingle(AgreementDTO agreement) {
        return isTripCancellationEnabled
                ? Optional.empty()
                : validateRisks(agreement);
    }

    private Optional<ValidationErrorDTO> validateRisks(AgreementDTO agreement) {
        return agreement.selectedRisks() != null && agreement.selectedRisks().contains("TRAVEL_CANCELLATION")
                ? Optional.of(validationErrorFactory.buildError("ERROR_CODE_61"))
                : Optional.empty();
    }

}
