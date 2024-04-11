package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ValidateSelectedRisksTest {

    @Mock
    private TravelCalculatePremiumRequest requestMock;
    @Mock
    private ValidationErrorFactory errorMock;

    @InjectMocks
    private ValidateSelectedRisks validate;

    @Autowired
    @InjectMocks
    private ValidateHelper helper;

    @BeforeEach
    public void setUp() {
        helper.setUpRequestMockWithAllValues(requestMock);
    }

    @Test
    public void validate_ShouldReturnErrorWhenSelectedRisksIsNull() {
        when(requestMock.getSelectedRisks()).thenReturn(null);
        when(errorMock.buildError("ERROR_CODE_5"))
                .thenReturn(new ValidationError("ERROR_CODE_5", "Field selectedRisks is empty!"));

        Optional<ValidationError> result = validate.execute(requestMock);

        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_5", result.get().getErrorCode());
        assertEquals("Field selectedRisks is empty!", result.get().getDescription());
    }

    @Test
    public void validate_ShouldReturnErrorWhenSelectedRisksIsEmpty() {
        when(requestMock.getSelectedRisks()).thenReturn(Collections.emptyList());
        when(errorMock.buildError("ERROR_CODE_5"))
                .thenReturn(new ValidationError("ERROR_CODE_5", "Field selectedRisks is empty!"));

        Optional<ValidationError> result = validate.execute(requestMock);

        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_5", result.get().getErrorCode());
        assertEquals("Field selectedRisks is empty!", result.get().getDescription());
    }

}