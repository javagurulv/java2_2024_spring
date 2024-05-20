package lv.javaguru.travel.insurance.core.validations.uuid;

import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.domain.entities.AgreementEntity;
import lv.javaguru.travel.insurance.core.repositories.entities.AgreementEntityRepository;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.lenient;
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
        ValidationErrorDTO error = new ValidationErrorDTO("ERROR_CODE_21", "description");
        when(errorFactoryMock.buildError("ERROR_CODE_21")).thenReturn(error);

        List<ValidationErrorDTO> result = validator.validate(uuid);

        assertEquals(1, result.size());
        assertEquals(error, result.get(0));
    }

    private static Stream<Arguments> uuidValue() {
        return Stream.of(
                Arguments.of("uuid null", null),
                Arguments.of("uuid blank", "     ")
        );
    }

    @Test
    void validate_ShouldReturnErrorWhenUuidDoesNotExistInRepository() {
        String uuid = "INVALID";
        when(agreementRepositoryMock.findByUuid(uuid)).thenReturn(Optional.empty());

        ValidationErrorDTO error = new ValidationErrorDTO("ERROR_CODE_22", "description");
        lenient().when(errorFactoryMock.buildError(eq("ERROR_CODE_22"), anyList())).thenReturn(error);

        List<ValidationErrorDTO> result = validator.validate(uuid);

        assertEquals(1, result.size());
        assertEquals(error, result.get(0));
    }

    @Test
    void validate_ShouldReturnEmptyListWhenUuidExistsInRepository() {
        String uuid = "existing-uuid";
        when(agreementRepositoryMock.findByUuid(uuid)).thenReturn(Optional.of(new AgreementEntity()));

        List<ValidationErrorDTO> result = validator.validate(uuid);

        assertTrue(result.isEmpty());
    }

}
