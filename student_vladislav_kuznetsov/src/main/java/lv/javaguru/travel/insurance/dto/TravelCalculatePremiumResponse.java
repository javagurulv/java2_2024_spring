package lv.javaguru.travel.insurance.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.javaguru.travel.insurance.core.util.BigDecimalSerializer;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelCalculatePremiumResponse extends CoreResponse{

    private String personFirstName;
    private String personLastName;
    private Date agreementDateFrom;
    private Date agreementDateTo;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date personBirthDate;
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal agreementPremium;
    private List<RiskPremium> risks;
    private String medicalRiskLimitLevel;

    public TravelCalculatePremiumResponse(List<ValidationError> errors) {
        super(errors);
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Date getAgreementDateFrom() {
        return agreementDateFrom;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Date getAgreementDateTo() {
        return agreementDateTo;
    }
}
