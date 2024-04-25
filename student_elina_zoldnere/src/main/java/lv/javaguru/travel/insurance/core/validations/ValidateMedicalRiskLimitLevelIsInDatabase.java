package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
class ValidateMedicalRiskLimitLevelIsInDatabase extends RequestFieldValidationImpl {

    @Value("${medical.risk.limit.level.enabled:false}")
    private Boolean medicalRiskLimitLevelEnabled;

    @Autowired
    private ClassifierValueRepository classifierValueRepository;
    @Autowired
    private ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationError> validateSingle(TravelCalculatePremiumRequest request) {
        return (isMedicalRiskLevelEnabled() && request.getMedicalRiskLimitLevel() != null
                && !request.getMedicalRiskLimitLevel().isBlank())
                ? validateMedicalRiskLimitLevel(request)
                : Optional.empty();
    }

    private boolean isMedicalRiskLevelEnabled() {
        return medicalRiskLimitLevelEnabled;
    }

    private Optional<ValidationError> validateMedicalRiskLimitLevel(TravelCalculatePremiumRequest request) {
        String limitLevelIc = request.getMedicalRiskLimitLevel();
        return classifierValueRepository
                .findByClassifierTitleAndIc("MEDICAL_RISK_LIMIT_LEVEL", limitLevelIc).isPresent()
                ? Optional.empty()
                : Optional.of(buildValidationError(limitLevelIc));
    }

    private ValidationError buildValidationError(String limitLevelIc) {
        Placeholder placeholder = new Placeholder("NOT_EXISTING_LIMIT_LEVEL", limitLevelIc);
        return validationErrorFactory.buildError("ERROR_CODE_93", List.of(placeholder));
    }

}
