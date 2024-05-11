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
class ValidateMedicalRiskLimitLevelIsInDatabase extends PersonFieldValidationImpl {

    @Autowired
    private ClassifierValueRepository classifierValueRepository;
    @Autowired
    private ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationErrorDTO> validateSingle(AgreementDTO agreement, PersonDTO person) {
        return (person.medicalRiskLimitLevel() != null
                && !person.medicalRiskLimitLevel().isBlank())
                ? validateMedicalRiskLimitLevel(person)
                : Optional.empty();
    }

    private Optional<ValidationErrorDTO> validateMedicalRiskLimitLevel(PersonDTO person) {
        String limitLevelIc = person.medicalRiskLimitLevel();
        return classifierValueRepository
                .findByClassifierTitleAndIc("MEDICAL_RISK_LIMIT_LEVEL", limitLevelIc).isPresent()
                ? Optional.empty()
                : Optional.of(buildValidationError(limitLevelIc));
    }

    private ValidationErrorDTO buildValidationError(String limitLevelIc) {
        Placeholder placeholder = new Placeholder("NOT_EXISTING_LIMIT_LEVEL", limitLevelIc);
        return validationErrorFactory.buildError("ERROR_CODE_93", List.of(placeholder));
    }

}
