package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
class ValidateSelectedRisksAreInDatabase extends RequestAgreementFieldValidationImpl {

    @Autowired
    private ClassifierValueRepository classifierValueRepository;
    @Autowired
    private ValidationErrorFactory validationErrorFactory;

    @Override
    public List<ValidationError> validateList(TravelCalculatePremiumRequestV1 request) {
        return request.getSelectedRisks() != null
                ? validateRisks(request)
                : List.of();
    }

    private List<ValidationError> validateRisks(TravelCalculatePremiumRequestV1 request) {
        return request.getSelectedRisks().stream()
                .filter(risk -> !isInDatabase(risk))
                .map(this::buildValidationError)
                .collect(Collectors.toList());
    }

    private boolean isInDatabase(String riskIc) {
        return classifierValueRepository.findByClassifierTitleAndIc("RISK_TYPE", riskIc).isPresent();
    }

    private ValidationError buildValidationError(String riskIc) {
        Placeholder placeholder = new Placeholder("NOT_EXISTING_RISK_TYPE", riskIc);
        return validationErrorFactory.buildError("ERROR_CODE_91", List.of(placeholder));
    }

}
