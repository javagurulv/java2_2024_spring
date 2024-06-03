package lv.javaguru.travel.insurance.core.sercices;

import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;

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
    private AgreementPersonsPremiumCalculator agreementPersonsPremiumCalculator;
    @Mock
    private AgreementTotalPremiumCalculator agreementTotalPremiumCalculator;
    @Mock
    private PremiumServiceImplResponseBuilder premiumServiceImplResponseBuilder;
    @Mock
    private PersonEntityFactory personEntityFactory;
    @Mock
    private AgreementEntityFactory agreementEntityFactory;

    @InjectMocks
    private TravelCalculatePremiumServiceImpl premiumService;

    @Test
    void shouldReturnValidationsErrors() {
        var agreement = new AgreementDTO();
        var command = new TravelCalculatePremiumCoreCommand(agreement);

        var validationError = new ValidationErrorDTO("Error code", "Error description");
        when(agreementValidator.validate(agreement)).thenReturn(List.of(validationError));
        when(premiumServiceImplResponseBuilder.buildResponse(List.of(validationError))).thenReturn(new TravelCalculatePremiumCoreResult(List.of(validationError)));
        TravelCalculatePremiumCoreResult result = premiumService.calculatePremium(command);

        assertEquals(1, result.getErrors().size());
        assertEquals("Error code", result.getErrors().get(0).getErrorCode());
        assertEquals("Error description", result.getErrors().get(0).getDescription());
        verifyNoInteractions(agreementPersonsPremiumCalculator, agreementPersonsPremiumCalculator, personEntityFactory);
    }

    @Test
    public void shouldCalculatePersonsPremium() {
        var person = new PersonDTO(
                "John",
                "Dou",
                "12345",
                LocalDate.of(2000, 1, 1),
                "TRAVEL_MEDICAL",
                List.of()
        );
        var agreement = new AgreementDTO();
        agreement.setPersons(List.of(person));
        when(agreementValidator.validate(agreement)).thenReturn(Collections.emptyList());
        when(premiumServiceImplResponseBuilder.buildResponse(agreement)).thenReturn(new TravelCalculatePremiumCoreResult(Collections.emptyList(), agreement));
        premiumService.calculatePremium(new TravelCalculatePremiumCoreCommand(agreement));
        verify(agreementPersonsPremiumCalculator).calculateRiskPremiums(agreement);
    }

    @Test
    public void shouldCalculateAgreementTotalPremium() {
        var person = new PersonDTO(
                "John",
                "Dou",
                "12345",
                LocalDate.of(2000, 1, 1),
                "TRAVEL_MEDICAL",
                List.of()
        );
        var agreement = new AgreementDTO();
        agreement.setPersons(List.of(person));
        when(agreementValidator.validate(agreement)).thenReturn(Collections.emptyList());
        when(agreementTotalPremiumCalculator.calculate(agreement)).thenReturn(BigDecimal.ONE);
        when(premiumServiceImplResponseBuilder.buildResponse(agreement)).thenReturn(new TravelCalculatePremiumCoreResult(Collections.emptyList(), agreement));
        TravelCalculatePremiumCoreResult result = premiumService.calculatePremium(new TravelCalculatePremiumCoreCommand(agreement));
        assertEquals(BigDecimal.ONE, result.getAgreement().getAgreementPremium());
    }

    @Test
    public void shouldSaveAgreement(){
        var person = new PersonDTO(
                "John",
                "Dou",
                "12345",
                LocalDate.of(2000, 1, 1),
                "TRAVEL_MEDICAL",
                List.of()
        );
        var agreement = new AgreementDTO();
        agreement.setPersons(List.of(person));
        when(agreementValidator.validate(agreement)).thenReturn(Collections.emptyList());
        premiumService.calculatePremium(new TravelCalculatePremiumCoreCommand(agreement));
        verify(agreementEntityFactory).createAgreementEntity(agreement);
    }
}