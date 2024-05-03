package lv.javaguru.travel.insurance.dto.v1;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.javaguru.travel.insurance.dto.util.BigDecimalSerializer;
import lv.javaguru.travel.insurance.dto.CoreResponse;
import lv.javaguru.travel.insurance.dto.RiskPremium;
import lv.javaguru.travel.insurance.dto.ValidationError;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TravelCalculatePremiumResponseV1 extends CoreResponse {

    private String personFirstName;
    private String personLastName;
    private LocalDate personBirthDate;
    private LocalDate agreementDateFrom;
    private LocalDate agreementDateTo;
    private String country;
    private List<RiskPremium> riskPremiums;
    private String medicalRiskLimitLevel;
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal agreementPremium;
    public TravelCalculatePremiumResponseV1(List<ValidationError> errors) {
        super(errors);
    }
}
