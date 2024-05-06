package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidatePersonLastNameTest {

    @Mock
    private ValidationErrorFactory errorMock;

    @InjectMocks
    private ValidatePersonLastName validate;

    @ParameterizedTest(name = "{0}")
    @MethodSource("lastNameValue")
    public void validate_ShouldReturnErrorWhenPersonLastNameIsNotValid(String testName, String lastName) {
        PersonDTO person = new PersonDTO
                ("Jānis", lastName, new Date(1990 - 1900, 0, 1), Collections.emptyList());
        when(errorMock.buildError("ERROR_CODE_2"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_2", "Field personLastName is empty!"));

        Optional<ValidationErrorDTO> result = validate.validateSingle(person);

        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_2", result.get().errorCode());
        assertEquals("Field personLastName is empty!", result.get().description());
    }

    private static Stream<Arguments> lastNameValue() {
        return Stream.of(
                Arguments.of("personLastName null", null),
                Arguments.of("personLastName empty", ""),
                Arguments.of("personLastName blank", "     ")
        );
    }

}