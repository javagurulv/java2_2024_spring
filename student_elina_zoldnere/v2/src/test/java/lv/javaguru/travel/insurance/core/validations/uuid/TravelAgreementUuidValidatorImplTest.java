package lv.javaguru.travel.insurance.core.validations.uuid;

import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.domain.entities.AgreementEntity;
import lv.javaguru.travel.insurance.core.repositories.entities.AgreementEntityRepository;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelAgreementUuidValidatorImplTest {

    @Mock
    private ValidationErrorFactory errorFactoryMock;
    @Mock
    private AgreementEntityRepository agreementRepositoryMock;

    @InjectMocks
    private TravelAgreementUuidValidatorImpl validator;

    @ParameterizedTest(name = "{0}")
    @MethodSource("uuidValue")
    void validate_ShouldReturnErrorWhenUuidIsNullOrBlank(String testName, String uuid) {
        when(errorFactoryMock.buildError("ERROR_CODE_21"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_21", "DESCRIPTION"));

        List<ValidationErrorDTO> result = validator.validate(uuid);

        assertThat(result)
                .hasSize(1)
                .extracting("errorCode", "description")
                .containsExactly(
                        Assertions.tuple("ERROR_CODE_21", "DESCRIPTION"));
    }

    private static Stream<Arguments> uuidValue() {
        return Stream.of(
                Arguments.of("uuid is null", null),
                Arguments.of("uuid is empty", ""),
                Arguments.of("uuid is blank", "     ")
        );
    }

    @Test
    void validate_ShouldReturnErrorWhenUuidDoesNotExistInRepository() {
        String uuid = "INVALID";
        when(agreementRepositoryMock.findByUuid(uuid)).thenReturn(Optional.empty());
        lenient().when(errorFactoryMock.buildError(eq("ERROR_CODE_22"), anyList()))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_22", "DESCRIPTION"));

        List<ValidationErrorDTO> result = validator.validate(uuid);

        assertThat(result)
                .hasSize(1)
                .extracting("errorCode", "description")
                .containsExactly(
                        Assertions.tuple("ERROR_CODE_22", "DESCRIPTION"));
    }

    @Test
    void validate_ShouldReturnEmptyListWhenUuidExistsInRepository() {
        String uuid = "existing-uuid";
        when(agreementRepositoryMock.findByUuid(uuid)).thenReturn(Optional.of(new AgreementEntity()));

        List<ValidationErrorDTO> result = validator.validate(uuid);

        assertThat(result).isEmpty();
        verifyNoInteractions(errorFactoryMock);
    }

}
