package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
class SelectedRiskValidator extends TravelRequestValidationImpl{

    @Autowired
    private ClassifierValueRepository classifierValueRepository;
    @Autowired
    private ValidationErrorFactory validationErrorFactory;

    @Override
    public List<ValidationErrors> listValidation(TravelCalculatePremiumRequest premiumRequest) {
        return premiumRequest.getSelectedRisks() != null
                ? selectedRiskValidation(premiumRequest)
                : List.of();
    }

    private List<ValidationErrors> selectedRiskValidation(TravelCalculatePremiumRequest premiumRequest) {
        return premiumRequest.getSelectedRisks().stream()
                .map(this::riskIcValidation)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private Optional<ValidationErrors> riskIcValidation(String riskIc) {
        return !existInDatabase(riskIc)
                ? Optional.of(createValidationError(riskIc))
                : Optional.empty();
    }

    private boolean existInDatabase(String riskIc) {
        return classifierValueRepository.findByClassifierTitleAndIc("RISK_TYPE", riskIc).isPresent();
    }

    private ValidationErrors createValidationError(String riskIc) {
        Placeholder placeholder = new Placeholder("NOT_EXISTING_RISK_TYPE", riskIc);
        return validationErrorFactory.createError("ERROR_CODE_9", List.of(placeholder));
    }

}
