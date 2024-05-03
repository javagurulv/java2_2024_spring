package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {

    @Mock
    private TravelPremiumUnderwriting premiumUnderwriting;

    @Mock
    private TravelCalculatePremiumRequestValidator requestValidator;

    @Mock
    private TravelCalculatePremiumRequest request;

    @InjectMocks
    private TravelCalculatePremiumServiceImpl premiumService;

    @Test
    public void personFirstName() {
        TravelCalculatePremiumResponse response = premiumService.calculatePremium(request);
        assertEquals(response.getPersonFirstName(), request.getPersonFirstName());
    }

    @Test
    public void personLastName() {
        TravelCalculatePremiumResponse response = premiumService.calculatePremium(request);
        assertEquals(response.getPersonLastName(), request.getPersonLastName());
    }

    @Test
    public void agreementDateFrom() {
        TravelCalculatePremiumResponse response = premiumService.calculatePremium(request);
        assertEquals(response.getAgreementDateFrom(), request.getAgreementDateFrom());
    }

    @Test
    public void agreementDateTo() {
        TravelCalculatePremiumResponse response = premiumService.calculatePremium(request);
        assertEquals(response.getAgreementDateTo(), request.getAgreementDateTo());
    }

    @Test
    public void responseWithCorrectAgreementPrice() {
        when(request.getAgreementDateFrom()).thenReturn(createDate("2005.05.15"));
        when(request.getAgreementDateTo()).thenReturn(createDate("2005.05.20"));
        when(premiumUnderwriting.calculatePremium(request)).thenReturn(new BigDecimal(5));
        TravelCalculatePremiumResponse response = premiumService.calculatePremium(request);
        assertEquals(response.getAgreementPrice(), new BigDecimal(5));
    }

    @Test
    public void responseWithErrors() {
        List<ValidationError> errors = createValidationErrorList();
        when(requestValidator.validate(request)).thenReturn(errors);
        TravelCalculatePremiumResponse response = premiumService.calculatePremium(request);
        assertTrue(response.hasErrors());
    }

    @Test
    public void responseWithValidationErrors() {
        List<ValidationError> errors = createValidationErrorList();
        when(requestValidator.validate(request)).thenReturn(errors);
        TravelCalculatePremiumResponse response = premiumService.calculatePremium(request);
        assertEquals(errors.size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "field");
        assertEquals(response.getErrors().get(0).getMessage(), "errorMessage");
    }



    private Date createDate(String dateString) {
        try {
            return new SimpleDateFormat("yyyy.MM.dd").parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private List<ValidationError> createValidationErrorList() {
        return List.of(new ValidationError("field", "errorMessage"));
    }

}