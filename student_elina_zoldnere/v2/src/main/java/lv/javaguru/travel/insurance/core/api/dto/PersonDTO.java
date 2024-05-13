package lv.javaguru.travel.insurance.core.api.dto;

import java.util.Date;
import java.util.List;

public record PersonDTO(String personFirstName,
                        String personLastName,

                        Date personBirthDate,

                        String medicalRiskLimitLevel,

                        List<RiskDTO> personRisks) {

    // Copy constructor that allows to add personRisks, effectively copying other values
    public PersonDTO withRisks(List<RiskDTO> personRisks) {
        return new PersonDTO(personFirstName, personLastName, personBirthDate, medicalRiskLimitLevel, personRisks);
    }

}
