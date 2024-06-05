package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
class ValidateTravelCostIsValidWhenTravelCancellationSelected extends PersonFieldValidationImpl {

    @Autowired
    private ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationErrorDTO> validateSingle(AgreementDTO agreement, PersonDTO person) {
        return (containsRisk("TRAVEL_CANCELLATION", agreement) && person.travelCost() != null)
                ? validateTravelCost(person)
                : Optional.empty();
    }

    private boolean containsRisk(String riskType, AgreementDTO agreement) {
        if (agreement.selectedRisks() == null || agreement.selectedRisks().isEmpty()) {
            return false;
        }
        return agreement.selectedRisks().contains(riskType);
    }

    private Optional<ValidationErrorDTO> validateTravelCost(PersonDTO person) {
        BigDecimal travelCost = person.travelCost();
        return (travelCost.compareTo(BigDecimal.ZERO) < 0 || travelCost.compareTo(new BigDecimal("1000000")) > 0)
                ? Optional.of(buildValidationError(travelCost))
                : Optional.empty();
    }

    private ValidationErrorDTO buildValidationError(BigDecimal travelCost) {
        Placeholder placeholder = new Placeholder("NOT_SUPPORTED_TRAVEL_COST", travelCost.toString());
        return validationErrorFactory.buildError("ERROR_CODE_94", List.of(placeholder));
    }

}

