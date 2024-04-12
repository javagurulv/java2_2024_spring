package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.ValidationError;

public interface ValidationErrorFactory {
    ValidationError buildError(String errorCode);
}
