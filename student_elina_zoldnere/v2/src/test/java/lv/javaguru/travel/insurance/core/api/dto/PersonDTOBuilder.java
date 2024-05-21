package lv.javaguru.travel.insurance.core.api.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersonDTOBuilder {
    private String personFirstName;
    private String personLastName;
    private String personalCode;
    private Date personBirthDate;
    private String medicalRiskLimitLevel;
    private BigDecimal travelCost;
    private List<RiskDTO> personRisks = new ArrayList<>();

    public static PersonDTOBuilder createPerson() {
        return new PersonDTOBuilder();
    }

    public PersonDTO build() {
        return new PersonDTO(
                personFirstName,
                personLastName,
                personalCode,
                personBirthDate,
                medicalRiskLimitLevel,
                travelCost,
                personRisks);
    }

    public PersonDTOBuilder withPersonFirstName(String personFirstName) {
        this.personFirstName = personFirstName;
        return this;
    }

    public PersonDTOBuilder withPersonLastName(String personLastName) {
        this.personLastName = personLastName;
        return this;
    }

    public PersonDTOBuilder withPersonalCode(String personalCode) {
        this.personalCode = personalCode;
        return this;
    }

    public PersonDTOBuilder withPersonBirthdate(Date personBirthDate) {
        this.personBirthDate = personBirthDate;
        return this;
    }

    public PersonDTOBuilder withMedicalRiskLimitLevel(String medicalRiskLimitLevel) {
        this.medicalRiskLimitLevel = medicalRiskLimitLevel;
        return this;
    }

    public PersonDTOBuilder withTravelCost(BigDecimal travelCost) {
        this.travelCost = travelCost;
        return this;
    }

    public PersonDTOBuilder withPersonRisk(RiskDTO personRisk) {
        this.personRisks.add(personRisk);
        return this;
    }

    public PersonDTOBuilder withPersonRisks(List<RiskDTO> personRisks) {
        this.personRisks = personRisks;
        return this;
    }

}
