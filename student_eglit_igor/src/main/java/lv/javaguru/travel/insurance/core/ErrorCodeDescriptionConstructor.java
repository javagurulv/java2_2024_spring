package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class ErrorCodeDescriptionConstructor implements ValidationErrorFactory {
    @Autowired
    private ErrorCodeService errorCodeService;

    @Override
    public ValidationError buildError(String errorCode) {
        var errorCodeDescription = errorCodeService.getErrorCodeDescription(errorCode);
        return new ValidationError(errorCode, errorCodeDescription);
    }
}
