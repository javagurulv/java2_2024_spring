package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.core.util.ErrorCodeUtil;
import lv.javaguru.travel.insurance.dto.ValidationErrors;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidationErrorFactoryTest {

    @Mock
    private ErrorCodeUtil errorCodeUtil;

    @InjectMocks
    private ValidationErrorFactory validationErrorFactory;

    @Test
    public void errorWithDescription() {
        when(errorCodeUtil.getErrorDescription("ERROR_CODE"))
                .thenReturn("error description");
        ValidationErrors validationErrors = validationErrorFactory.createError("ERROR_CODE");
        assertEquals(validationErrors.getErrorCode(), "ERROR_CODE");
        assertEquals(validationErrors.getDescription(), "error description");
    }
}
