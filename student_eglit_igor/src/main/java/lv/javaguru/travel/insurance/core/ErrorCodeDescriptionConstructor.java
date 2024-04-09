package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.core.util.ErrorCodeUtil;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class ErrorCodeDescriptionConstructor implements ValidationErrorFactory {
    @Autowired
    private ErrorCodeUtil errorCodeUtil;

    @Override
    public ValidationError buildError(String errorCode) {
        var errorCodeDescription = errorCodeUtil.getErrorCodeDescription(errorCode);
        return new ValidationError(errorCode, errorCodeDescription);
    }
}
