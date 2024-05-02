package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
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

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidatePersonBirthDateIsValidTest {

    @Mock
    private TravelCalculatePremiumRequestV1 requestMock;
    @Mock
    private DateTimeUtil dateTimeUtil;
    @Mock
    private ValidationErrorFactory errorMock;

    @InjectMocks
    private ValidatePersonBirthDateIsValid validate;

    @Autowired
    @InjectMocks
    private ValidateSetUpRequestHelper helper;

    @BeforeEach
    public void setUp() {
        helper.setUpRequestMockWithAllValues(requestMock);
    }

    @Test
    public void validate_ShouldReturnErrorWhenPersonBirthDateAfterCurrentDate() {
        when(requestMock.getPersonBirthDate()).thenReturn(new Date(2030 - 1900, 2, 11));
        when(dateTimeUtil.startOfToday()).thenReturn(new Date(2025 - 1900, 2, 11));
        when(errorMock.buildError("ERROR_CODE_14"))
                .thenReturn(new ValidationError("ERROR_CODE_14", "PersonBirthDate is not a valid date!"));

        Optional<ValidationError> result = validate.validateSingle(requestMock);

        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_14", result.get().getErrorCode());
        assertEquals("PersonBirthDate is not a valid date!", result.get().getDescription());
    }

    @Test
    public void validate_ShouldReturnErrorWhenPersonBirthDateLessThanMinimal() {
        when(requestMock.getPersonBirthDate()).thenReturn(new Date(1800 - 1900, 2, 11));
        when(dateTimeUtil.startOfToday()).thenReturn(new Date(2024 - 1900, 2, 11));
        when(dateTimeUtil.subtractYears(any(Date.class), eq(150))).thenReturn(new Date(1874 - 1900, 2, 11));
        when(errorMock.buildError("ERROR_CODE_14"))
                .thenReturn(new ValidationError("ERROR_CODE_14", "PersonBirthDate is not a valid date!"));

        Optional<ValidationError> result = validate.validateSingle(requestMock);

        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_14", result.get().getErrorCode());
        assertEquals("PersonBirthDate is not a valid date!", result.get().getDescription());
    }

}