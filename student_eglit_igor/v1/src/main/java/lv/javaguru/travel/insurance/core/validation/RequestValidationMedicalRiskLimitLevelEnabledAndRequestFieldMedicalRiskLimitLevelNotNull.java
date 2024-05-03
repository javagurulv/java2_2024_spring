package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RequestValidationMedicalRiskLimitLevelEnabledAndRequestFieldMedicalRiskLimitLevelNotNull extends RequestValidationIntImpl {

    @Value("${medical.risk.limit.level.enabled:true}")
    private Boolean medicalRiskLimitLevelEnabled;

    @Autowired
    private ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationError> validateSingle(TravelCalculatePremiumRequestV1 request) {
        String errorCode = "ERROR_CODE_14";
        return ((request.getMedicalRiskLimitLevel() == null || request.getMedicalRiskLimitLevel().isBlank())
                && medicalRiskLimitLevelEnabled())
                && request.getSelectedRisks().contains("TRAVEL_MEDICAL")
                ? Optional.of(validationErrorFactory.buildError(errorCode))
                : Optional.empty();
    }

    private boolean medicalRiskLimitLevelEnabled() {
        return medicalRiskLimitLevelEnabled;
    }
}
