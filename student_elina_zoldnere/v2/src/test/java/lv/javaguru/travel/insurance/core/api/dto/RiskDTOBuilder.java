package lv.javaguru.travel.insurance.core.api.dto;

import java.math.BigDecimal;

public class RiskDTOBuilder {
    private String riskIc;
    private BigDecimal premium;

    public static RiskDTOBuilder createRisk() {
        return new RiskDTOBuilder();
    }

    public RiskDTO build() {
        return new RiskDTO(
                riskIc,
                premium);
    }

    public RiskDTOBuilder withRiskIc(String riskIc) {
        this.riskIc = riskIc;
        return this;
    }

    public RiskDTOBuilder withPremium(BigDecimal premium) {
        this.premium = premium;
        return this;
    }

}
