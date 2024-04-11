package lv.javaguru.travel.insurance.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class PropertyResolver {

    @Autowired
    private Environment env;

    public String getPropertyDescription(String key) {
        return env.getProperty(key);
    }

}