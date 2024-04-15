package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.util.PropertyResolver;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ValidationErrorFactoryTest {

    @Mock
    private PropertyResolver propertyResolverMock;

    @InjectMocks
    private ValidationErrorFactory errorFactory;

    @Test
    public void shouldReturnErrorWithDescription(){
        when(propertyResolverMock.getPropertyDescription("ERROR_CODE"))
                .thenReturn("error description");

        ValidationError error = errorFactory.buildError("ERROR_CODE");
        assertEquals("ERROR_CODE", error.getErrorCode());
        assertEquals("error description", error.getDescription());
    }

}
