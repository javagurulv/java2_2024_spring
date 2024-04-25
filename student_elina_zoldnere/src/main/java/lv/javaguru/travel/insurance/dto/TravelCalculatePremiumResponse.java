package lv.javaguru.travel.insurance.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lv.javaguru.travel.insurance.core.util.BigDecimalSerializer;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelCalculatePremiumResponse extends CoreResponse {

    private String personFirstName;

    private String personLastName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date personBirthDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date agreementDateFrom;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date agreementDateTo;

    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal agreementPremium;

    private List<RiskPremium> riskPremiums;

    private String country;

    private String medicalRiskLimitLevel;

    public TravelCalculatePremiumResponse(List<ValidationError> errors) {
        super(errors);
    }

}
