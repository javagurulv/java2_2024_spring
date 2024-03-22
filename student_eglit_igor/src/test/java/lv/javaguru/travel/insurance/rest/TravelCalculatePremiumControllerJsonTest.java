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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelCalculatePremiumControllerJsonTest {
    @Autowired
    private MockMvc mockMvc;
    JsonFileReader jsonFileReader = new JsonFileReader();

    @Test
    public void shouldCalculatePremiumWithAllFieldsValid() throws Exception {
        String actualJson = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("allFieldsValidRequest.json"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))// Устанавливаем заголовок Content-Type для запроса в виде JSON данных
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();// Получаем ответ в виде строки
        String expectedJson = jsonFileReader.readJsonFromFile("allFieldsValidResponse.json");
        JSONAssert.assertEquals(expectedJson, actualJson, true);
        /*true: Этот параметр указывает на строгое сравнение JSON-структур.
        Когда он установлен в true, это означает, что порядок элементов в
        массивах и поля в объектах должны точно совпадать в обоих JSON-структурах.
        Если установлено значение false, то порядок элементов и полей не имеет
        значения при сравнении.*/
    }
    @Test
    public void shouldReturnErrorsMessageForAllFieldsEmpty() throws Exception {
        String actualJson = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("allFieldsEmptyRequest.json"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        String expectedJson = jsonFileReader.readJsonFromFile("allFieldsEmptyResponse.json");
        JSONAssert.assertEquals(expectedJson, actualJson, false);
    }
    @Test
    public void shouldReturnErrorMessageForEmptyFirstName() throws Exception {
        String actualJson = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("firstNameEmptyRequest.json"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        String expectedJson = jsonFileReader.readJsonFromFile("firstNameEmptyResponse.json");
        JSONAssert.assertEquals(expectedJson, actualJson, false);
    }
    @Test
    public void shouldReturnErrorMessageForNullFirstName() throws Exception {
        String actualJson = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("firstNameNullRequest.json"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        String expectedJson = jsonFileReader.readJsonFromFile("firstNameNullResponse.json");
        JSONAssert.assertEquals(expectedJson, actualJson, false);
    }
    @Test
    public void shouldReturnErrorMessageForEmptyLastName() throws Exception {
        String actualJson = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("lastNameEmptyRequest.json"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        String expectedJson = jsonFileReader.readJsonFromFile("lastNameEmptyResponse.json");
        JSONAssert.assertEquals(expectedJson, actualJson, false);
    }
    @Test
    public void shouldReturnErrorMessageForNullLastName() throws Exception {
        String actualJson = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("lastNameNullRequest.json"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        String expectedJson = jsonFileReader.readJsonFromFile("lastNameNullResponse.json");
        JSONAssert.assertEquals(expectedJson, actualJson, false);
    }
    @Test
    public void shouldReturnErrorMessageForEmptyDateFrom() throws Exception {
        String actualJson = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("dateFromEmptyRequest.json"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        String expectedJson = jsonFileReader.readJsonFromFile("dateFromEmptyResponse.json");
        JSONAssert.assertEquals(expectedJson, actualJson, false);
    }
    @Test
    public void shouldReturnErrorMessageForNullDateFrom() throws Exception {
        String actualJson = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("dateFromNullRequest.json"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        String expectedJson = jsonFileReader.readJsonFromFile("dateFromNullResponse.json");
        JSONAssert.assertEquals(expectedJson, actualJson, false);
    }
    @Test
    public void shouldReturnErrorMessageForEmptyDateTo() throws Exception {
        String actualJson = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("dateToEmptyRequest.json"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        String expectedJson = jsonFileReader.readJsonFromFile("dateToEmptyResponse.json");
        JSONAssert.assertEquals(expectedJson, actualJson, false);
    }
    @Test
    public void shouldReturnErrorMessageForNullDateTo() throws Exception {
        String actualJson = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("dateToNullRequest.json"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        String expectedJson = jsonFileReader.readJsonFromFile("dateToNullResponse.json");
        JSONAssert.assertEquals(expectedJson, actualJson, false);
    }
    @Test
    public void shouldReturnErrorMessageForDateToBeforeDateFrom() throws Exception {
        String actualJson = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonFileReader.readJsonFromFile("dateToBeforeDateFromRequest.json"))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        String expectedJson = jsonFileReader.readJsonFromFile("dateToBeforeDateFromResponse.json");
        JSONAssert.assertEquals(expectedJson, actualJson, false);
    }
}