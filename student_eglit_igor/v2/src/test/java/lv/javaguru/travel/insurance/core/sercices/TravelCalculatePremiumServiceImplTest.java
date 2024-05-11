package lv.javaguru.travel.insurance.core.sercices;

import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumCalculationResult;
import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import lv.javaguru.travel.insurance.core.validations.TravelAgreementValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {

    @Mock
    private TravelAgreementValidator agreementValidator;
    @Mock
    private TravelPremiumUnderwriting premiumUnderwriting;

    @InjectMocks
    private TravelCalculatePremiumServiceImpl premiumService;

    @Test
    void shouldReturnValidationsErrors() {
        var agreement = new AgreementDTO();
        var command = new TravelCalculatePremiumCoreCommand(agreement);

        var validationError = new ValidationErrorDTO("Error code", "Error description");
        when(agreementValidator.validate(agreement)).thenReturn(List.of(validationError));

        TravelCalculatePremiumCoreResult result = premiumService.calculatePremium(command);

        assertEquals(1, result.getErrors().size());
        assertEquals("Error code", result.getErrors().get(0).getErrorCode());
        assertEquals("Error description", result.getErrors().get(0).getDescription());
        verify(premiumUnderwriting, never()).calculatePremium(any(), any());
    }

    @Test
    void shouldReturnPremiumForOnePerson() {
        var person = new PersonDTO("Name", "Surname",
                LocalDate.of(2000, 1, 1),
                List.of(new RiskDTO()));
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
    void shouldReturnPremiumForTwoPersons(){
        var person1 = new PersonDTO("Name1", "Surname1",
                LocalDate.of(2000, 1, 1),
                List.of(new RiskDTO()));
        var person2 = new PersonDTO("Name2", "Surname2",
                LocalDate.of(2000, 1, 1),
                List.of(new RiskDTO()));
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

        assertEquals(BigDecimal.valueOf(4), result.getAgreement().getAgreementPremium());
        verify(premiumUnderwriting, times(2)).calculatePremium(eq(agreement), any(PersonDTO.class));
    }

}