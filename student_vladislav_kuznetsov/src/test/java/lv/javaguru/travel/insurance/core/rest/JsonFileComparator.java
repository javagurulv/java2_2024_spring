package lv.javaguru.travel.insurance.core.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JsonFileComparator {
    public boolean compareJsonFile (String jsonString1, String jsonString2){
        ObjectMapper mapper = new ObjectMapper();
        boolean result = false;
        try {
            JsonNode jsonNode1 = mapper.readTree(jsonString1);
            JsonNode jsonNode2 = mapper.readTree(jsonString2);
            if (jsonNode1.equals(jsonNode2)) {
                result = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}

