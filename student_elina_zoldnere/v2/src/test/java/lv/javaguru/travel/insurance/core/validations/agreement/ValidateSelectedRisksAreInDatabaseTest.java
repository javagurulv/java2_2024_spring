package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.domain.ClassifierValue;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ValidateSelectedRisksAreInDatabaseTest {

    @Mock
    private AgreementDTO agreementMock;
    @Mock
    private ClassifierValueRepository repositoryMock;
    @Mock
    private ValidationErrorFactory errorFactoryMock;

    @InjectMocks
    private ValidateSelectedRisksAreInDatabase validateRisks;

    @Autowired
    @InjectMocks
    private ValidateSetUpAgreementValuesHelper helper;

    @BeforeEach
    public void setUp() {
        helper.setUpAgreementMockWithValues(agreementMock);
    }

    @Test
    public void validateList_ShouldReturnCorrectResponseWhenOneSelectedRiskIsNotSupported() {
        when(agreementMock.getSelectedRisks()).thenReturn(Arrays.asList("TRAVEL_MEDICAL", "INVALID"));
        when(repositoryMock.findByClassifierTitleAndIc("RISK_TYPE", "TRAVEL_MEDICAL"))
                .thenReturn(Optional.of(new ClassifierValue()));
        when(repositoryMock.findByClassifierTitleAndIc("RISK_TYPE", "INVALID"))
                .thenReturn(Optional.empty());

        ValidationErrorDTO error = mock(ValidationErrorDTO.class);
        lenient().when(errorFactoryMock.buildError(eq("ERROR_CODE_9"), anyList())).thenReturn(error);

        List<ValidationErrorDTO> result = validateRisks.validateList(agreementMock);

        assertEquals(1, result.size());
    }

    @Test
    public void validateList_ShouldReturnCorrectResponseWhenTwoSelectedRisksAreNotSupported() {
        when(agreementMock.getSelectedRisks()).thenReturn(Arrays.asList("INVALID1", "INVALID2"));
        when(repositoryMock.findByClassifierTitleAndIc("RISK_TYPE", "INVALID1"))
                .thenReturn(Optional.empty());
        when(repositoryMock.findByClassifierTitleAndIc("RISK_TYPE", "INVALID2"))
                .thenReturn(Optional.empty());

        ValidationErrorDTO error = mock(ValidationErrorDTO.class);
        lenient().when(errorFactoryMock.buildError(eq("ERROR_CODE_9"), anyList())).thenReturn(error);

        List<ValidationErrorDTO> result = validateRisks.validateList(agreementMock);

        assertEquals(2, result.size());
    }

}