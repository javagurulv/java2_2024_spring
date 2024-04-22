package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.core.domain.ClassifierValue;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RequestValidationCountryTest {
    @Mock
    private ClassifierValueRepository classifierValueRepository;

    @Mock
    private ValidationErrorFactory errorFactory;
    @InjectMocks
    private RequestValidationCountry validator;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldReturnEmptyWhenCountryExists() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setCountry("Latvia");

        when(classifierValueRepository.findByClassifierTitleAndIc("COUNTRY", "Latvia"))
                .thenReturn(Optional.of(new ClassifierValue()));

        Optional<ValidationError> result = validator.validateReq(request);

        assertTrue(result.isEmpty());
    }

    @Test
    public void shouldReturnErrorWhenCountryDoesNotExist() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getCountry()).thenReturn("INVALID");
        when(classifierValueRepository.findByClassifierTitleAndIc("COUNTRY", "INVALID"))
                .thenReturn(Optional.empty());

        ValidationError error = mock(ValidationError.class);
        lenient().when(errorFactory.buildError(eq("ERROR_CODE_11"), anyList())).thenReturn(error);

        Optional<ValidationError> result = validator.validateReq(request);

        assertTrue(result.isPresent());
    }
    @Test
    public void shouldReturnEmptyWhenCountryIsNull() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();

        Optional<ValidationError> result = validator.validateReq(request);

        assertTrue(result.isEmpty());
    }
}