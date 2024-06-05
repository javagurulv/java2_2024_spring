package lv.javaguru.travel.insurance.core.validations.integration;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTOBuilder;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTOBuilder;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.util.SetUpInstancesHelper;
import lv.javaguru.travel.insurance.core.validations.TravelAgreementValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ValidateMedicalRiskLimitLevelIntegrationTest {

    @Autowired
    private TravelAgreementValidator validator;
    @Autowired
    private SetUpInstancesHelper helper;

    @Test
    public void validate_ShouldReturnErrorWhenMedicalRiskLimitLevelIsNotValid() {
        PersonDTO person = PersonDTOBuilder.createPerson()
                .withPersonFirstName("Jānis")
                .withPersonLastName("Bērziņš")
                .withPersonalCode("123456-12345")
                .withPersonBirthdate(helper.newDate("1990.01.01"))
                .withMedicalRiskLimitLevel("INVALID")
                .build();

        AgreementDTO agreement = AgreementDTOBuilder.createAgreement()
                .withDateFrom(helper.newDate("2025.03.10"))
                .withDateTo(helper.newDate("2025.03.11"))
                .withCountry("SPAIN")
                .withSelectedRisks(List.of("TRAVEL_MEDICAL", "TRAVEL_LOSS_BAGGAGE"))
                .withPerson(person)
                .withPremium(BigDecimal.ZERO)
                .build();

        List<ValidationErrorDTO> errors = validator.validate(agreement);

        assertEquals(1, errors.size());
        assertEquals("ERROR_CODE_93", errors.get(0).errorCode());
        assertEquals("Medical Risk Limit Level value INVALID is not supported!", errors.get(0).description());
    }

    @Test
    public void validate_ShouldReturnErrorMedicalRiskLimitLevelIsNull() {
        PersonDTO person = PersonDTOBuilder.createPerson()
                .withPersonFirstName("Jānis")
                .withPersonLastName("Bērziņš")
                .withPersonalCode("123456-12345")
                .withPersonBirthdate(helper.newDate("1990.01.01"))
                .withMedicalRiskLimitLevel(null)
                .build();

        AgreementDTO agreement = AgreementDTOBuilder.createAgreement()
                .withDateFrom(helper.newDate("2025.03.10"))
                .withDateTo(helper.newDate("2025.03.11"))
                .withCountry("SPAIN")
                .withSelectedRisks(List.of("TRAVEL_MEDICAL", "TRAVEL_LOSS_BAGGAGE"))
                .withPerson(person)
                .withPremium(BigDecimal.ZERO)
                .build();

        List<ValidationErrorDTO> errors = validator.validate(agreement);

        assertEquals(1,errors.size());
        assertEquals("ERROR_CODE_8", errors.get(0).errorCode());
        assertEquals("Field medicalRiskLimitLevel is empty when medical risk limit level enabled!",
                errors.get(0).description());
    }

}