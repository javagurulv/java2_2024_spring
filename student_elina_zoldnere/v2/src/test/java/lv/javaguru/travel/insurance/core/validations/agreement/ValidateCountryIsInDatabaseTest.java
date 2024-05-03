package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.domain.ClassifierValue;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.validations.ValidateSetUpAgreementValuesHelper;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ValidateCountryIsInDatabaseTest {

    @Mock
    private AgreementDTO agreementMock;
    @Mock
    private ClassifierValueRepository repositoryMock;
    @Mock
    private ValidationErrorFactory errorFactoryMock;

    @InjectMocks
    private ValidateCountryIsInDatabase validateCountry;

    @Autowired
    @InjectMocks
    private ValidateSetUpAgreementValuesHelper helper;

    @BeforeEach
    public void setUp() {
        helper.setUpAgreementMockWithValues(agreementMock);
    }

    @Test
    public void validateSingle_ShouldReturnCorrectResponseWhenCountryIsNotSupported() {
        when(agreementMock.getCountry()).thenReturn("INVALID");
        when(repositoryMock.findByClassifierTitleAndIc("COUNTRY", "INVALID"))
                .thenReturn(Optional.empty());

        ValidationErrorDTO error = mock(ValidationErrorDTO.class);
        lenient().when(errorFactoryMock.buildError(eq("ERROR_CODE_92"), anyList())).thenReturn(error);

        Optional<ValidationErrorDTO> result = validateCountry.validateSingle(agreementMock);

        assertTrue(result.isPresent());
    }

    @Test
    public void validateSingle_ShouldNotReturnErrorWhenCountryExists() {
        when(repositoryMock.findByClassifierTitleAndIc("COUNTRY", "SPAIN"))
                .thenReturn(Optional.of(new ClassifierValue()));

        Optional<ValidationErrorDTO> result = validateCountry.validateSingle(agreementMock);

        assertFalse(result.isPresent());
    }

}