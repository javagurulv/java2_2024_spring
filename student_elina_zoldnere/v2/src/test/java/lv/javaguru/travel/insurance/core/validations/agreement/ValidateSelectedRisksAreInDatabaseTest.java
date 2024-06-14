package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTOBuilder;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.domain.ClassifierValue;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidateSelectedRisksAreInDatabaseTest {

    @Mock
    private ClassifierValueRepository repositoryMock;
    @Mock
    private ValidationErrorFactory errorFactoryMock;

    @InjectMocks
    private ValidateSelectedRisksAreInDatabase validate;

    @ParameterizedTest(name = "{0}")
    @MethodSource("riskTypeValues")
    public void validateList_ShouldReturnCorrectResponseWhenSelectedRisksAreNotSupported(
            String testName, String firstRisk, String secondRisk, int expectedSize) {
        AgreementDTO agreement = AgreementDTOBuilder.createAgreement()
                .withSelectedRisks(List.of(firstRisk, secondRisk))
                .build();

        Mockito.lenient().when(repositoryMock.findByClassifierTitleAndIc("RISK_TYPE", "TRAVEL_MEDICAL"))
                .thenReturn(Optional.of(new ClassifierValue()));
        when(repositoryMock.findByClassifierTitleAndIc("RISK_TYPE", "INVALID"))
                .thenReturn(Optional.empty());
        lenient().when(errorFactoryMock.buildError(eq("ERROR_CODE_9"), anyList()))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_9", "description"));

        List<ValidationErrorDTO> result = validate.validateList(agreement);

        assertThat(result).hasSize(expectedSize);
    }

    private static Stream<Arguments> riskTypeValues() {
        return Stream.of(
                Arguments.of("one selectedRisk is invalid", "TRAVEL_MEDICAL", "INVALID", 1),
                Arguments.of("two selectedRisks are invalid", "INVALID", "INVALID", 2)
        );
    }

    @Test
    void validateList_ShouldNotReturnErrorWhenSelectedRisksAreInDatabase() {
        AgreementDTO agreement = AgreementDTOBuilder.createAgreement()
                .withSelectedRisks(List.of("TRAVEL_MEDICAL", "TRAVEL_CANCELLATION"))
                .build();

        when(repositoryMock.findByClassifierTitleAndIc(any(), any()))
                .thenReturn(Optional.of(new ClassifierValue()));

        List<ValidationErrorDTO> result = validate.validateList(agreement);

        assertThat(result).isEmpty();
        verifyNoInteractions(errorFactoryMock);
    }

}