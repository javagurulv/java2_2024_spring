package lv.javaguru.travel.insurance.core.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


public record PersonDTO (
        String personFirstName,
        String personLastName,
        String personCode,
        LocalDate personBirthDate,
        String medicalRiskLimitLevel,
        BigDecimal travelCost,
        List<RiskDTO> risks
){ }
