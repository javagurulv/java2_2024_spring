package lv.javaguru.travel.insurance.loadtesting;

import com.google.common.base.Stopwatch;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lv.javaguru.travel.insurance.rest.JsonFileReader;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

import static uk.org.webcompere.modelassert.json.JsonAssertions.assertJson;

public class RestCallExample {
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final JsonFileReader reader = new JsonFileReader();
    private static final Gson gson = new Gson();

    private static final String filePathV2 = "v2/agreement/AgreementV2Test_98_all_fields_are_present_and_valid-1.json";
    private static final String urlV2 = "http://localhost:8080/insurance/travel/api/v2/";

    public static void main(String[] args) {
        JsonObject dataJson = gson.fromJson(reader.readJsonFromFile(filePathV2), JsonObject.class);

        String requestJsonString = gson.toJson(dataJson.getAsJsonObject("request"));
        String expectedResponseString = gson.toJson(dataJson.getAsJsonObject("expectedResponse"));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestJsonString, headers);

        Stopwatch stopwatch = Stopwatch.createStarted();
        ResponseEntity<String> response = restTemplate.postForEntity(urlV2, requestEntity, String.class);
        stopwatch.stop();

        String calculatedResponseAsString = response.getBody();

        System.out.println("Request processing time: " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + " (ms)");

        assertJson(calculatedResponseAsString)
                .where()
                .keysInAnyOrder()
                .arrayInAnyOrder()
                .isEqualTo(expectedResponseString);
    }

}
