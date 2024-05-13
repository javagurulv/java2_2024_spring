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
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ValidateSelectedRisksIntegrationTest {

    @Autowired
    private TravelAgreementValidator validator;
    @Autowired
    private SetUpInstancesHelper helper;

    @Test
    public void validate_ShouldReturnErrorWhenOneSelectedRiskIsNotValid() {
        PersonDTO person = PersonDTOBuilder.createPerson()
                .withPersonFirstName("Jānis")
                .withPersonLastName("Bērziņš")
                .withPersonBirthdate(helper.newDate("1990.01.01"))
                .withMedicalRiskLimitLevel("LEVEL_10000")
                .build();

        AgreementDTO agreement = AgreementDTOBuilder.createAgreement()
                .withDateFrom(helper.newDate("2025.03.10"))
                .withDateTo(helper.newDate("2025.03.11"))
                .withCountry("SPAIN")
                .withSelectedRisks(List.of("TRAVEL_MEDICAL", "INVALID", "TRAVEL_LOSS_BAGGAGE"))
                .withPerson(person)
                .withPremium(BigDecimal.ZERO)
                .build();

        List<ValidationErrorDTO> errors = validator.validate(agreement);

        assertEquals(errors.size(), 1);
        assertEquals("ERROR_CODE_91", errors.get(0).errorCode());
        assertEquals("Risk type value INVALID is not supported!", errors.get(0).description());
    }

    @Test
    public void validate_ShouldReturnErrorWhenTwoSelectedRisksAreNotValid() {
        PersonDTO person = PersonDTOBuilder.createPerson()
                .withPersonFirstName("Jānis")
                .withPersonLastName("Bērziņš")
                .withPersonBirthdate(helper.newDate("1990.01.01"))
                .withMedicalRiskLimitLevel("LEVEL_10000")
                .build();

        AgreementDTO agreement = AgreementDTOBuilder.createAgreement()
                .withDateFrom(helper.newDate("2025.03.10"))
                .withDateTo(helper.newDate("2025.03.11"))
                .withCountry("SPAIN")
                .withSelectedRisks(List.of("TRAVEL_MEDICAL", "INVALID1", "INVALID2"))
                .withPerson(person)
                .withPremium(BigDecimal.ZERO)
                .build();

        List<ValidationErrorDTO> errors = validator.validate(agreement);

        assertEquals(2, errors.size());

        List<String> expectedDescriptions = Arrays.asList(
                "Risk type value INVALID1 is not supported!",
                "Risk type value INVALID2 is not supported!");

        assertTrue(errors.stream().anyMatch(error ->
                "ERROR_CODE_91".equals(error.errorCode()) &&
                        expectedDescriptions.contains(error.description())));

        assertTrue(errors.stream().anyMatch(error ->
                "ERROR_CODE_91".equals(error.errorCode()) &&
                        expectedDescriptions.contains(error.description())));
    }

    @Test
    public void validate_ShouldReturnErrorWhenSelectedRiskIsNull() {
        PersonDTO person = PersonDTOBuilder.createPerson()
                .withPersonFirstName("Jānis")
                .withPersonLastName("Bērziņš")
                .withPersonBirthdate(helper.newDate("1990.01.01"))
                .withMedicalRiskLimitLevel("LEVEL_10000")
                .build();

        AgreementDTO agreement = AgreementDTOBuilder.createAgreement()
                .withDateFrom(helper.newDate("2025.03.10"))
                .withDateTo(helper.newDate("2025.03.11"))
                .withCountry("SPAIN")
                .withSelectedRisks(null)
                .withPerson(person)
                .withPremium(BigDecimal.ZERO)
                .build();

        List<ValidationErrorDTO> errors = validator.validate(agreement);

        assertEquals(errors.size(), 1);
        assertEquals("ERROR_CODE_5", errors.get(0).errorCode());
        assertEquals("Field selectedRisks is empty!", errors.get(0).description());
    }

}