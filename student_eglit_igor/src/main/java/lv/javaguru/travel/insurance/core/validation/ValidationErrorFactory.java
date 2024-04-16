package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ValidationErrorFactory {
    ValidationError buildError(String errorCode);
    ValidationError buildError(String errorCode, List<Placeholder> placeholders);
}
