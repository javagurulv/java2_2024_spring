package lv.javaguru.travel.insurance.core.validations.integration;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTOBuilder;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.util.DateHelper;
import lv.javaguru.travel.insurance.core.validations.TravelAgreementValidator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class ValidatePersonsIntegrationTest {

    @Autowired
    private TravelAgreementValidator validator;
    @Autowired
    private DateHelper helper;

    @Test
    void validate_ShouldReturnErrorWhenPersonsIsEmpty() {

        AgreementDTO agreement = AgreementDTOBuilder.createAgreement()
                .withDateFrom(helper.newDate("2025.03.10"))
                .withDateTo(helper.newDate("2025.03.11"))
                .withCountry("SPAIN")
                .withSelectedRisks(List.of("TRAVEL_MEDICAL", "TRAVEL_LOSS_BAGGAGE"))
                .withPersons(Collections.emptyList())
                .withPremium(BigDecimal.ZERO)
                .build();

        List<ValidationErrorDTO> result = validator.validate(agreement);

        assertThat(result)
                .hasSize(1)
                .extracting("errorCode", "description")
                .containsExactly(
                        Assertions.tuple("ERROR_CODE_18", "No persons added to agreement! " +
                                "Agreement must contain at least one person!"));
    }

}