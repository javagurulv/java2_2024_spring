package org.javaguru.travel.insurance.core.services;

import org.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import org.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.javaguru.travel.insurance.core.services.AgreementPersonsPremiumCalculator;
import org.javaguru.travel.insurance.core.services.AgreementTotalPremiumCalculator;
import org.javaguru.travel.insurance.core.services.TravelCalculatePremiumServiceImpl;
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
    @Mock private AgreementPersonsPremiumCalculator agreementPersonsPremiumCalculator;
    @Mock private AgreementTotalPremiumCalculator agreementTotalPremiumCalculator;

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
        verifyNoInteractions(agreementPersonsPremiumCalculator, agreementPersonsPremiumCalculator);
    }

    @Test
    public void shouldCalculatePersonsPremium() {
        var agreement = new AgreementDTO();
        when(agreementValidator.validate(agreement)).thenReturn(Collections.emptyList());
        premiumService.calculatePremium(new TravelCalculatePremiumCoreCommand(agreement));
        verify(agreementPersonsPremiumCalculator).calculateRiskPremiums(agreement);
    }

    @Test
    public void shouldCalculateAgreementTotalPremium() {
        var agreement = new AgreementDTO();
        when(agreementValidator.validate(agreement)).thenReturn(Collections.emptyList());
        when(agreementTotalPremiumCalculator.calculate(agreement)).thenReturn(BigDecimal.ONE);
        TravelCalculatePremiumCoreResult result = premiumService.calculatePremium(new TravelCalculatePremiumCoreCommand(agreement));
        assertEquals(result.getAgreement().getAgreementPremium(), BigDecimal.ONE);
    }

}
