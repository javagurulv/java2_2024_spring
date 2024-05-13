package lv.javaguru.travel.insurance.core.util;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTOBuilder;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTOBuilder;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTOBuilder;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTOBuilder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        return PersonDTOBuilder.createPerson()
                .withPersonFirstName("Jānis")
                .withPersonLastName("Bērziņš")
                .withPersonBirthdate(newDate("1990.01.01"))
                .withMedicalRiskLimitLevel("LEVEL_10000")
                .build();
    }

    public RiskDTO newRiskDTO() {
        return RiskDTOBuilder.createRisk()
                .withRiskIc("TRAVEL_MEDICAL")
                .withPremium(BigDecimal.TEN)
                .build();
    }

    public ValidationErrorDTO newValidationErrorDTO() {
        return ValidationErrorDTOBuilder.createValidationError()
                .withErrorCode("errorCode")
                .withDescription("description")
                .build();
    }

    public PersonDTO newPersonWithRiskDTO() {
        return PersonDTOBuilder.createPerson()
                .withPersonFirstName("Jānis")
                .withPersonLastName("Bērziņš")
                .withPersonBirthdate(newDate("1990.01.01"))
                .withMedicalRiskLimitLevel("LEVEL_10000")
                .withPersonRisk(newRiskDTO())
                .build();
    }

    public Date newDate(String dateStr) {
        try {
            return new SimpleDateFormat("yyyy.MM.dd").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}