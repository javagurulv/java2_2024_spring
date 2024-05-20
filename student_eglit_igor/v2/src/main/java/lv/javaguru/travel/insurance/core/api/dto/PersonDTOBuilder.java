package lv.javaguru.travel.insurance.core.api.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersonDTOBuilder {

    private String personFirstName;
    private String personLastName;
    private LocalDate personBirthDate;
    private String medicalRiskLimitLevel;
    private List<RiskDTO> risks  = new ArrayList<>();;

    public static PersonDTOBuilder createPerson() {
        return new PersonDTOBuilder();
    }

    public PersonDTO build(){
        return new PersonDTO(
                personFirstName,
                personLastName,
                personBirthDate,
                medicalRiskLimitLevel,
                risks
        );
    }

    public PersonDTOBuilder withFirstName(String personFirstName){
        this.personFirstName = personFirstName;
        return this;
    }
    public PersonDTOBuilder withLastName(String personLastName){
        this.personLastName = personLastName;
        return this;
    }
    public PersonDTOBuilder withBirthDate(LocalDate personBirthDate){
        this.personBirthDate = personBirthDate;
        return this;
    }
    public PersonDTOBuilder withMedicalRiskLimitLevel(String medicalRiskLimitLevel){
        this.medicalRiskLimitLevel = medicalRiskLimitLevel;
        return this;
    }
    public PersonDTOBuilder withRisk(RiskDTO risk){
        this.risks.add(risk);
        return this;
    }
    public PersonDTOBuilder withRisks(List<RiskDTO> risks){
        this.risks = risks;
        return this;
    }
}
