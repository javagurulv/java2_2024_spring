package lv.javaguru.travel.insurance.rest;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.nio.file.Files;

@Component
public class JsonFileReader {


    public String getJsonFromFile(String filePath) {
        try {
            File file = ResourceUtils.getFile("classpath:" + filePath);
            return new String(Files.readAllBytes(file.toPath()));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}