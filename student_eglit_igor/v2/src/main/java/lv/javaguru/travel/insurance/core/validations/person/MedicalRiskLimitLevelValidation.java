package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Optional;


@Component
class MedicalRiskLimitLevelValidation
extends TravelPersonFieldValidationImpl {

    @Autowired private ClassifierValueRepository classifierValueRepository;
    @Autowired private ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO agreement, PersonDTO person) {
        return isMedicalRiskLimitLevelNotBlank(person)
                ? validateMedicalRiskLimitLevel(person)
                : Optional.empty();
    }

    private Optional<ValidationErrorDTO>  validateMedicalRiskLimitLevel(PersonDTO person) {
        String medicalRiskLimitLevelIc = person.medicalRiskLimitLevel();
        return classifierValueRepository.findByClassifierTitleAndIc("MEDICAL_RISK_LIMIT_LEVEL", medicalRiskLimitLevelIc).isPresent()
                ? Optional.empty()
                : Optional.of(buildValidationError(medicalRiskLimitLevelIc));
    }

    private ValidationErrorDTO buildValidationError(String medicalRiskLimitLevelIc) {
        Placeholder placeholder = new Placeholder("NOT_EXISTING_MEDICAL_RISK_LIMIT_LEVEL", medicalRiskLimitLevelIc);
        return errorFactory.buildError("ERROR_CODE_15", List.of(placeholder));
    }

    private boolean isMedicalRiskLimitLevelNotBlank(PersonDTO person) {
        return person.medicalRiskLimitLevel() !=null && !person.medicalRiskLimitLevel().isBlank();
    }
}
