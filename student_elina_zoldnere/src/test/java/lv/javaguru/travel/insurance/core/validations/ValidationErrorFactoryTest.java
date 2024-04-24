package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.util.ErrorCodeUtil;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import lv.javaguru.travel.insurance.dto.ValidationError;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidationErrorFactoryTest {

    @Mock
    private ErrorCodeUtil errorCodeUtilMock;

    @InjectMocks
    private ValidationErrorFactory errorFactory;

    @Test
    public void shouldReturnErrorWithDescription() {
        when(errorCodeUtilMock.getErrorDescription("ERROR_CODE"))
                .thenReturn("error description");

        ValidationError error = errorFactory.buildError("ERROR_CODE");
        assertEquals("ERROR_CODE", error.getErrorCode());
        assertEquals("error description", error.getDescription());
    }

    @Test
    public void shouldReturnErrorWithDescriptionAndPlaceholders() {
        Placeholder placeholder = new Placeholder("placeholderName", "PH");
        when(errorCodeUtilMock.getErrorDescription("ERROR_CODE", List.of(placeholder)))
                .thenReturn("error description PH");

        ValidationError error = errorFactory.buildError("ERROR_CODE", List.of(placeholder));
        assertEquals("ERROR_CODE", error.getErrorCode());
        assertEquals("error description PH", error.getDescription());
    }

}
