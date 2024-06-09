package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.services.writers.entity.EntityWriter;
import lv.javaguru.travel.insurance.core.util.SetUpInstancesHelper;
import lv.javaguru.travel.insurance.core.validations.TravelAgreementValidator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelCalculatePremiumServiceImplTest {

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
    @InjectMocks
    private SetUpInstancesHelper helper;

    @Test
    public void calculatePremium_ShouldReturnErrors() {
        when(agreementValidatorMock.validate(commandMock.getAgreement()))
                .thenReturn(List.of(helper.newValidationErrorDTO()));

        TravelCalculatePremiumCoreResult result = service.calculatePremium(commandMock);

        assertEquals(1, result.getErrors().size());
        assertEquals("errorCode", result.getErrors().get(0).errorCode());
        assertEquals("description", result.getErrors().get(0).description());
        verifyNoInteractions(calculateAndUpdateAgreementMock, entityWriterMock);
    }

    @Test
    public void calculatePremium_ShouldReturnCorrectResponseWithOnePerson() {
        AgreementDTO agreement = helper.newAgreementDTO();
        List<PersonDTO> personsWithRisks = List.of(helper.newPersonWithRiskDTO());
        TravelCalculatePremiumCoreCommand command = new TravelCalculatePremiumCoreCommand(agreement);

        when(agreementValidatorMock.validate(command.getAgreement()))
                .thenReturn(Collections.emptyList());
        when(calculateAndUpdateAgreementMock.calculateAgreementPremiums(agreement))
                .thenReturn(agreement.withPremiums(personsWithRisks, BigDecimal.TEN, "UUID"));

        TravelCalculatePremiumCoreResult result = service.calculatePremium(command);

        assertEquals(BigDecimal.TEN, result.getAgreement().agreementPremium());
        assertEquals(1, result.getAgreement().persons().size());
        assertEquals(BigDecimal.TEN, result.getAgreement().persons().get(0).personRisks().get(0).premium());
        assertEquals("UUID", result.getAgreement().uuid());
    }

    @Test
    public void calculatePremium_ShouldReturnCorrectResponseWithTwoPersons() {
        AgreementDTO agreement = helper.newTwoPersonsAgreementDTO();
        List<PersonDTO> personsWithRisks = List.of(helper.newPersonWithRiskDTO(), helper.newPersonWithRiskDTO());
        TravelCalculatePremiumCoreCommand command = new TravelCalculatePremiumCoreCommand(agreement);

        when(agreementValidatorMock.validate(command.getAgreement()))
                .thenReturn(Collections.emptyList());
        when(calculateAndUpdateAgreementMock.calculateAgreementPremiums(agreement))
                .thenReturn(agreement.withPremiums(personsWithRisks, BigDecimal.valueOf(20), "UUID"));

        TravelCalculatePremiumCoreResult result = service.calculatePremium(command);

        assertEquals(BigDecimal.valueOf(20), result.getAgreement().agreementPremium());
        assertEquals(2, result.getAgreement().persons().size());
        assertEquals(BigDecimal.TEN, result.getAgreement().persons().get(0).personRisks().get(0).premium());
        assertEquals(BigDecimal.TEN, result.getAgreement().persons().get(1).personRisks().get(0).premium());
        assertEquals("UUID", result.getAgreement().uuid());
    }

}