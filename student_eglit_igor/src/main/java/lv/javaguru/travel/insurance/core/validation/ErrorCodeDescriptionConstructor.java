package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.core.util.ErrorCodeUtil;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class ErrorCodeDescriptionConstructor implements ValidationErrorFactory {
    @Autowired
    private ErrorCodeUtil errorCodeUtil;

    @Override
    public ValidationError buildError(String errorCode) {
        var errorCodeDescription = errorCodeUtil.getErrorCodeDescription(errorCode);
        return new ValidationError(errorCode, errorCodeDescription);
    }
    @Override
    public ValidationError buildError(String errorCode, List<Placeholder> placeholders) {
        var errorCodeDescription = errorCodeUtil.getErrorCodeDescription(errorCode, placeholders);
        return new ValidationError(errorCode, errorCodeDescription);
    }
}
