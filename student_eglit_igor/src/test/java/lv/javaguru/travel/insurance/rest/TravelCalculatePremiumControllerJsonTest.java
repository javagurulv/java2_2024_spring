package lv.javaguru.travel.insurance.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelCalculatePremiumControllerJsonTest {
    @Autowired
    private MockMvc mockMvc; // Позволяет тестировать контроллеры без запуска сервера приложений
    // и без отправки HTTP-запросов на сервер.
    // Вместо этого он создает фейковый запрос
    // и передает его контроллеру.
    @Autowired
    private JsonFileReader jsonFileReader;

    @Test
    public void shouldCalculatePremiumWithAllFieldsValid() throws Exception {
        var actualJson = getActualJson("rest/allFieldsValidRequest.json");
        var expectedJson = getExpectedJson("rest/allFieldsValidResponse.json");
        JSONAssert.assertEquals(expectedJson, actualJson, false);
        /*true: Этот параметр указывает на строгое сравнение JSON-структур.
        Когда он установлен в true, это означает, что порядок элементов в
        массивах и поля в объектах должны точно совпадать в обоих JSON-структурах.
        Если установлено значение false, то порядок элементов и полей не имеет
        значения при сравнении.*/
    }

    @Test
    public void shouldReturnErrorsMessageForAllFieldsEmpty() throws Exception {
        var actualJson = getActualJson("rest/allFieldsEmptyRequest.json");
        var expectedJson = getExpectedJson("rest/allFieldsEmptyResponse.json");
        JSONAssert.assertEquals(expectedJson, actualJson, false);
    }

    @Test
    public void shouldReturnErrorMessageForEmptyFirstName() throws Exception {
        var actualJson = getActualJson("rest/firstNameEmptyRequest.json");
        var expectedJson = getExpectedJson("rest/firstNameEmptyResponse.json");
        JSONAssert.assertEquals(expectedJson, actualJson, false);
    }

    @Test
    public void shouldReturnErrorMessageForNullFirstName() throws Exception {
        var actualJson = getActualJson("rest/firstNameNullRequest.json");
       var expectedJson = getExpectedJson("rest/firstNameNullResponse.json");
        JSONAssert.assertEquals(expectedJson, actualJson, false);
    }

    @Test
    public void shouldReturnErrorMessageForEmptyLastName() throws Exception {
        var actualJson = getActualJson("rest/lastNameEmptyRequest.json");
        var expectedJson = getExpectedJson("rest/lastNameEmptyResponse.json");
        JSONAssert.assertEquals(expectedJson, actualJson, false);
    }

    @Test
    public void shouldReturnErrorMessageForNullLastName() throws Exception {
        var actualJson = getActualJson("rest/lastNameNullRequest.json");
        var expectedJson = getExpectedJson("rest/lastNameNullResponse.json");
        JSONAssert.assertEquals(expectedJson, actualJson, false);
    }

    @Test
    public void shouldReturnErrorMessageForEmptyDateFrom() throws Exception {
        var actualJson = getActualJson("rest/dateFromEmptyRequest.json");
        var expectedJson = getExpectedJson("rest/dateFromEmptyResponse.json");
        JSONAssert.assertEquals(expectedJson, actualJson, false);
    }

    @Test
    public void shouldReturnErrorMessageForNullDateFrom() throws Exception {
        var actualJson = getActualJson("rest/dateFromNullRequest.json");
        var expectedJson = getExpectedJson("rest/dateFromNullResponse.json");
        JSONAssert.assertEquals(expectedJson, actualJson, false);
    }

    @Test
    public void shouldReturnErrorMessageForEmptyDateTo() throws Exception {
        var actualJson = getActualJson("rest/dateToEmptyRequest.json");
        var expectedJson = getExpectedJson("rest/dateToEmptyResponse.json");
        JSONAssert.assertEquals(expectedJson, actualJson, false);
    }

    @Test
    public void shouldReturnErrorMessageForNullDateTo() throws Exception {
        var actualJson = getActualJson("rest/dateToNullRequest.json");
        var expectedJson = getExpectedJson("rest/dateToNullResponse.json");
        JSONAssert.assertEquals(expectedJson, actualJson, false);
    }

    @Test
    public void shouldReturnErrorMessageForDateToBeforeDateFrom() throws Exception {
        var actualJson = getActualJson("rest/dateToBeforeDateFromRequest.json");
        var expectedJson = getExpectedJson("rest/dateToBeforeDateFromResponse.json");
        JSONAssert.assertEquals(expectedJson, actualJson, false);
    }
    @Test
    public void shouldReturnErrorMessageForDateFromIsInThePast() throws Exception {
        var actualJson = getActualJson("rest/dateFromInThePastRequest.json");
        var expectedJson = getExpectedJson("rest/dateFromInThePastResponse.json");
        JSONAssert.assertEquals(expectedJson, actualJson, false);
    }
    @Test
    public void shouldReturnErrorMessageForDateToIsInThePastAndBeforeDateFrom() throws Exception {
        var actualJson = getActualJson("rest/dateToIsInThePastAndBeforeDateFromRequest.json");
        var expectedJson = getExpectedJson("rest/dateToIsInThePastAndBeforeDateFromResponse.json");
        JSONAssert.assertEquals(expectedJson, actualJson, false);
    }

    private String getActualJson(String filePath) throws Exception {
        return mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile(filePath))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))// Устанавливаем заголовок Content-Type для запроса в виде JSON данных
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }
    private String getExpectedJson(String filePath) throws IOException {
        return jsonFileReader.readJsonFromFile(filePath);
    }
}