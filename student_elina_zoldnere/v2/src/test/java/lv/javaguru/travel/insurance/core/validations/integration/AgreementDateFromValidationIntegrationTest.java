package lv.javaguru.travel.insurance.core.validations.integration;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTOBuilder;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.util.SetUpInstancesHelper;
import lv.javaguru.travel.insurance.core.validations.TravelAgreementValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AgreementDateFromValidationIntegrationTest {

    @Autowired
    private TravelAgreementValidator validator;
    @Autowired
    private SetUpInstancesHelper helper;

    @Test
    public void validate_ShouldReturnErrorWhenAgreementDateFromIsNull() {
        PersonDTO person = new PersonDTO(
                "Jānis",
                "Bērziņš",
                helper.newDate("1990.01.01"),
                "LEVEL_10000",
                null);

        AgreementDTO agreement = AgreementDTOBuilder.createAgreement()
                .withDateFrom(null)
                .withDateTo(helper.newDate("2025.03.10"))
                .withCountry("SPAIN")
                .withSelectedRisk("TRAVEL_MEDICAL")
                .withPersons(List.of(person))
                .build();

        List<ValidationErrorDTO> errors = validator.validate(agreement);

        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).errorCode(), "ERROR_CODE_3");
        assertEquals(errors.get(0).description(), "Field agreementDateFrom is empty!");
    }

    @Test
    public void validate_ShouldReturnErrorWhenAgreementDateFromLessThanToday() {
        PersonDTO person = new PersonDTO(
                "Jānis",
                "Bērziņš",
                helper.newDate("2000.01.01"),
                "LEVEL_10000",
                null);

        AgreementDTO agreement = AgreementDTOBuilder.createAgreement()
                .withDateFrom(helper.newDate("2020.01.01"))
                .withDateTo(helper.newDate("2030.01.01"))
                .withCountry("SPAIN")
                .withSelectedRisk("TRAVEL_MEDICAL")
                .withPersons(List.of(person))
                .build();

        List<ValidationErrorDTO> errors = validator.validate(agreement);

        assertEquals(errors.size(), 1);
        assertEquals("ERROR_CODE_11", errors.get(0).errorCode());
        assertEquals("Field agreementDateFrom is in the past!", errors.get(0).description());
    }

}
