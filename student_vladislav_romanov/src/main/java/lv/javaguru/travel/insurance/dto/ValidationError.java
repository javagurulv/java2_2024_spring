package lv.javaguru.travel.insurance.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationError {

    private String field;
    private String message;

}
