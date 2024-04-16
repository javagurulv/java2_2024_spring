package lv.javaguru.travel.insurance.core.validations;

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
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ValidateSelectedRisksTest {

    @Mock
    private TravelCalculatePremiumRequest requestMock;
    @Mock
    private ClassifierValueRepository repositoryMock;
    @Mock
    private ValidationErrorFactory errorFactoryMock;

    @InjectMocks
    private ValidateSelectedRisks validateRisks;

    @Autowired
    @InjectMocks
    private ValidateHelper helper;

    @BeforeEach
    public void setUp() {
        helper.setUpRequestMockWithAllValues(requestMock);
    }

    @Test
    public void validateSingle_ShouldReturnErrorWhenSelectedRisksIsNull() {
        when(requestMock.getSelectedRisks()).thenReturn(null);
        when(errorFactoryMock.buildError("ERROR_CODE_5"))
                .thenReturn(new ValidationError("ERROR_CODE_5", "Field selectedRisks is empty!"));

        Optional<ValidationError> result = validateRisks.validateSingle(requestMock);

        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_5", result.get().getErrorCode());
        assertEquals("Field selectedRisks is empty!", result.get().getDescription());
    }

    @Test
    public void validateSingle_ShouldReturnErrorWhenSelectedRisksIsEmpty() {
        when(requestMock.getSelectedRisks()).thenReturn(Collections.emptyList());
        when(errorFactoryMock.buildError("ERROR_CODE_5"))
                .thenReturn(new ValidationError("ERROR_CODE_5", "Field selectedRisks is empty!"));

        Optional<ValidationError> result = validateRisks.validateSingle(requestMock);

        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_5", result.get().getErrorCode());
        assertEquals("Field selectedRisks is empty!", result.get().getDescription());
    }

    @Test
    public void validateList_ShouldReturnCorrectResponseWhenOneSelectedRiskIsNotSupported() {
        when(requestMock.getSelectedRisks()).thenReturn(Arrays.asList("TRAVEL_MEDICAL","INVALID"));
        when(repositoryMock.findByClassifierTitleAndIc("RISK_TYPE", "TRAVEL_MEDICAL"))
                .thenReturn(Optional.of(new ClassifierValue()));
        when(repositoryMock.findByClassifierTitleAndIc("RISK_TYPE", "INVALID"))
                .thenReturn(Optional.empty());

        ValidationError error = mock(ValidationError.class);
        lenient().when(errorFactoryMock.buildError(eq("ERROR_CODE_9"), anyList())).thenReturn(error);

        List<ValidationError> result = validateRisks.validateList(requestMock);

        assertEquals(1, result.size());
    }

    @Test
    public void validateList_ShouldReturnCorrectResponseWhenTwoSelectedRisksAreNotSupported() {
        when(requestMock.getSelectedRisks()).thenReturn(Arrays.asList("INVALID1","INVALID2"));
        when(repositoryMock.findByClassifierTitleAndIc("RISK_TYPE", "INVALID1"))
                .thenReturn(Optional.empty());
        when(repositoryMock.findByClassifierTitleAndIc("RISK_TYPE", "INVALID2"))
                .thenReturn(Optional.empty());

        ValidationError error = mock(ValidationError.class);
        lenient().when(errorFactoryMock.buildError(eq("ERROR_CODE_9"), anyList())).thenReturn(error);

        List<ValidationError> result = validateRisks.validateList(requestMock);

        assertEquals(2, result.size());
    }

}