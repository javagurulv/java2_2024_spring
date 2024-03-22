package lv.javaguru.travel.insurance.rest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;




public class JsonFileReader {
    public String readJsonFromFile(String filePath) throws IOException {
        try {
            Path directoryPath = Path.of("C:\\Users\\igegl\\IdeaProjects\\java2_2024_spring\\student_eglit_igor\\src\\test\\resources\\rest");
            return Files.readString(Path.of(directoryPath + "\\" + filePath));
        } catch (IOException e) {
            throw new IOException(filePath+" File not found");
        }
    }
}
