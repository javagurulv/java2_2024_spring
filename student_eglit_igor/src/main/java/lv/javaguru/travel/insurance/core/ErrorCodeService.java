package lv.javaguru.travel.insurance.core;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ResourceBundle;
@Component
@Configuration

public class ErrorCodeService {

    public String getErrorCodeDescription(String errorCode) {

        ResourceBundle bundle;
        try {
            bundle = ResourceBundle.getBundle("errorCodes");
        } catch (Exception e) {
            throw new RuntimeException("no errorCodes.properties found "+e);
        }
        String description;
        try {
            description = bundle.getString(errorCode);
        } catch (Exception e) {
            throw new RuntimeException("no such error code found in error codes list "+e);
        }
        return description;
    }
}
//ResourceBundle - это класс Java, который позволяет загружать локализованные ресурсы,
// такие как строки сообщений, из файлов свойств.
// используем bundle для получения строки, связанной с ключом errorCode из файла свойств