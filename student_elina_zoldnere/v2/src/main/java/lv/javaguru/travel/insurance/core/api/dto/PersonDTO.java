package lv.javaguru.travel.insurance.core.api.dto;

import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public record PersonDTO(
        @Size(max = 200, message = "First name must not exceed 200 characters!")
        String personFirstName,

        @Size(max=200, message = "Last name must not exceed 200 characters!")
        String personLastName,

        String personalCode,

        Date personBirthDate,

        String medicalRiskLimitLevel,

        BigDecimal travelCost,

        List<RiskDTO> personRisks) {

    // Copy constructor that allows to add personRisks, effectively copying other values
    public PersonDTO withRisks(List<RiskDTO> personRisks) {
        return new PersonDTO(
                personFirstName,
                personLastName,
                personalCode,
                personBirthDate,
                medicalRiskLimitLevel,
                travelCost,
                personRisks);
    }

}
