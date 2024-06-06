package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import lv.javaguru.travel.insurance.core.validation.TravelCalculatePremiumRequestValidator;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.dto.ValidationErrors;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {

    @Mock
    private TravelCalculatePremiumRequestValidator travelCalculatePremiumRequestValidator;
    @Mock
    private TravelPremiumUnderwriting travelPremiumUnderwriting;

    @InjectMocks
    private TravelCalculatePremiumServiceImpl premiumService;


    @Test
    public void checkResponseFirstName() {
        TravelCalculatePremiumRequest travelCalculatePremiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(travelCalculatePremiumRequest.getPersonFirstName()).thenReturn("firstName");
        when(travelCalculatePremiumRequestValidator.validation(travelCalculatePremiumRequest)).thenReturn(List.of());
        TravelCalculatePremiumResponse travelCalculatePremiumResponse = premiumService.calculatePremium(travelCalculatePremiumRequest);
        assertEquals(travelCalculatePremiumResponse.getPersonFirstName(), travelCalculatePremiumRequest.getPersonFirstName());
    }

    @Test
    public void checkResponseLastName() {
        TravelCalculatePremiumRequest travelCalculatePremiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(travelCalculatePremiumRequest.getPersonLastName()).thenReturn("lastName");
        when(travelCalculatePremiumRequestValidator.validation(travelCalculatePremiumRequest)).thenReturn(List.of());
        TravelCalculatePremiumResponse travelCalculatePremiumResponse = premiumService.calculatePremium(travelCalculatePremiumRequest);
        assertEquals(travelCalculatePremiumResponse.getPersonLastName(), travelCalculatePremiumRequest.getPersonLastName());
    }

    @Test
    public void checkResponseDateFrom() {
        TravelCalculatePremiumRequest travelCalculatePremiumRequest = mock(TravelCalculatePremiumRequest.class);
        Date dateFrom = new Date();
        when(travelCalculatePremiumRequest.getAgreementDateFrom()).thenReturn(dateFrom);
        when(travelCalculatePremiumRequestValidator.validation(travelCalculatePremiumRequest)).thenReturn(List.of());
        TravelCalculatePremiumResponse travelCalculatePremiumResponse = premiumService.calculatePremium(travelCalculatePremiumRequest);
        assertEquals(travelCalculatePremiumResponse.getAgreementDateFrom(), travelCalculatePremiumRequest.getAgreementDateFrom());
    }

    @Test
    public void checkResponseDateTo() {
        TravelCalculatePremiumRequest travelCalculatePremiumRequest = mock(TravelCalculatePremiumRequest.class);
        Date dateTo= new Date();
        when(travelCalculatePremiumRequest.getAgreementDateTo()).thenReturn(dateTo);
        when(travelCalculatePremiumRequestValidator.validation(travelCalculatePremiumRequest)).thenReturn(List.of());
        TravelCalculatePremiumResponse travelCalculatePremiumResponse = premiumService.calculatePremium(travelCalculatePremiumRequest);
        assertEquals(travelCalculatePremiumResponse.getAgreementDateTo(), travelCalculatePremiumRequest.getAgreementDateTo());
    }

    @Test
    public void checkResponseAgreementPrice() {
        TravelCalculatePremiumRequest travelCalculatePremiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(travelCalculatePremiumRequest.getAgreementDateFrom()).thenReturn(makeDate("10.10.2010"));
        when(travelCalculatePremiumRequest.getAgreementDateTo()).thenReturn(makeDate("11.10.2010"));
        when(travelCalculatePremiumRequestValidator.validation(travelCalculatePremiumRequest)).thenReturn(List.of());
        when(travelPremiumUnderwriting.calculatedPremium(travelCalculatePremiumRequest)).thenReturn(new BigDecimal(1));
        TravelCalculatePremiumResponse travelCalculatePremiumResponse = premiumService.calculatePremium(travelCalculatePremiumRequest);
        assertEquals(travelCalculatePremiumResponse.getAgreementPrice(), new BigDecimal(1));
    }

    @Test
    public void checkResponseWithErrors() {
        TravelCalculatePremiumRequest travelCalculatePremiumRequest = mock(TravelCalculatePremiumRequest.class);
        List<ValidationErrors> validationErrors = makeValidationErrorList();
        when(travelCalculatePremiumRequestValidator.validation(travelCalculatePremiumRequest)).thenReturn(validationErrors);
        TravelCalculatePremiumResponse travelCalculatePremiumResponse = premiumService.calculatePremium(travelCalculatePremiumRequest);
        assertTrue(travelCalculatePremiumResponse.errorsFound());
    }

    @Test
    public void checkResponseWithRightValidationErrors() {
        TravelCalculatePremiumRequest travelCalculatePremiumRequest = mock(TravelCalculatePremiumRequest.class);
        List<ValidationErrors> validationErrors = makeValidationErrorList();
        when(travelCalculatePremiumRequestValidator.validation(travelCalculatePremiumRequest)).thenReturn(validationErrors);
        TravelCalculatePremiumResponse travelCalculatePremiumResponse = premiumService.calculatePremium(travelCalculatePremiumRequest);
        assertTrue(travelCalculatePremiumResponse.errorsFound());
    }

    @Test
    public void checkNoInvokingDateTimeUtilWhenValidationError() {
        TravelCalculatePremiumRequest travelCalculatePremiumRequest = mock(TravelCalculatePremiumRequest.class);
        List<ValidationErrors> validationErrors = makeValidationErrorList();
        when(travelCalculatePremiumRequestValidator.validation(travelCalculatePremiumRequest)).thenReturn(validationErrors);
        TravelCalculatePremiumResponse travelCalculatePremiumResponse = premiumService.calculatePremium(travelCalculatePremiumRequest);
        assertEquals(travelCalculatePremiumResponse.getValidationErrors().size(),1);
        assertEquals(travelCalculatePremiumResponse.getValidationErrors().get(0).getErrorCode(), "field");
        assertEquals(travelCalculatePremiumResponse.getValidationErrors().get(0).getDescription(), "message");

    }


    private List<ValidationErrors> makeValidationErrorList() {
        return List.of(new ValidationErrors("field", "message"));
    }

    private Date makeDate(String dateString) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
