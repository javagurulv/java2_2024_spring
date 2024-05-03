package lv.javaguru.travel.insurance.dto.v2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
public class PersonRequestDTO {
    private String personFirstName;
    private String personLastName;

    private LocalDate personBirthDate;
}
