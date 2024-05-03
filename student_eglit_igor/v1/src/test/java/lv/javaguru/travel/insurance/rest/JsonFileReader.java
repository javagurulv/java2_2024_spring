package lv.javaguru.travel.insurance.rest;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


@Component
public class JsonFileReader {
    public String readJsonFromFile(String filePath) throws IOException {
        try {
            File file = ResourceUtils.getFile("classpath:" + filePath);
            return new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            throw new IOException(filePath+" File not found");
        }

        /*try (InputStream inputStream = getClass().getResourceAsStream(filePath)) {
            if (inputStream == null) {
                throw new IOException(filePath + " File not found");
            }
            return new String(inputStream.readAllBytes());*/
    }
}
