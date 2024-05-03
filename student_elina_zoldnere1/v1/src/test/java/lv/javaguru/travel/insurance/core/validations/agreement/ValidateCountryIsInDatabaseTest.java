package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.domain.ClassifierValue;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidateCountryIsInDatabaseTest {

    @Mock
    private TravelCalculatePremiumRequestV1 requestMock;
    @Mock
    private ClassifierValueRepository repositoryMock;
    @Mock
    private ValidationErrorFactory errorFactoryMock;

    @InjectMocks
    private ValidateCountryIsInDatabase validateCountry;

    @Autowired
    @InjectMocks
    private ValidateSetUpRequestHelper helper;

    @BeforeEach
    public void setUp() {
        helper.setUpRequestMockWithAllValues(requestMock);
    }

    @Test
    public void validateSingle_ShouldReturnCorrectResponseWhenCountryIsNotSupported() {
        when(requestMock.getCountry()).thenReturn("INVALID");
        when(repositoryMock.findByClassifierTitleAndIc("COUNTRY", "INVALID"))
                .thenReturn(Optional.empty());

        ValidationError error = mock(ValidationError.class);
        lenient().when(errorFactoryMock.buildError(eq("ERROR_CODE_92"), anyList())).thenReturn(error);

        Optional<ValidationError> result = validateCountry.validateSingle(requestMock);

        assertTrue(result.isPresent());
    }

    @Test
    public void validateSingle_ShouldNotReturnErrorWhenCountryExists() {
        when(repositoryMock.findByClassifierTitleAndIc("COUNTRY", "SPAIN"))
                .thenReturn(Optional.of(new ClassifierValue()));

        Optional<ValidationError> result = validateCountry.validateSingle(requestMock);

        assertFalse(result.isPresent());
    }

}