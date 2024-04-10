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

        Optional<ValidationError> result = validate.execute(requestMock);

        assertTrue(result.isPresent());
        assertEquals("selectedRisks", result.get().getField());
        assertEquals("Must not be empty!", result.get().getMessage());
    }

    @Test
    public void validate_ShouldReturnErrorWhenSelectedRisksIsEmpty() {
        when(requestMock.getSelectedRisks()).thenReturn(Collections.emptyList());

        Optional<ValidationError> result = validate.execute(requestMock);

        assertTrue(result.isPresent());
        assertEquals("selectedRisks", result.get().getField());
        assertEquals("Must not be empty!", result.get().getMessage());
    }

}