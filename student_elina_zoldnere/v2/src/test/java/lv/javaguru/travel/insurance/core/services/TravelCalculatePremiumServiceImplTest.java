package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.underwriting.TravelCalculatePremiumUnderwriting;
import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumCalculationResult;
import lv.javaguru.travel.insurance.core.util.SetUpInstancesHelper;
import lv.javaguru.travel.insurance.core.validations.TravelAgreementValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelCalculatePremiumServiceImplTest {

    @Mock
    private TravelAgreementValidator agreementValidatorMock;
    @Mock
    private TravelCalculatePremiumUnderwriting calculateUnderwritingMock;
    @Mock
    private TravelCalculatePremiumCoreCommand commandMock;

    @InjectMocks
    private TravelCalculatePremiumServiceImpl service;

    @Autowired
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
    }

    @Test
    public void calculatePremium_ShouldReturnCorrectResponseWithOnePerson() {
        AgreementDTO agreement = helper.newAgreementDTO();
        PersonDTO person = helper.newPersonDTO();
        TravelCalculatePremiumCoreCommand command = new TravelCalculatePremiumCoreCommand(agreement);
        when(agreementValidatorMock.validate(command.getAgreement()))
                .thenReturn(Collections.emptyList());
        when(calculateUnderwritingMock.calculateAgreementPremium(agreement, person))
                .thenReturn(new TravelPremiumCalculationResult(BigDecimal.TEN, List.of(helper.newRiskDTO())));

        TravelCalculatePremiumCoreResult result = service.calculatePremium(command);

        assertEquals(BigDecimal.TEN, result.getAgreement().agreementPremium());
    }

    @Test
    public void calculatePremium_ShouldReturnCorrectResponseWithTwoPersons() {
        AgreementDTO agreement = helper.newTwoPersonsAgreementDTO();
        PersonDTO person = helper.newPersonDTO();
        TravelCalculatePremiumCoreCommand command = new TravelCalculatePremiumCoreCommand(agreement);
        when(agreementValidatorMock.validate(command.getAgreement()))
                .thenReturn(Collections.emptyList());
        when(calculateUnderwritingMock.calculateAgreementPremium(agreement, person))
                .thenAnswer(invocation ->
                        new TravelPremiumCalculationResult(BigDecimal.TEN, List.of(helper.newRiskDTO())));

        TravelCalculatePremiumCoreResult result = service.calculatePremium(command);

        assertEquals(BigDecimal.valueOf(20), result.getAgreement().agreementPremium());
    }

}