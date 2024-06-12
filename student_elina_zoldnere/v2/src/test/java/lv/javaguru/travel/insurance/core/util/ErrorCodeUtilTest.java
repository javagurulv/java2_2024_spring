package lv.javaguru.travel.insurance.core.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ErrorCodeUtilTest {

    @Mock
    private PropertyResolver resolverMock;

    @InjectMocks
    private ErrorCodeUtil errorCodeUtil;

    @Test
    void getErrorDescription_ShouldReturnCorrectDescription() {
        String errorCode = "ERROR_CODE";
        String expectedDescription = "Error description";

        when(resolverMock.getPropertyDescription(errorCode)).thenReturn(expectedDescription);

        String actualDescription = errorCodeUtil.getErrorDescription(errorCode);

        assertThat(actualDescription).isEqualTo(expectedDescription);
    }

    @Test
    void getErrorDescriptionWithPlaceholders_ShouldReturnCorrectDescription() {
        String errorCode = "ERROR_CODE";
        String descriptionWithPlaceholders = "Error description {placeholder}";
        List<Placeholder> placeholders = List.of(new Placeholder("placeholder", "VALUE"));

        when(resolverMock.getPropertyDescription(errorCode)).thenReturn(descriptionWithPlaceholders);

        String actualDescription = errorCodeUtil.getErrorDescription(errorCode, placeholders);

        assertThat(actualDescription).isEqualTo("Error description VALUE");
    }

    @Test
    void getErrorDescription_ShouldNotReturnDescriptionFromInvalidCode() {
        String errorCode = "INVALID_CODE";

        when(resolverMock.getPropertyDescription(errorCode)).thenReturn(null);

        String actualDescription = errorCodeUtil.getErrorDescription(errorCode);

        assertThat(actualDescription).isNull();
    }

}
