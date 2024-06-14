package lv.javaguru.travel.insurance.dto;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TravelCalculatePremiumResponse extends CoreResponse {

    private String personFirstName;
    private String personLastName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date agreementDateFrom;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date agreementDateTo;

    private BigDecimal agreementPremium;

    private List<RiskPremium> risks;

    public TravelCalculatePremiumResponse(List<ValidationErrors> validationErrors) {
        super(validationErrors);
    }

    private String country;
}