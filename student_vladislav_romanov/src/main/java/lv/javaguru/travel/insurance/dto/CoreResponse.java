package lv.javaguru.travel.insurance.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoreResponse {

    private List<ValidationError> errors;

    public boolean hasErrors() {
        return errors != null && !errors.isEmpty();
    }

}