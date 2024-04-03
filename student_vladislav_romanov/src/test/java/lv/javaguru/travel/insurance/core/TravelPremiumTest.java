package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TravelPremiumTest {

    @Mock
    DateTimeService dateTimeServiceMock;

    @InjectMocks
    private TravelPremium travelPremium;

    @Test
    void travelPremiumTest() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Vladislav", "Romanov", LocalDate.of(2030, 3, 8), LocalDate.of(2030, 3, 18));

        doReturn(10).when(dateTimeServiceMock).calculateTravelPeriod(LocalDate.of(2030, 3, 8), LocalDate.of(2030, 3, 18));

        BigDecimal expected = new BigDecimal(10);
        BigDecimal actual = travelPremium.calculatePremium(request);

        assertEquals(expected, actual);
    }

}
