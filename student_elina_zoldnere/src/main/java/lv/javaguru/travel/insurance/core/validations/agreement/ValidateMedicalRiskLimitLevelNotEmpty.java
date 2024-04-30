package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class ValidateMedicalRiskLimitLevelNotEmpty extends RequestAgreementFieldValidationImpl {

    @Value("${medical.risk.limit.level.enabled:false}")
    private Boolean medicalRiskLimitLevelEnabled;

    @Autowired
    private ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationError> validateSingle(TravelCalculatePremiumRequest request) {
        return (isMedicalRiskEnabled() && containsRisk("TRAVEL_MEDICAL", request)
                && (request.getMedicalRiskLimitLevel() == null || request.getMedicalRiskLimitLevel().isBlank()))
                ? Optional.of(validationErrorFactory.buildError("ERROR_CODE_8"))
                : Optional.empty();
    }

    private boolean isMedicalRiskEnabled() {
        return medicalRiskLimitLevelEnabled;
    }

    private boolean containsRisk(String riskType, TravelCalculatePremiumRequest request) {
        if (request.getSelectedRisks() == null || request.getSelectedRisks().isEmpty()) {
            return false;
        }
        return request.getSelectedRisks().contains(riskType);
    }

}
