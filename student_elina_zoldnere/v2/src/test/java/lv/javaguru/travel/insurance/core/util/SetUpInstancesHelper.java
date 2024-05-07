package lv.javaguru.travel.insurance.core.util;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
public class SetUpInstancesHelper {

    public AgreementDTO newAgreementDTO() {
        return new AgreementDTO(
                new Date(2025 - 1900, 2, 10),
                new Date(2025 - 1900, 2, 11),
                "SPAIN",
                "LEVEL_10000",
                List.of("TRAVEL_MEDICAL", "TRAVEL_CANCELLATION", "TRAVEL_LOSS_BAGGAGE"),
                List.of(newPersonDTO()),
                BigDecimal.ZERO);
    }

    public AgreementDTO newTwoPersonsAgreementDTO() {
        return new AgreementDTO(
                new Date(2025 - 1900, 2, 10),
                new Date(2025 - 1900, 2, 11),
                "SPAIN",
                "LEVEL_10000",
                List.of("TRAVEL_MEDICAL", "TRAVEL_CANCELLATION", "TRAVEL_LOSS_BAGGAGE"),
                List.of(newPersonDTO(), newPersonDTO()),
                BigDecimal.ZERO);
    }

    public PersonDTO newPersonDTO() {
        return new PersonDTO("Jānis", "Bērziņš",
                new Date(1990 - 1900, 0, 1), Collections.emptyList());
    }

    public RiskDTO newRiskDTO() {
        return new RiskDTO("TRAVEL_MEDICAL", BigDecimal.ZERO);
    }

}