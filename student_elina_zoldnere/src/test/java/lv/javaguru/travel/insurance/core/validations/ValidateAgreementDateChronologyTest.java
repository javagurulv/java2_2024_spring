package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.ValidateHelper;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ValidateAgreementDateChronologyTest {

    @Mock
    private TravelCalculatePremiumRequest requestMock;

    @InjectMocks
    private ValidateAgreementDateChronology validate;

    @Autowired
    @InjectMocks
    private ValidateHelper helper;

    @BeforeEach
    public void setUp() {
        helper.setUpRequestMockWithAllValues(requestMock);
    }

    @Test
    public void validate_ShouldReturnErrorWhenAgreementDateToIsEqualsAgreementDateFrom() {
        // requestMock.getAgreementDateFrom() returns (new Date (2025 - 1900, 2, 10))
        when(requestMock.getAgreementDateTo()).thenReturn(new Date(2025 - 1900, 2, 10));

        Optional<ValidationError> result = validate.validateAgreementDateChronology(requestMock);

        assertTrue(result.isPresent());
        assertEquals("agreementDateFrom", result.get().getField());
        assertEquals("Must be before agreementDateTo!", result.get().getMessage());
    }

    @Test
    public void validate_ShouldReturnErrorWhenAgreementDateToIsLessThanAgreementDateFrom() {
        // requestMock.getAgreementDateFrom() returns (new Date (2025 - 1900, 2, 10))
        when(requestMock.getAgreementDateTo()).thenReturn(new Date(2025 - 1900, 2, 9));

        Optional<ValidationError> result = validate.validateAgreementDateChronology(requestMock);

        assertTrue(result.isPresent());
        assertEquals("agreementDateFrom", result.get().getField());
        assertEquals("Must be before agreementDateTo!", result.get().getMessage());
    }

}