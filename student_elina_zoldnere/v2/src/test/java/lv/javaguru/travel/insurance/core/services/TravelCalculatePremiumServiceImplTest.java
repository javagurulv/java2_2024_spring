package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTOBuilder;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTOBuilder;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTOBuilder;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTOBuilder;
import lv.javaguru.travel.insurance.core.services.writers.entity.EntityWriter;
import lv.javaguru.travel.insurance.core.validations.TravelAgreementValidator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {

    @Mock
    private TravelAgreementValidator agreementValidatorMock;
    @Mock
    private CalculateAndUpdateAgreementWithPremiums calculateAndUpdateAgreementMock;
    @Mock
    private EntityWriter entityWriterMock;
    @Mock
    private TravelCalculatePremiumCoreCommand commandMock;

    @InjectMocks
    private TravelCalculatePremiumServiceImpl service;

    @Test
    void calculatePremium_ShouldReturnErrors() {
        ValidationErrorDTO validationError = ValidationErrorDTOBuilder.createValidationError()
                .withErrorCode("ERROR_CODE")
                .withDescription("DESCRIPTION")
                .build();
        when(agreementValidatorMock.validate(commandMock.getAgreement()))
                .thenReturn(List.of(validationError));

        TravelCalculatePremiumCoreResult result = service.calculatePremium(commandMock);

        assertThat(result.getErrors())
                .hasSize(1)
                .element(0)
                .satisfies(e -> {
                    assertThat(e.errorCode()).isEqualTo("ERROR_CODE");
                    assertThat(e.description()).isEqualTo("DESCRIPTION");
                });
        verifyNoInteractions(calculateAndUpdateAgreementMock, entityWriterMock);
    }

    @Test
    void calculatePremium_ShouldReturnCorrectResponseWithOnePerson() {
        RiskDTO risk = RiskDTOBuilder.createRisk().withPremium(BigDecimal.TEN).build();
        List<PersonDTO> persons = List.of(PersonDTOBuilder.createPerson().withPersonRisk(risk).build());
        AgreementDTO agreement = AgreementDTOBuilder.createAgreement()
                .withPersons(persons)
                .build();
        TravelCalculatePremiumCoreCommand command = new TravelCalculatePremiumCoreCommand(agreement);

        when(agreementValidatorMock.validate(command.getAgreement()))
                .thenReturn(Collections.emptyList());
        when(calculateAndUpdateAgreementMock.calculateAgreementPremiums(agreement))
                .thenReturn(agreement.withPremiums(persons, BigDecimal.TEN, "UUID"));

        TravelCalculatePremiumCoreResult result = service.calculatePremium(command);

        assertThat(result.getAgreement())
                .satisfies(a -> {
                    assertThat(a.agreementPremium()).isEqualByComparingTo(BigDecimal.TEN);
                    assertThat(a.persons()).hasSize(1);
                    assertThat(a.persons().get(0).personRisks().get(0).premium()).isEqualByComparingTo(BigDecimal.TEN);
                    assertThat(a.uuid()).isEqualTo("UUID");
                });
    }

    @Test
    void calculatePremium_ShouldReturnCorrectResponseWithTwoPersons() {
        RiskDTO risk1 = RiskDTOBuilder.createRisk().withPremium(BigDecimal.TEN).build();
        RiskDTO risk2 = RiskDTOBuilder.createRisk().withPremium(BigDecimal.TEN).build();
        List<PersonDTO> persons = List.of(PersonDTOBuilder.createPerson().withPersonRisk(risk1).build(),
                PersonDTOBuilder.createPerson().withPersonRisk(risk2).build());
        AgreementDTO agreement = AgreementDTOBuilder.createAgreement()
                .withPersons(persons)
                .build();
        TravelCalculatePremiumCoreCommand command = new TravelCalculatePremiumCoreCommand(agreement);

        when(agreementValidatorMock.validate(command.getAgreement()))
                .thenReturn(Collections.emptyList());
        when(calculateAndUpdateAgreementMock.calculateAgreementPremiums(agreement))
                .thenReturn(agreement.withPremiums(persons, BigDecimal.valueOf(20), "UUID"));

        TravelCalculatePremiumCoreResult result = service.calculatePremium(command);

        assertThat(result.getAgreement())
                .satisfies(a -> {
                    assertThat(a.agreementPremium()).isEqualByComparingTo(BigDecimal.valueOf(20));
                    assertThat(a.persons()).hasSize(2);
                    assertThat(a.persons().get(0).personRisks().get(0).premium()).isEqualByComparingTo(BigDecimal.TEN);
                    assertThat(a.persons().get(1).personRisks().get(0).premium()).isEqualByComparingTo(BigDecimal.TEN);
                    assertThat(a.uuid()).isEqualTo("UUID");
                });
    }

}