package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.core.validations.TravelCalculatePremiumRequestValidator;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {

    private TravelCalculatePremiumRequest travelCalculatePremiumRequestData;

    @Mock
    private TravelPremium travelPremium;

    @Mock
    private TravelCalculatePremiumRequestValidator requestValidatorMock;

    @InjectMocks
    private TravelCalculatePremiumServiceImpl service;

    @BeforeEach
    void setUp() {
        LocalDate dateFrom = LocalDate.of(2030, 3, 8);
        LocalDate dateTo = LocalDate.of(2030, 3, 18);
        List<String> selectedRisks = List.of("TRAVEL_MEDICAL", "TRAVEL_CANCELLATION", "TRAVEL_LOSS_BAGGAGE");

        travelCalculatePremiumRequestData = new TravelCalculatePremiumRequest("Vladislav", "Romanov", dateFrom, dateTo, selectedRisks);
    }

    @Test
    void responseFirstNameTest() {
        TravelCalculatePremiumRequest request = travelCalculatePremiumRequestData;
        doReturn(List.of()).when(requestValidatorMock).validate(request);
        TravelCalculatePremiumResponse response = createResponse();

        assertEquals(request.getPersonFirstName(), response.getPersonFirstName());
    }

    @Test
    void responseLastNameTest() {
        TravelCalculatePremiumRequest request = travelCalculatePremiumRequestData;
        doReturn(List.of()).when(requestValidatorMock).validate(request);
        TravelCalculatePremiumResponse response = createResponse();

        assertEquals(request.getPersonLastName(), response.getPersonLastName());
    }

    @Test
    void responseDateFromTest() {
        TravelCalculatePremiumRequest request = travelCalculatePremiumRequestData;
        doReturn(List.of()).when(requestValidatorMock).validate(request);
        TravelCalculatePremiumResponse response = createResponse();

        assertEquals(request.getAgreementDateFrom(), response.getAgreementDateFrom());
    }

    @Test
    void responseDateToTest() {
        TravelCalculatePremiumRequest request = travelCalculatePremiumRequestData;
        doReturn(List.of()).when(requestValidatorMock).validate(request);
        TravelCalculatePremiumResponse response = createResponse();

        assertEquals(request.getAgreementDateTo(), response.getAgreementDateTo());
    }

    @Test
    void responseWithErrorsTest() {
        TravelCalculatePremiumRequest request = travelCalculatePremiumRequestData;
        ValidationError validationError = new ValidationError("field", "message");
        doReturn(List.of(validationError)).when(requestValidatorMock).validate(request);
        TravelCalculatePremiumResponse response = createResponse();

        assertTrue(response.hasErrors());
    }

    @Test
    void responseWithErrorCountTest() {
        TravelCalculatePremiumRequest request = travelCalculatePremiumRequestData;
        ValidationError validationError = new ValidationError("field", "message");
        doReturn(List.of(validationError)).when(requestValidatorMock).validate(request);
        TravelCalculatePremiumResponse response = createResponse();

        assertEquals(response.getErrors().size(), 1);
    }

    @Test
    void responseWithCorrectErrorMessageTest() {
        TravelCalculatePremiumRequest request = travelCalculatePremiumRequestData;
        ValidationError validationError = new ValidationError("field", "message");
        doReturn(List.of(validationError)).when(requestValidatorMock).validate(request);
        TravelCalculatePremiumResponse response = createResponse();

        assertEquals(response.getErrors().get(0).getField(), "field");
        assertEquals(response.getErrors().get(0).getMessage(), "message");
    }

    @Test
    void emptyResponseFieldsInCaseOfErrorsTest() {
        TravelCalculatePremiumRequest request = travelCalculatePremiumRequestData;
        ValidationError validationError = new ValidationError("field", "message");
        doReturn(List.of(validationError)).when(requestValidatorMock).validate(request);
        TravelCalculatePremiumResponse response = createResponse();

        assertNull(response.getPersonFirstName());
        assertNull(response.getPersonLastName());
        assertNull(response.getAgreementDateFrom());
        assertNull(response.getAgreementDateTo());
        assertNull(response.getAgreementPrice());
    }

    private TravelCalculatePremiumResponse createResponse() {
        return service.calculatePremium(travelCalculatePremiumRequestData);
    }

}