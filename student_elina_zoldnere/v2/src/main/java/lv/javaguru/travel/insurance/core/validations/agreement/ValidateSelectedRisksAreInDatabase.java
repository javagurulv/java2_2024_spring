package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class ValidateSelectedRisksAreInDatabase extends AgreementFieldValidationImpl {

    @Autowired
    private ClassifierValueRepository classifierValueRepository;
    @Autowired
    private ValidationErrorFactory validationErrorFactory;

    @Override
    public List<ValidationErrorDTO> validateList(AgreementDTO agreement) {
        return agreement.selectedRisks() != null
                ? validateRisks(agreement)
                : List.of();
    }

    private List<ValidationErrorDTO> validateRisks(AgreementDTO agreement) {
        return agreement.selectedRisks().stream()
                .filter(risk -> !isInDatabase(risk))
                .map(this::buildValidationError)
                .toList();
    }

    private boolean isInDatabase(String riskIc) {
        return classifierValueRepository.findByClassifierTitleAndIc("RISK_TYPE", riskIc).isPresent();
    }

    private ValidationErrorDTO buildValidationError(String riskIc) {
        Placeholder placeholder = new Placeholder("NOT_EXISTING_RISK_TYPE", riskIc);
        return validationErrorFactory.buildError("ERROR_CODE_91", List.of(placeholder));
    }

}
