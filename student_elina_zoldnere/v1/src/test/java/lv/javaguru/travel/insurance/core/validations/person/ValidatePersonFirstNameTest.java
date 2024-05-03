package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.validations.ValidateSetUpRequestHelper;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.ValidationError;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidatePersonFirstNameTest {

    @Mock
    private TravelCalculatePremiumRequestV1 requestMock;
    @Mock
    private ValidationErrorFactory errorMock;

    @InjectMocks
    private ValidatePersonFirstName validate;

    @Autowired
    @InjectMocks
    private ValidateSetUpRequestHelper helper;

    @BeforeEach
    public void setUp() {
        helper.setUpRequestMockWithAllValues(requestMock);
    }

    @Test
    public void validate_ShouldReturnErrorWhenPersonFirstNameIsNull() {
        when(requestMock.getPersonFirstName()).thenReturn(null);
        when(errorMock.buildError("ERROR_CODE_1"))
                .thenReturn(new ValidationError("ERROR_CODE_1", "Field personFirstName is empty!"));

        Optional<ValidationError> result = validate.validateSingle(requestMock);

        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_1", result.get().getErrorCode());
        assertEquals("Field personFirstName is empty!", result.get().getDescription());
    }

    @Test
    public void validate_ShouldReturnErrorWhenPersonFirstNameIsEmpty() {
        when(requestMock.getPersonFirstName()).thenReturn("");
        when(errorMock.buildError("ERROR_CODE_1"))
                .thenReturn(new ValidationError("ERROR_CODE_1", "Field personFirstName is empty!"));

        Optional<ValidationError> result = validate.validateSingle(requestMock);

        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_1", result.get().getErrorCode());
        assertEquals("Field personFirstName is empty!", result.get().getDescription());
    }

    @Test
    public void validate_ShouldReturnErrorWhenPersonFirstNameIsBlank() {
        when(requestMock.getPersonFirstName()).thenReturn("     ");
        when(errorMock.buildError("ERROR_CODE_1"))
                .thenReturn(new ValidationError("ERROR_CODE_1", "Field personFirstName is empty!"));


        Optional<ValidationError> result = validate.validateSingle(requestMock);

        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_1", result.get().getErrorCode());
        assertEquals("Field personFirstName is empty!", result.get().getDescription());
    }

}