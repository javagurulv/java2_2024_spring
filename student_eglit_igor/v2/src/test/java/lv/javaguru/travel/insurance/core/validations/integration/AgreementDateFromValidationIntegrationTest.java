package lv.javaguru.travel.insurance.core.validations.integration;

import lv.javaguru.travel.insurance.core.api.dto.*;
import lv.javaguru.travel.insurance.core.validations.TravelAgreementValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AgreementDateFromValidationIntegrationTest {

    @Autowired
    private TravelAgreementValidator validator;

    @Test
    public void shouldReturnErrorWhenDateFromIsNull() {

        PersonDTO person = PersonDTOBuilder.createPerson()
                .withFirstName("Vasja")
                .withLastName("Pupkin")
                .withBirthDate(LocalDate.of(2000, 1, 1))
                .withMedicalRiskLimitLevel("LEVEL_10000")
                .withRisks(List.of(new RiskDTO(), new RiskDTO()))
                .build();

        AgreementDTO agreement = AgreementDTOBuilder.createAgreement()
                .withDateFrom(null)
                .withDateTo(LocalDate.of(2030, 1, 1))
                .withCountry("SPAIN")
                .withSelectedRisk("TRAVEL_MEDICAL")
                .withPersons(List.of(person))
                .build();

        List<ValidationErrorDTO> errors = validator.validate(agreement);

        assertEquals(1, errors.size());
        assertEquals("ERROR_CODE_3", errors.get(0).getErrorCode());
        assertEquals("Field agreementDateFrom is empty!", errors.get(0).getDescription());
    }

    @Test
    public void shouldReturnErrorWhenDateFromIsInThePast() {
        PersonDTO person = new PersonDTO(
                "Vasja",
                "Pupkin",
                LocalDate.of(2000, 1, 1),
                "LEVEL_10000",
                List.of()
        );

        AgreementDTO agreement = AgreementDTOBuilder.createAgreement()
                .withDateFrom(LocalDate.of(2019, 1, 1))
                .withDateTo(LocalDate.of(2030, 1, 5))
                .withCountry("SPAIN")
                .withSelectedRisk("TRAVEL_MEDICAL")
                .withPersons(List.of(person))
                .build();

        List<ValidationErrorDTO> errors = validator.validate(agreement);

        assertEquals(1, errors.size());
        assertEquals("ERROR_CODE_5", errors.get(0).getErrorCode());
        assertEquals("Field agreementDateFrom is in the past!", errors.get(0).getDescription());
    }

    @Test
    public void shouldReturnErrorWhenCountryIsNull() {
        PersonDTO person = new PersonDTO(
                "Vasja",
                "Pupkin",
                LocalDate.of(2000, 1, 1),
                "LEVEL_10000",
                List.of()
        );

        AgreementDTO agreement = AgreementDTOBuilder.createAgreement()
                .withDateFrom(LocalDate.of(2030, 1, 1))
                .withDateTo(LocalDate.of(2030, 1, 5))
                .withCountry(null)
                .withSelectedRisk("TRAVEL_MEDICAL")
                .withPersons(List.of(person))
                .build();

        List<ValidationErrorDTO> errors = validator.validate(agreement);

        assertEquals(1, errors.size());
        assertEquals("ERROR_CODE_10", errors.get(0).getErrorCode());
        assertEquals("Field Country is empty!", errors.get(0).getDescription());
    }

    @Test
    public void shouldReturnErrorThenFirstNameIsEmpty() {
        PersonDTO person = PersonDTOBuilder.createPerson()
                .withFirstName("")
                .withLastName("Pupkin")
                .withBirthDate(LocalDate.of(2000, 1, 1))
                .withMedicalRiskLimitLevel("LEVEL_10000")
                .withRisk(new RiskDTO())
                .build();

        AgreementDTO agreement = AgreementDTOBuilder.createAgreement()
                .withDateFrom(LocalDate.of(2030, 1, 1))
                .withDateTo(LocalDate.of(2030, 1, 5))
                .withCountry("SPAIN")
                .withSelectedRisk("TRAVEL_MEDICAL")
                .withPersons(List.of(person))
                .build();

        List<ValidationErrorDTO> errors = validator.validate(agreement);

        assertEquals(1, errors.size());
        assertEquals("ERROR_CODE_1", errors.get(0).getErrorCode());
        assertEquals("Field personFirstName is empty!", errors.get(0).getDescription());
    }

    @Test
    public void shouldReturnMultiplyErrorThenFirstNamesAreEmpty() {
        PersonDTO person = new PersonDTO(
                "",
                "Pupkin",
                LocalDate.of(2000, 1, 1),
                "LEVEL_10000",
                List.of()
        );
        PersonDTO person2 = new PersonDTO(
                "",
                "Pupkin",
                LocalDate.of(2000, 12, 1),
                "LEVEL_10000",
                List.of()
        );

        AgreementDTO agreement = AgreementDTOBuilder.createAgreement()
                .withDateFrom(LocalDate.of(2030, 1, 1))
                .withDateTo(LocalDate.of(2030, 1, 5))
                .withCountry("SPAIN")
                .withSelectedRisk("TRAVEL_MEDICAL")
                .withPersons(List.of(person, person2))
                .build();

        List<ValidationErrorDTO> errors = validator.validate(agreement);

        assertEquals(2, errors.size());
        assertEquals("ERROR_CODE_1", errors.get(0).getErrorCode());
        assertEquals("Field personFirstName is empty!", errors.get(0).getDescription());
        assertEquals("ERROR_CODE_1", errors.get(1).getErrorCode());
        assertEquals("Field personFirstName is empty!", errors.get(1).getDescription());
    }
}
