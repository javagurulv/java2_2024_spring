package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.util.ErrorCodeUtil;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ValidationErrorFactoryTest {

    @Mock
    private ErrorCodeUtil errorCodeUtilMock;

    @InjectMocks
    private ValidationErrorFactory errorFactory;

    @Test
    void buildError_shouldReturnErrorWithDescription() {
        when(errorCodeUtilMock.getErrorDescription("ERROR_CODE"))
                .thenReturn("error description");

        ValidationErrorDTO error = errorFactory.buildError("ERROR_CODE");

        assertThat(error.errorCode()).isEqualTo("ERROR_CODE");
        assertThat(error.description()).isEqualTo("error description");
    }

    @Test
    void buildError_shouldReturnErrorWithDescriptionAndPlaceholders() {
        Placeholder placeholder = new Placeholder("placeholderName", "PH");
        when(errorCodeUtilMock.getErrorDescription("ERROR_CODE", List.of(placeholder)))
                .thenReturn("error description PH");

        ValidationErrorDTO error = errorFactory.buildError("ERROR_CODE", List.of(placeholder));

        assertThat(error.errorCode()).isEqualTo("ERROR_CODE");
        assertThat(error.description()).isEqualTo("error description PH");
    }

}
