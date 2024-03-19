package lv.javaguru.travel.insurance.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CoreResponse {

    private List<ValidationError> errors;

    public boolean hasErrors() {
        return errors != null && !errors.isEmpty();
    }

}
