package lv.javaguru.travel.insurance.core.sercices;

import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {

    @Mock
    private TravelAgreementValidator agreementValidator;

    @Mock
    private AgreementPersonsPremiumCalculator agreementPersonsPremiumCalculator;
    @Mock
    private AgreementTotalPremiumCalculator agreementTotalPremiumCalculator;
    @Mock
    private ResponseBuilder responseBuilder;

    @InjectMocks
    private TravelCalculatePremiumServiceImpl premiumService;

    @Test
    void shouldReturnValidationsErrors() {
        var agreement = new AgreementDTO();
        var command = new TravelCalculatePremiumCoreCommand(agreement);

        var validationError = new ValidationErrorDTO("Error code", "Error description");
        when(agreementValidator.validate(agreement)).thenReturn(List.of(validationError));
        when(responseBuilder.buildResponse(List.of(validationError))).thenReturn(new TravelCalculatePremiumCoreResult(List.of(validationError)));
        TravelCalculatePremiumCoreResult result = premiumService.calculatePremium(command);

        assertEquals(1, result.getErrors().size());
        assertEquals("Error code", result.getErrors().get(0).getErrorCode());
        assertEquals("Error description", result.getErrors().get(0).getDescription());
        verifyNoInteractions(agreementPersonsPremiumCalculator, agreementPersonsPremiumCalculator);
    }

    @Test
    public void shouldCalculatePersonsPremium() {
        var agreement = new AgreementDTO();
        when(agreementValidator.validate(agreement)).thenReturn(Collections.emptyList());
        when(responseBuilder.buildResponse(agreement)).thenReturn(new TravelCalculatePremiumCoreResult(Collections.emptyList(), agreement));
        premiumService.calculatePremium(new TravelCalculatePremiumCoreCommand(agreement));
        verify(agreementPersonsPremiumCalculator).calculateRiskPremiums(agreement);
    }

    @Test
    public void shouldCalculateAgreementTotalPremium() {
        var agreement = new AgreementDTO();
        when(agreementValidator.validate(agreement)).thenReturn(Collections.emptyList());
        when(agreementTotalPremiumCalculator.calculate(agreement)).thenReturn(BigDecimal.ONE);
        when(responseBuilder.buildResponse(agreement)).thenReturn(new TravelCalculatePremiumCoreResult(Collections.emptyList(), agreement));
        TravelCalculatePremiumCoreResult result = premiumService.calculatePremium(new TravelCalculatePremiumCoreCommand(agreement));
        assertEquals(BigDecimal.ONE, result.getAgreement().getAgreementPremium());
    }
}