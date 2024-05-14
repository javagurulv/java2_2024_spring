package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.core.ErrorCodeUtil;
import lv.javaguru.travel.insurance.dto.ValidationErrors;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidationErrorFactory {

    @Autowired
    private ErrorCodeUtil errorCodeUtil;

    ValidationErrors createError(String errorCode) {
        String errorDescription = errorCodeUtil.getErrorDescription(errorCode);
        return new ValidationErrors(errorCode, errorDescription);
    }
}
