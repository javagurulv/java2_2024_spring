package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.util.ErrorCodeUtil;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class ValidationErrorFactoryTest {

    @Mock
    private ErrorCodeUtil errorCodeUtil;

    @InjectMocks
    private ValidationErrorFactory validationErrorFactory;

    @Test
    void shouldReturnValidationErrorDTOWhenErrorCodeIsProvided() {
        String errorCode = "ERROR_CODE";
        String errorDescription = "Error description";
        when(errorCodeUtil.getErrorDescription(errorCode)).thenReturn(errorDescription);

        ValidationErrorDTO result = validationErrorFactory.buildError(errorCode);

        assertEquals(errorCode, result.getErrorCode());
        assertEquals(errorDescription, result.getDescription());
    }

    @Test
    void shouldReturnValidationErrorDTOWhenErrorCodeAndPlaceholdersAreProvided() {
        String errorCode = "ERROR_CODE";
        String errorDescription = "Error description";
        Placeholder placeholder = new Placeholder("key", "value");
        when(errorCodeUtil.getErrorDescription(errorCode, Collections.singletonList(placeholder))).thenReturn(errorDescription);

        ValidationErrorDTO result = validationErrorFactory.buildError(errorCode, Collections.singletonList(placeholder));

        assertEquals(errorCode, result.getErrorCode());
        assertEquals(errorDescription, result.getDescription());
    }
}