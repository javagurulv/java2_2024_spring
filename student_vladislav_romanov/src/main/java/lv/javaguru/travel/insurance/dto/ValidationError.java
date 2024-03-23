package lv.javaguru.travel.insurance.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationError {

    private String field;
    private String message;

}
