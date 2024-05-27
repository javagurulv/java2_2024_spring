package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class ValidateTravelCostNotEmptyWhenTravelCancellationSelected extends PersonFieldValidationImpl {

    @Autowired
    private ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationErrorDTO> validateSingle(AgreementDTO agreement, PersonDTO person) {
        return (containsRisk("TRAVEL_CANCELLATION", agreement) && person.travelCost() == null)
                ? Optional.of(validationErrorFactory.buildError("ERROR_CODE_10"))
                : Optional.empty();
    }

    private boolean containsRisk(String riskType, AgreementDTO agreement) {
        if (agreement.selectedRisks() == null || agreement.selectedRisks().isEmpty()) {
            return false;
        }
        return agreement.selectedRisks().contains(riskType);
    }

}
