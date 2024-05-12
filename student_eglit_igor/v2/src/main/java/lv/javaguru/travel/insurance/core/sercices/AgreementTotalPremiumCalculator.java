package lv.javaguru.travel.insurance.core.sercices;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collection;

@Component
class AgreementTotalPremiumCalculator {
    BigDecimal calculate(AgreementDTO agreement) {
        return agreement.getPersons().stream()
                .map(PersonDTO::risks)
                .flatMap(Collection::stream)
                .map(RiskDTO::getPremium)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
