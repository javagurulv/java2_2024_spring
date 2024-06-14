package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class ValidateMedicalRiskLimitLevelNotEmpty extends PersonFieldValidationImpl {

    @Value("${medical.risk.limit.level.enabled:false}")
    private Boolean medicalRiskLimitLevelEnabled;

    @Autowired
    private ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationErrorDTO> validateSingle(AgreementDTO agreement, PersonDTO person) {
        return (isMedicalRiskEnabled() && containsRisk("TRAVEL_MEDICAL", agreement)
                && (person.medicalRiskLimitLevel() == null || person.medicalRiskLimitLevel().isBlank()))
                ? Optional.of(validationErrorFactory.buildError("ERROR_CODE_8"))
                : Optional.empty();
    }

    private boolean isMedicalRiskEnabled() {
        return medicalRiskLimitLevelEnabled;
    }

    private boolean containsRisk(String riskType, AgreementDTO agreement) {
        if (agreement.selectedRisks() == null || agreement.selectedRisks().isEmpty()) {
            return false;
        }
        return agreement.selectedRisks().contains(riskType);
    }

}
