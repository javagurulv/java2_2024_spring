package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.core.util.SetUpInstancesHelper;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidatePersonBirthDateIsValidTest {

    @Mock
    private DateTimeUtil dateTimeUtil;
    @Mock
    private ValidationErrorFactory errorMock;

    @InjectMocks
    private ValidatePersonBirthDateIsValid validate;

    private static SetUpInstancesHelper helper;

    @BeforeAll
    static void setUp() {
        helper = new SetUpInstancesHelper();
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("birthDateValue")
    public void validate_ShouldReturnErrorWhenPersonBirthDateIsNotValid(String testName, Date birthDate) {
        AgreementDTO agreement = helper.newAgreementDTO();
        PersonDTO person = new PersonDTO(
                "Jānis", "Bērziņš", birthDate, "LEVEL_10000", Collections.emptyList());
        when(dateTimeUtil.startOfToday()).thenReturn(helper.newDate("2025.03.11"));
        when(dateTimeUtil.subtractYears(any(Date.class), eq(150))).thenReturn(helper.newDate("1875.03.11"));
        when(errorMock.buildError("ERROR_CODE_14"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_14", "PersonBirthDate is not a valid date!"));

        Optional<ValidationErrorDTO> result = validate.validateSingle(agreement, person);

        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_14", result.get().errorCode());
        assertEquals("PersonBirthDate is not a valid date!", result.get().description());
    }

    private static Stream<Arguments> birthDateValue() {
        return Stream.of(
                Arguments.of("birthDate after current date", helper.newDate("2030.03.11")),
                Arguments.of("birthDate less than minimal", helper.newDate("1800.03.11"))
        );
    }

}