package org.javaguru.travel.insurance.rest;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.nio.file.Files;

public class JsonFileReader {
    public String readJsonFromFile(String filePath) {
        try {
            File file = ResourceUtils.getFile("classpath:" + filePath);
            return new String(Files.readAllBytes(file.toPath()));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

   /* public static void main(String[] args) {
        JsonFileReader jsonFileReader = new JsonFileReader();
        System.out.println(jsonFileReader.readJsonFromFile("rest/TravelCalculatePremiumRequestWithAllFieldsNull.json"));
    }*/



}
