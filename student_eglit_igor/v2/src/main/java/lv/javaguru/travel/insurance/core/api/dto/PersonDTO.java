package lv.javaguru.travel.insurance.core.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


public record PersonDTO (
        String personFirstName,
        String personLastName,
        LocalDate personBirthDate,
        String medicalRiskLimitLevel,
        List<RiskDTO> risks
){ }
