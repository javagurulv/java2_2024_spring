package lv.javaguru.travel.insurance.core.underwriting.integrations.cancellation;

import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTOBuilder;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTOBuilder;
import lv.javaguru.travel.insurance.core.services.TravelCalculatePremiumService;
import lv.javaguru.travel.insurance.core.util.DateHelper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(properties = {"feature.tripCancellation.enabled=false"})
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class TripCancellationOffIntegrationTest {
    @Autowired
    private TravelCalculatePremiumService service;
    @Autowired
    private DateHelper helper;

    @Test
    void calculateAgreementPremium_whenTripCancellationEnabled() {
        PersonDTO person = PersonDTOBuilder.createPerson()
                .withPersonFirstName("Jānis")
                .withPersonLastName("Bērziņš")
                .withPersonalCode("123456-12345")
                .withPersonBirthdate(helper.newDate("1990.01.01"))
                .withTravelCost(new BigDecimal("6000"))
                .build();

        AgreementDTO agreement = AgreementDTOBuilder.createAgreement()
                .withDateFrom(helper.newDate("2025.03.10"))
                .withDateTo(helper.newDate("2025.03.11"))
                .withCountry("SPAIN")
                .withSelectedRisk("TRAVEL_CANCELLATION")
                .withPerson(person)
                .build();

        TravelCalculatePremiumCoreCommand command = new TravelCalculatePremiumCoreCommand(agreement);

        TravelCalculatePremiumCoreResult result = service.calculatePremium(command);

        assertThat(result.getErrors())
                .hasSize(1)
                .extracting("errorCode", "description")
                .containsExactly(
                        Assertions.tuple("ERROR_CODE_61", "Travel cancellation risk currently disabled!"));
    }

}
