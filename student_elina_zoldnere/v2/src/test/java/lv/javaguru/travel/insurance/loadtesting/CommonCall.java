package lv.javaguru.travel.insurance.loadtesting;

import com.google.common.base.Stopwatch;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lv.javaguru.travel.insurance.rest.JsonFileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

import static uk.org.webcompere.modelassert.json.JsonAssertions.assertJson;

abstract class CommonCall implements Runnable {

    @Autowired
    private Gson gson = new Gson();

    private static final Logger logger = LoggerFactory.getLogger(CommonCall.class);

    abstract String getFilePath();

    abstract String getUrl();

    abstract String getIdentifier();

    @Override
    public void run() {
        performCall(getFilePath(), getUrl(), getIdentifier());
    }

    private void performCall(String filePath, String url, String identifier) {
        RestTemplate restTemplate = new RestTemplate();
        JsonFileReader reader = new JsonFileReader();

        JsonObject dataJson = gson.fromJson(reader.readJsonFromFile(filePath), JsonObject.class);

        String requestJsonString = gson.toJson(dataJson.getAsJsonObject("request"));
        String expectedResponseString = gson.toJson(dataJson.getAsJsonObject("expectedResponse"));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestJsonString, headers);

        Stopwatch stopwatch = Stopwatch.createStarted();
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
        stopwatch.stop();

        String calculatedResponseAsString = response.getBody();

        logger.info("Request {} processing time: {} (ms)", identifier, stopwatch.elapsed(TimeUnit.MILLISECONDS));

        assertJson(calculatedResponseAsString)
                .where()
                .keysInAnyOrder()
                .arrayInAnyOrder()
                .isEqualTo(expectedResponseString);
    }

}
