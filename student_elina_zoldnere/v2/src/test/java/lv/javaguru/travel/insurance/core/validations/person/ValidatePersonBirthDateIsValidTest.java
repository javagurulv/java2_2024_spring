package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
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
    private PersonDTO personMock;
    @Mock
    private DateTimeUtil dateTimeUtil;
    @Mock
    private ValidationErrorFactory errorMock;

    @InjectMocks
    private ValidatePersonBirthDateIsValid validate;

    @Autowired
    @InjectMocks
    private ValidateSetUpPersonValuesHelper helper;

    @BeforeEach
    public void setUp() {
        helper.setUpPersonMockWithValues(personMock);
    }

    @Test
    public void validate_ShouldReturnErrorWhenPersonBirthDateAfterCurrentDate() {
        when(personMock.getPersonBirthDate()).thenReturn(new Date(2030 - 1900, 2, 11));
        when(dateTimeUtil.startOfToday()).thenReturn(new Date(2025 - 1900, 2, 11));
        when(errorMock.buildError("ERROR_CODE_14"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_14", "PersonBirthDate is not a valid date!"));

        Optional<ValidationErrorDTO> result = validate.validateSingle(personMock);

        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_14", result.get().getErrorCode());
        assertEquals("PersonBirthDate is not a valid date!", result.get().getDescription());
    }

    @Test
    public void validate_ShouldReturnErrorWhenPersonBirthDateLessThanMinimal() {
        when(personMock.getPersonBirthDate()).thenReturn(new Date(1800 - 1900, 2, 11));
        when(dateTimeUtil.startOfToday()).thenReturn(new Date(2024 - 1900, 2, 11));
        when(dateTimeUtil.subtractYears(any(Date.class), eq(150))).thenReturn(new Date(1874 - 1900, 2, 11));
        when(errorMock.buildError("ERROR_CODE_14"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_14", "PersonBirthDate is not a valid date!"));

        Optional<ValidationErrorDTO> result = validate.validateSingle(personMock);

        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_14", result.get().getErrorCode());
        assertEquals("PersonBirthDate is not a valid date!", result.get().getDescription());
    }

}