package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelPremiumUnderwritingImplTest {

    @Mock
    private DateTimeUtil dateTimeUtil;

    @InjectMocks
    private TravelPremiumUnderwritingImpl premiumUnderwriting;

    @Test
    public void checkResponseWithCorrectAgreementPrice() {
        TravelCalculatePremiumRequest travelCalculatePremiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(travelCalculatePremiumRequest.getAgreementDateFrom()).thenReturn(makeDate("10.10.2010"));
        when(travelCalculatePremiumRequest.getAgreementDateTo()).thenReturn(makeDate("11.10.2010"));
        when(dateTimeUtil.calculateDateFromTo(
                travelCalculatePremiumRequest.getAgreementDateFrom(),
                travelCalculatePremiumRequest.getAgreementDateTo())).thenReturn(1L);
        BigDecimal premium = premiumUnderwriting.calculatedPremium(travelCalculatePremiumRequest);
        assertEquals(premium, new BigDecimal(1));
    }

    private Date makeDate(String dateString) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


}

