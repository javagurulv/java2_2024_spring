package lv.javaguru.travel.insurance.core.rest;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import java.io.File;
import java.nio.file.Files;

@Component
public class JsonFileReader {
    String filePath = "resources/rest/TravelCalculatePremiumRequest_personLastName_is_not_entered.json";
    public String readJsonFromFile(String filePath) {
        try {
            File file = ResourceUtils.getFile("classpath:" + filePath);
            return new String(Files.readAllBytes(file.toPath()));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}

