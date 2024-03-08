package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TravelCalculatePremiumServiceImplAIEachFieldTest {

    TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();

    // Создаем тестовый запрос с валидными данными
    LocalDate agreementDateFrom = LocalDate.of(2024, 12, 12);
    LocalDate agreementDateTo = LocalDate.of(2024, 12, 13);
    TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(
            "Igor",
            "Eglit",
            agreementDateFrom,
            agreementDateTo);
    TravelCalculatePremiumResponse response = service.calculatePremium(request);

    @Test
    public void testCalculatePremiumWithValidData() {
        // Проверяем, что полученный ответ не является null
        assertNotNull(response);
    }

    @Test
    public void testCalculatePremiumPersonFirstName() {
        // Проверяем, что значение поля PersonFirstName в ответе соответствует значению в запросе
        assertEquals(request.getPersonFirstName(), response.getPersonFirstName());
    }

    @Test
    public void testCalculatePremiumPersonLastName() {
        // Проверяем, что значение поля PersonLastName в ответе соответствует значению в запросе
        assertEquals(request.getPersonLastName(), response.getPersonLastName());
    }

    @Test
    public void testCalculatePremiumAgreementDateFrom() {
        assertEquals(request.getAgreementDateFrom(), response.getAgreementDateFrom());
    }

    @Test
    public void testCalculatePremiumAgreementDateTo() {
        // Проверяем, что значение поля AgreementDateTo в ответе соответствует значению в запросе
        assertEquals(request.getAgreementDateTo(), response.getAgreementDateTo());
    }
}
