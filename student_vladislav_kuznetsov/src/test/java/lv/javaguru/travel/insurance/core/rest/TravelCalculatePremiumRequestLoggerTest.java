package lv.javaguru.travel.insurance.core.rest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequestLogger;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TravelCalculatePremiumRequestLoggerTest {
    TravelCalculatePremiumRequestLogger logger = new TravelCalculatePremiumRequestLogger();
    @Test
    public void requestConversionToJsonString() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Vladislav");
        when(request.getPersonLastName()).thenReturn("Kuznetsov");
        when(request.getAgreementDateTo()).thenReturn(new Date());
        when(request.getAgreementDateFrom()).thenReturn(new Date());
        logger.log(request);
    }
}
