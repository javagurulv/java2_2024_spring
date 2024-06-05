package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.core.util.ErrorCodeUtil;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import lv.javaguru.travel.insurance.dto.ValidationErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class ValidationErrorFactory {

    @Autowired
    private ErrorCodeUtil errorCodeUtil;

    ValidationErrors createError(String errorCode) {
        String errorDescription = errorCodeUtil.getErrorDescription(errorCode);
        return new ValidationErrors(errorCode, errorDescription);
    }

    ValidationErrors createError(String errorCode, List<Placeholder> placeholderList){
        String errorDescription = errorCodeUtil.getErrorDescription(errorCode, placeholderList);
        return new ValidationErrors(errorCode, errorDescription);

    }

}
