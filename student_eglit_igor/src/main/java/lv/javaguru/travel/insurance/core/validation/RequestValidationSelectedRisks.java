package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.core.ErrorCodeService;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class RequestValidationSelectedRisks implements RequestValidationInterface {

    private String errorCode = "ERROR_CODE_8";

    @Autowired
    private ErrorCodeService errorCodeService;

    @Override
    public Optional<ValidationError> executeValidation(TravelCalculatePremiumRequest request) {
        var errorCodeDescription = errorCodeService.getErrorCodeDescription(errorCode);
        return (request.getInsuranceRisks() == null || request.getInsuranceRisks().isEmpty())
                ? Optional.of(new ValidationError("ERROR_CODE_8", "Field selected_risks is empty!"))
                : Optional.empty();
    }
}
