package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
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
class ValidatePersonBirthDateNotNullTest {

    @Mock
    private PersonDTO personMock;
    @Mock
    private ValidationErrorFactory errorMock;

    @InjectMocks
    private ValidatePersonBirthDateNotNull validate;

    @Autowired
    @InjectMocks
    private ValidateSetUpPersonValuesHelper helper;

    @BeforeEach
    public void setUp() {
        helper.setUpPersonMockWithValues(personMock);
    }

    @Test
    public void validate_ShouldReturnErrorWhenAgreementDateFromIsNull() {
        when(personMock.getPersonBirthDate()).thenReturn(null);
        when(errorMock.buildError("ERROR_CODE_7"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_7", "Field personBirthDate is empty!"));

        Optional<ValidationErrorDTO> result = validate.validateSingle(personMock);

        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_7", result.get().getErrorCode());
        assertEquals("Field personBirthDate is empty!", result.get().getDescription());
    }

}