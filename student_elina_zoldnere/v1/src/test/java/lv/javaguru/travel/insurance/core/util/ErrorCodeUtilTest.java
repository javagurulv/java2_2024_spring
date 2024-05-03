package lv.javaguru.travel.insurance.core.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ErrorCodeUtilTest {

    @Mock
    private PropertyResolver resolverMock;

    @InjectMocks
    private ErrorCodeUtil errorCodeUtil;

    @Test
    public void getErrorDescription_ShouldReturnCorrectDescription() {
        String errorCode = "ERROR_CODE";
        String expectedDescription = "Error description";
        when(resolverMock.getPropertyDescription(errorCode)).thenReturn(expectedDescription);

        String actualDescription = errorCodeUtil.getErrorDescription(errorCode);

        assertEquals(expectedDescription, actualDescription);
    }

    @Test
    public void getErrorDescriptionWithPlaceholders_ShouldReturnCorrectDescription() {
        String errorCode = "ERROR_CODE";
        String descriptionWithPlaceholders = "Error description {placeholder}";
        List<Placeholder> placeholders = List.of(new Placeholder("placeholder", "VALUE"));
        when(resolverMock.getPropertyDescription(errorCode)).thenReturn(descriptionWithPlaceholders);

        String actualDescription = errorCodeUtil.getErrorDescription(errorCode, placeholders);

        assertEquals("Error description VALUE", actualDescription);
    }

    @Test
    public void getErrorDescription_ShouldNotReturnDescriptionFromInvalidCode() {
        String errorCode = "INVALID_CODE";
        when(resolverMock.getPropertyDescription(errorCode)).thenReturn(null);

        String actualDescription = errorCodeUtil.getErrorDescription(errorCode);

        assertNull(actualDescription);
    }

}
