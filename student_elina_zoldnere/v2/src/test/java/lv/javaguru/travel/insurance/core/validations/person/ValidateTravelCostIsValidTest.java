package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTOBuilder;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTOBuilder;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidateTravelCostIsValidTest {

    @Mock
    private ValidationErrorFactory errorFactoryMock;

    @InjectMocks
    private ValidateTravelCostIsValidWhenTravelCancellationSelected validate;

    @Test
    void validateSingle_ShouldReturnCorrectResponseWhenTravelCostIsNotSupported() {
        AgreementDTO agreement = AgreementDTOBuilder.createAgreement()
                .withSelectedRisk("TRAVEL_CANCELLATION")
                .build();
        PersonDTO person = PersonDTOBuilder.createPerson()
                .withTravelCost(new BigDecimal("2000000"))
                .build();

        when(errorFactoryMock.buildError(eq("ERROR_CODE_94"), anyList()))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_94",
                        "Travel Cost value 2000000 is not supported!"));

        Optional<ValidationErrorDTO> result = validate.validateSingle(agreement, person);

        assertThat(result)
                .isPresent()
                .hasValueSatisfying(error -> {
                    assertThat(error.errorCode()).isEqualTo("ERROR_CODE_94");
                    assertThat(error.description()).isEqualTo("Travel Cost value 2000000 is not supported!");
                });
    }

    @Test
    void validateSingle_ShouldNotReturnErrorWhenTravelCostIsValid() {
        AgreementDTO agreement = AgreementDTOBuilder.createAgreement()
                .withSelectedRisk("TRAVEL_CANCELLATION")
                .build();
        PersonDTO person = PersonDTOBuilder.createPerson()
                .withTravelCost(new BigDecimal("6000"))
                .build();

        Optional<ValidationErrorDTO> result = validate.validateSingle(agreement, person);

        assertThat(result).isNotPresent();
        verifyNoInteractions(errorFactoryMock);
    }

}