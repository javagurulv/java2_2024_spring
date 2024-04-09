package org.javaguru.travel.insurance.core.api.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ValidationErrorDTO {

    private String errorCode;
    private String description;

}
