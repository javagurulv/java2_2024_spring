package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
class RequestValidationMedicalRiskLimitLevelExists
        extends RequestValidationIntImpl {
    @Autowired
    private ValidationErrorFactory errorFactory;
    @Autowired
    private ClassifierValueRepository classifierValueRepository;

    @Override
    public Optional<ValidationError> validateSingle(TravelCalculatePremiumRequestV1 request) {
        return (request.getMedicalRiskLimitLevel() != null && !request.getMedicalRiskLimitLevel().isBlank())
                ? validateMedicalRiskLimitLevelIc(request)
                : Optional.empty();
    }

    private Optional<ValidationError> validateMedicalRiskLimitLevelIc(TravelCalculatePremiumRequestV1 request) {
        return !existInDatabase(request.getMedicalRiskLimitLevel())
                ? Optional.of(buildValidationError(request.getMedicalRiskLimitLevel()))
                : Optional.empty();
    }

    private ValidationError buildValidationError(String medicalRiskLimitLevelIc) {
        Placeholder placeholder = new Placeholder("NOT_EXISTING_MEDICAL_RISK_LIMIT_LEVEL", medicalRiskLimitLevelIc);
        return errorFactory.buildError("ERROR_CODE_15", List.of(placeholder));
    }

    private boolean existInDatabase(String medicalRiskLimitLevelIc) {
        return classifierValueRepository.findByClassifierTitleAndIc("MEDICAL_RISK_LIMIT_LEVEL", medicalRiskLimitLevelIc).isPresent();
    }

}
