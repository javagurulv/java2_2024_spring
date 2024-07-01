package lv.javaguru.travel.insurance.loadtesting;

import com.google.common.base.Stopwatch;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lv.javaguru.travel.insurance.rest.JsonFileReader;
import org.apache.commons.lang3.tuple.Pair;
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

abstract class CallTemplate implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(CallTemplate.class);

    private final LoadTestingStatistic statistic;

    @Autowired
    private Gson gson = new Gson();

    CallTemplate(LoadTestingStatistic statistic) {
        this.statistic = statistic;
    }

    abstract String getFilePath();

    abstract String getUrl();

    abstract String getIdentifier();

    @Override
    public void run() {
        performCall(getFilePath(), getUrl(), getIdentifier());
    }

    private void performCall(String filePath, String url, String identifier) {
        Pair<String, String> testData = readTestData(filePath);
        String requestJsonString = testData.getLeft();
        String expectedResponseString = testData.getRight();

        Stopwatch stopwatch = Stopwatch.createStarted();
        executeCallAndCompare(requestJsonString, expectedResponseString, url);
        stopwatch.stop();

        Long processingTimeMillis = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        if (statistic != null) {
            statistic.addExecutionTime(processingTimeMillis);
            logger.info("Request {} processing time: {} (ms)", identifier, processingTimeMillis);
        } else {
            logger.info("Warm-up {} processing time: {} (ms), not included in statistics.",
                    identifier, processingTimeMillis);
        }
    }

    private void executeCallAndCompare(String requestJsonString, String expectedResponseString, String url) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestJsonString, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);

        String calculatedResponseAsString = response.getBody();

        assertJson(calculatedResponseAsString)
                .where()
                .keysInAnyOrder()
                .arrayInAnyOrder()
                .isEqualTo(expectedResponseString);
    }

    private Pair<String, String> readTestData(String filePath) {
        JsonFileReader reader = new JsonFileReader();
        JsonObject dataJson = gson.fromJson(reader.readJsonFromFile(filePath), JsonObject.class);

        String requestJsonString = gson.toJson(dataJson.getAsJsonObject("request"));
        String expectedResponseString = gson.toJson(dataJson.getAsJsonObject("expectedResponse"));

        return Pair.of(requestJsonString, expectedResponseString);
    }

}
