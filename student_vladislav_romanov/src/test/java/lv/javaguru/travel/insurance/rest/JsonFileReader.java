package lv.javaguru.travel.insurance.rest;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Component
public class JsonFileReader {

//    public String readJsonFromFile(String filePath) throws IOException {
//        StringBuilder result = new StringBuilder();
//
//        File file = ResourceUtils.getFile("classpath:" + filePath);
//
//        try (BufferedReader reader = Files.newBufferedReader(file.toPath())) {
//            String line;
//
//            while ((line = reader.readLine()) != null) {
//                result.append(line).append(System.lineSeparator());
//            }
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return result.toString();
//    }

    public String readJsonFromFile(String filePath) {
        try {
            File file = ResourceUtils.getFile("classpath:" + filePath);
            return new String(Files.readAllBytes(file.toPath()));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
