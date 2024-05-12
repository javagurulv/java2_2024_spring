package lv.javaguru.travel.insurance.core.util;

import lv.javaguru.travel.insurance.core.api.dto.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
public class SetUpInstancesHelper {

    public AgreementDTO newAgreementDTO() {
        return AgreementDTOBuilder.createAgreement()
                .withDateFrom(newDate("2025.03.10"))
                .withDateTo(newDate("2025.03.11"))
                .withCountry("SPAIN")
                .withSelectedRisks(List.of("TRAVEL_MEDICAL", "TRAVEL_CANCELLATION", "TRAVEL_LOSS_BAGGAGE"))
                .withPerson(newPersonDTO())
                .withPremium(BigDecimal.ZERO)
                .build();
    }

    public AgreementDTO newTwoPersonsAgreementDTO() {
        return AgreementDTOBuilder.createAgreement()
                .withDateFrom(newDate("2025.03.10"))
                .withDateTo(newDate("2025.03.11"))
                .withCountry("SPAIN")
                .withSelectedRisks(List.of("TRAVEL_MEDICAL", "TRAVEL_CANCELLATION", "TRAVEL_LOSS_BAGGAGE"))
                .withPersons(List.of(newPersonDTO(), newPersonDTO()))
                .withPremium(BigDecimal.ZERO)
                .build();
    }

    public PersonDTO newPersonDTO() {
        return new PersonDTO("Jānis", "Bērziņš",
                newDate("1990.01.01"), "LEVEL_10000", Collections.emptyList());
    }

    public RiskDTO newRiskDTO() {
        return new RiskDTO("TRAVEL_MEDICAL", BigDecimal.TEN);
    }

    public ValidationErrorDTO newValidationErrorDTO() {
        return new ValidationErrorDTO("errorCode", "description");
    }

    public PersonDTO newPersonWithRisksDTO() {
        return new PersonDTO("Jānis", "Bērziņš",
                newDate("1990.01.01"), "LEVEL_10000", List.of(newRiskDTO()));
    }

    public Date newDate(String dateStr) {
        try {
            return new SimpleDateFormat("yyyy.MM.dd").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}