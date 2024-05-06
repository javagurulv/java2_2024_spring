package lv.javaguru.travel.insurance.core.api.dto;

import java.util.Date;
import java.util.List;

public record PersonDTO(String personFirstName,
                        String personLastName,
                        Date personBirthDate,
                        List<RiskDTO> personRisks) {

}
