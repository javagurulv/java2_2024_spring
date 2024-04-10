package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.ValidationError;

public interface ValidationErrorFactory {
    ValidationError buildError(String errorCode);
}
