package org.javaguru.travel.insurance.core.services;

import org.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import org.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.api.dto.RiskDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.javaguru.travel.insurance.core.services.TravelCalculatePremiumServiceImpl;
import org.javaguru.travel.insurance.core.underwriting.TravelPremiumCalculationResult;
import org.javaguru.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import org.javaguru.travel.insurance.core.validations.TravelAgreementValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TravelCalculatePremiumServiceImplTest {

    @Mock private TravelAgreementValidator agreementValidator;
    @Mock private TravelPremiumUnderwriting premiumUnderwriting;

    @InjectMocks
    private TravelCalculatePremiumServiceImpl premiumService;

    @Test
    public void shouldReturnValidationErrors() {
        var agreement = new AgreementDTO();
        var command = new TravelCalculatePremiumCoreCommand(agreement);

        var validationError = new ValidationErrorDTO("Error code", "Error description");
        when(agreementValidator.validate(agreement)).thenReturn(List.of(validationError));

        TravelCalculatePremiumCoreResult result = premiumService.calculatePremium(command);

        assertEquals(result.getErrors().size(), 1);
        assertEquals(result.getErrors().get(0).getErrorCode(), "Error code");
        assertEquals(result.getErrors().get(0).getDescription(), "Error description");
        verify(premiumUnderwriting, never()).calculatePremium(any(), any());
    }

    @Test
    public void shouldReturnPremiumForOnePerson() {
        var person = new PersonDTO();
        var agreement = new AgreementDTO();
        agreement.setPersons(List.of(person));

        when(agreementValidator.validate(agreement)).thenReturn(Collections.emptyList());

        var risk = new RiskDTO("TRAVEL_MEDICAL", BigDecimal.ONE);
        TravelPremiumCalculationResult calculationResult = new TravelPremiumCalculationResult(BigDecimal.ONE, List.of(risk));
        when(premiumUnderwriting.calculatePremium(agreement, person))
                .thenReturn(calculationResult);

        TravelCalculatePremiumCoreResult result = premiumService.calculatePremium(new TravelCalculatePremiumCoreCommand(agreement));

        assertEquals(BigDecimal.ONE, result.getAgreement().getAgreementPremium());
        verify(premiumUnderwriting, times(1)).calculatePremium(eq(agreement), any(PersonDTO.class));
    }

    @Test
    public void shouldReturnPremiumForTwoPersons() {
        var person1 = new PersonDTO();
        var person2 = new PersonDTO();
        var agreement = new AgreementDTO();
        agreement.setPersons(List.of(person1, person2));

        when(agreementValidator.validate(agreement)).thenReturn(Collections.emptyList());

        var risk1 = new RiskDTO("TRAVEL_MEDICAL", BigDecimal.ONE);
        var risk2 = new RiskDTO("TRAVEL_MEDICAL", BigDecimal.ONE);
        when(premiumUnderwriting.calculatePremium(agreement, person1))
                .thenReturn(new TravelPremiumCalculationResult(BigDecimal.ONE, List.of(risk1)));
        when(premiumUnderwriting.calculatePremium(agreement, person2))
                .thenReturn(new TravelPremiumCalculationResult(BigDecimal.ONE, List.of(risk2)));

        TravelCalculatePremiumCoreResult result = premiumService.calculatePremium(new TravelCalculatePremiumCoreCommand(agreement));

        assertEquals(BigDecimal.valueOf(2), result.getAgreement().getAgreementPremium());
        verify(premiumUnderwriting, times(2)).calculatePremium(eq(agreement), any(PersonDTO.class));
    }

}
