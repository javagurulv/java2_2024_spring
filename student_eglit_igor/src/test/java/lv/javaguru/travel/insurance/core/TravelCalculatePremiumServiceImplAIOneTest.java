/*
package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TravelCalculatePremiumServiceImplAIOneTest {

    @Test
    public void testCalculatePremium() {
        // Создаем объект сервиса
        TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();

        // Создаем тестовый запрос
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(
                "Igor",
                "Eglit",
                LocalDate.of(2024, 12, 12),
                LocalDate.of(2024, 12, 13));

        // Вызываем метод calculatePremium для получения результата
        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        // Проверяем, что полученный ответ не является null
        assertNotNull(response);

        // Проверяем, что значения полей в ответе соответствуют значениям в запросе
        assertEquals(request.getPersonFirstName(), response.getPersonFirstName());
        assertEquals(request.getPersonLastName(), response.getPersonLastName());
        assertEquals(request.getAgreementDateFrom(), response.getAgreementDateFrom());
        assertEquals(request.getAgreementDateTo(), response.getAgreementDateTo());
    }
}
*/
