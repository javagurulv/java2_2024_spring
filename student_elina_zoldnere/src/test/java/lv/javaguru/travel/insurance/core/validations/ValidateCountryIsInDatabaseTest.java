package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.domain.ClassifierValue;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ValidateCountryIsInDatabaseTest {

    @Mock
    private TravelCalculatePremiumRequest requestMock;
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