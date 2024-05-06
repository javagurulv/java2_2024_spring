package lv.javaguru.travel.insurance.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class TravelCalculatePremiumResponse extends CoreResponse {

    private String personFirstName;
    private String personLastName;
    private LocalDate agreementDateFrom;
    private LocalDate agreementDateTo;
    private BigDecimal agreementPremium;
    private List<RiskPremium> risks;

    public TravelCalculatePremiumResponse(List<ValidationError> errors) {
        super(errors);
    }

}
