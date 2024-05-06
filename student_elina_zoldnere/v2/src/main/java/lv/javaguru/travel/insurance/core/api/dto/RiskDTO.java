package lv.javaguru.travel.insurance.core.api.dto;

import java.math.BigDecimal;

public record RiskDTO(String riskIc, BigDecimal premium) {
}