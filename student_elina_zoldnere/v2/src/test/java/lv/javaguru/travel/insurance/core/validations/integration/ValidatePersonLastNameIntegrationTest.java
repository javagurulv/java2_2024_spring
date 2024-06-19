package lv.javaguru.travel.insurance.core.validations.integration;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTOBuilder;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTOBuilder;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
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
class ValidatePersonLastNameIntegrationTest {

    @Autowired
    private TravelAgreementValidator validator;
    @Autowired
    private DateHelper helper;

    @Test
    void validate_ShouldReturnErrorWhenPersonLastNameIsNull() {
        PersonDTO person = PersonDTOBuilder.createPerson()
                .withPersonFirstName("Jānis")
                .withPersonLastName(null)
                .withPersonalCode("123456-12345")
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
                        Assertions.tuple("ERROR_CODE_2", "Field personLastName is empty!"));
    }

    @Test
    void validate_ShouldReturnErrorWhenPersonLastNameContainsNotAllowedChars() {
        PersonDTO person = PersonDTOBuilder.createPerson()
                .withPersonFirstName("Vasja")
                .withPersonLastName("Пупкин")
                .withPersonalCode("123456-12345")
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
                        Assertions.tuple("ERROR_CODE_17", "Wrong personLastName format! Allowed symbols " +
                                "include Latin and Latvian characters, hyphens, and spaces."));
    }

    @Test
    void validate_ShouldReturnErrorWhenPersonLastNameExceedsAllowedLength() {
        PersonDTO person = PersonDTOBuilder.createPerson()
                .withPersonFirstName("Jānis")
                .withPersonLastName("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvw" +
                        "xyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefg" +
                        "hijklmnopqrstuvwxyzabcdefghijklmnopqrs")
                .withPersonalCode("123456-12345")
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
                        Assertions.tuple("ERROR_CODE_71", "Last name must not exceed 200 characters!"));
    }

}
