package lv.javaguru.travel.insurance.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class TestDataFileProvider {

    @Autowired
    private ResourcePatternResolver resourcePatternResolver;

    public Stream<String> provideTestData(String directory) {
        String directoryPattern = "classpath:rest/" + directory + "/*.json";
        try {
            Resource[] resources = resourcePatternResolver.getResources(directoryPattern);
            return Stream.of(resources)
                    .map(Resource::getFilename);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}