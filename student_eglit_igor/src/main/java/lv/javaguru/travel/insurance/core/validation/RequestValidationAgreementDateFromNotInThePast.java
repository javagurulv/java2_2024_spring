package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.core.ErrorCodeService;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
class RequestValidationAgreementDateFromNotInThePast implements RequestValidationInterface{

    private String errorCode = "ERROR_CODE_5";

    @Autowired
    private ErrorCodeService errorCodeService;

    @Override
    public Optional<ValidationError> executeValidation(TravelCalculatePremiumRequest request) {
        if (request.getAgreementDateTo() == null || request.getAgreementDateFrom() == null) {
            return Optional.empty();
        }
        var errorCodeDescription = errorCodeService.getErrorCodeDescription(errorCode);
        return (request.getAgreementDateFrom().isBefore(java.time.LocalDate.now().plusDays(1)))
                ? Optional.of(new ValidationError(errorCode, errorCodeDescription))
                : Optional.empty();
    }
}
