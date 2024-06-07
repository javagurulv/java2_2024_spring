package lv.javaguru.travel.insurance.dto.serialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RiskSerialDTO {

    private String riskIc;

    private BigDecimal premium;

}
