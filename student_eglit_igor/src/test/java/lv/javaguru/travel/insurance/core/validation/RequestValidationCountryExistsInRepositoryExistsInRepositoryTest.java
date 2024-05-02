package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.core.domain.ClassifierValue;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RequestValidationCountryExistsInRepositoryExistsInRepositoryTest {
    @Mock
    private ClassifierValueRepository classifierValueRepository;

    @Mock
    private ValidationErrorFactory errorFactory;
    @InjectMocks
    private RequestValidationCountryExistsInRepository validator;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldReturnEmptyWhenCountryExists() {
        TravelCalculatePremiumRequestV1 request = new TravelCalculatePremiumRequestV1();
        request.setCountry("Latvia");

        when(classifierValueRepository.findByClassifierTitleAndIc("COUNTRY", "Latvia"))
                .thenReturn(Optional.of(new ClassifierValue()));

        Optional<ValidationError> result = validator.validateSingle(request);

        assertTrue(result.isEmpty());
    }

    @Test
    public void shouldReturnErrorWhenCountryDoesNotExist() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getCountry()).thenReturn("INVALID");
        when(classifierValueRepository.findByClassifierTitleAndIc("COUNTRY", "INVALID"))
                .thenReturn(Optional.empty());

        ValidationError error = mock(ValidationError.class);
        lenient().when(errorFactory.buildError(eq("ERROR_CODE_11"), anyList())).thenReturn(error);

        Optional<ValidationError> result = validator.validateSingle(request);

        assertTrue(result.isPresent());
    }
    @Test
    public void shouldReturnEmptyWhenCountryIsNull() {
        TravelCalculatePremiumRequestV1 request = new TravelCalculatePremiumRequestV1();

        Optional<ValidationError> result = validator.validateSingle(request);

        assertTrue(result.isEmpty());
    }
}