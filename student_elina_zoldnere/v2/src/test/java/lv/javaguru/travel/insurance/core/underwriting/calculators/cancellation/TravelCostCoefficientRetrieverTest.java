package lv.javaguru.travel.insurance.core.underwriting.calculators.cancellation;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTOBuilder;
import lv.javaguru.travel.insurance.core.domain.cancellation.TravelCostCoefficient;
import lv.javaguru.travel.insurance.core.repositories.cancellation.TravelCostCoefficientRepository;
import lv.javaguru.travel.insurance.core.util.SetUpInstancesHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCostCoefficientRetrieverTest {

    @Mock
    private TravelCostCoefficientRepository costCoefficientRepositoryMock;;

    @InjectMocks
    private TravelCostCoefficientRetriever  costCoefficientRetriever;

    @Autowired
    @InjectMocks
    private SetUpInstancesHelper helper;

    @Test
    void findTravelCostPremium_shouldFindRateWhenTravelCostExists() {
        BigDecimal travelCostPremium = BigDecimal.valueOf(30);
        PersonDTO person = PersonDTOBuilder.createPerson()
                .withTravelCost(BigDecimal.valueOf(6000))
                .build();

        TravelCostCoefficient travelCostCoefficientMock = mock(TravelCostCoefficient.class);
        when(costCoefficientRepositoryMock.findByTravelCost(any()))
                .thenReturn(Optional.of(travelCostCoefficientMock));
        when(travelCostCoefficientMock.getTravelCostPremium()).thenReturn(travelCostPremium);

        BigDecimal actualTravelCostPremium = costCoefficientRetriever.findTravelCostPremium(person);
        assertEquals(travelCostPremium, actualTravelCostPremium);
    }

    @Test
    void findTravelCostPremium_shouldThrowExceptionWhenTravelCostDoesNotExist() {
        PersonDTO person = PersonDTOBuilder.createPerson()
                .withTravelCost(BigDecimal.valueOf(10000000))
                .build();
        when(costCoefficientRepositoryMock.findByTravelCost(BigDecimal.valueOf(10000000)))
                .thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> costCoefficientRetriever
                .findTravelCostPremium(person));
        assertEquals("Premium for travel cost = 10000000 not found!", exception.getMessage());
    }

}
