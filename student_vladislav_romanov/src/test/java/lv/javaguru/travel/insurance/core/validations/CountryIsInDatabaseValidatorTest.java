package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.domain.ClassifierValue;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class CountryIsInDatabaseValidatorTest {


    @Mock
    private ClassifierValueRepository classifierValueRepository;
    @Mock
    private ValidationErrorFactory validationErrorFactory;
    @InjectMocks
    private CountryInDatabaseValidator validation;

    @Test
    void countryIsNotInDatabase() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getCountry()).thenReturn("CHINA");
        ValidationError validationError = mock(ValidationError.class);
        when(classifierValueRepository.findByClassifierTitleAndIc("COUNTRY", "CHINA"))
                .thenReturn(Optional.empty());

        when(validationErrorFactory.buildError(eq(11), anyList())).thenReturn(validationError);
        Optional<ValidationError> error = validation.validate(request);

        assertTrue(error.isPresent());
        assertSame(error.get(), validationError);
    }

    @Test
    void countryIsInDatabase() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getCountry()).thenReturn("GREECE");
        when(classifierValueRepository.findByClassifierTitleAndIc("COUNTRY", "GREECE"))
                .thenReturn(Optional.of(mock(ClassifierValue.class)));
        Optional<ValidationError> errors = validation.validate(request);
        assertTrue(errors.isEmpty());
    }
}
