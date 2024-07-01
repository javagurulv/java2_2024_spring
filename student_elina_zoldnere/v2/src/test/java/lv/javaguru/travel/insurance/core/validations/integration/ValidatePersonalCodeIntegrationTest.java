package lv.javaguru.travel.insurance.core.validations.integration;

import lv.javaguru.travel.insurance.core.api.dto.*;
import lv.javaguru.travel.insurance.core.util.DateHelper;
import lv.javaguru.travel.insurance.core.validations.TravelAgreementValidator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
public class ValidatePersonalCodeIntegrationTest {

    @Autowired
    private TravelAgreementValidator validator;
    @Autowired
    private DateHelper helper;

    @Test
    void validate_ShouldReturnErrorWhenPersonalCodeIsNull() {
        PersonDTO person = PersonDTOBuilder.createPerson()
                .withPersonFirstName("Jānis")
                .withPersonLastName("Bērziņš")
                .withPersonalCode(null)
                .withPersonBirthdate(helper.newDate("1990.01.01"))
                .withMedicalRiskLimitLevel("LEVEL_10000")
                .build();

        AgreementDTO agreement = AgreementDTOBuilder.createAgreement()
                .withDateFrom(helper.newDate("2025.03.10"))
                .withDateTo(helper.newDate("2025.03.11"))
                .withCountry("SPAIN")
                .withSelectedRisk("TRAVEL_MEDICAL")
                .withPerson(person)
                .build();

        List<ValidationErrorDTO> result = validator.validate(agreement);

        assertThat(result)
                .hasSize(1)
                .extracting("errorCode", "description")
                .containsExactly(
                        Assertions.tuple("ERROR_CODE_9", "Field personalCode is empty!"));
    }

    @Test
    void validate_ShouldReturnErrorWhenPersonalCodeIsWrongFormat() {
        PersonDTO person = PersonDTOBuilder.createPerson()
                .withPersonFirstName("Jānis")
                .withPersonLastName("Bērziņš")
                .withPersonalCode("12345612345")
                .withPersonBirthdate(helper.newDate("1990.01.01"))
                .withMedicalRiskLimitLevel("LEVEL_10000")
                .build();

        AgreementDTO agreement = AgreementDTOBuilder.createAgreement()
                .withDateFrom(helper.newDate("2025.03.10"))
                .withDateTo(helper.newDate("2025.03.11"))
                .withCountry("SPAIN")
                .withSelectedRisk("TRAVEL_MEDICAL")
                .withPerson(person)
                .build();

        List<ValidationErrorDTO> result = validator.validate(agreement);

        assertThat(result)
                .hasSize(1)
                .extracting("errorCode", "description")
                .containsExactly(
                        Assertions.tuple("ERROR_CODE_15", "Wrong personalCode format! Use 123456-12345."));
    }

}