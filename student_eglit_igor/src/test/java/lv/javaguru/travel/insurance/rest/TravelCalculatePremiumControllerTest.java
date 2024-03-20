package lv.javaguru.travel.insurance.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Используем SpringExtension для интеграции с Spring TestContext Framework
@ExtendWith(SpringExtension.class)
// Запускаем приложение Spring Boot для интеграционного тестирования
@SpringBootTest
// Автоматически настраиваем MockMvc для интеграционного тестирования MVC контроллеров
@AutoConfigureMockMvc//
public class TravelCalculatePremiumControllerTest {

    @Autowired
    private MockMvc mockMvc; // Инжектируем MockMvc для выполнения HTTP запросов к контроллеру

    // Тестируем метод контроллера
    @Test
    public void simpleRestControllerTest() throws Exception {
        // Отправляем POST запрос на "/insurance/travel/" с JSON телом запроса
        mockMvc.perform(post("/insurance/travel/")
                        .content("{" +
                                "\"personFirstName\" : \"Vasja\",\n" +
                                "\"personLastName\" : \"Pupkin\",\n" +
                                "\"agreementDateFrom\" : \"2021-05-25\",\n" +
                                "\"agreementDateTo\" : \"2021-05-29\"\n" +
                                "}")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                // Ожидаем, что статус ответа будет 200 OK
                .andExpect(status().isOk())
                // Ожидаем, что возвращенный JSON содержит указанные значения
                .andExpect(jsonPath("personFirstName", is("Vasja")))
                .andExpect(jsonPath("personLastName", is("Pupkin")))
                .andExpect(jsonPath("agreementDateFrom", is("2021-05-25")))
                .andExpect(jsonPath("agreementDateTo", is("2021-05-29")))
                .andExpect(jsonPath("agreementPrice", is(5))) // Ожидаем, что поле agreementPrice равно 5
                .andReturn(); // Возвращаем результат выполнения запроса
    }

}

