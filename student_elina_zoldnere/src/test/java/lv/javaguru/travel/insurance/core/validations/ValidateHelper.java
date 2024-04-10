package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;

import org.mockito.Mockito;
import org.springframework.stereotype.Component;


import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

@Component
public class ValidateHelper {

    public void setUpRequestMockWithAllValues(TravelCalculatePremiumRequest requestMock) {
        Mockito.lenient().when(requestMock.getPersonFirstName()).thenReturn("Jānis");
        Mockito.lenient().when(requestMock.getPersonLastName()).thenReturn("Bērziņš");
        Mockito.lenient().when(requestMock.getAgreementDateFrom()).thenReturn(new Date(125, 2, 10));
        Mockito.lenient().when(requestMock.getAgreementDateTo()).thenReturn(new Date(125, 2, 11));
        Mockito.lenient().when(requestMock.getSelectedRisks())
                .thenReturn(Arrays.asList("TRAVEL_MEDICAL","TRAVEL_CANCELLATION","TRAVEL_LOSS_BAGGAGE"));
    }

    public Date midnightToday() { // today 00:00:00 EET
        Calendar today = Calendar.getInstance();
        today.setTime(new Date());
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);
        return today.getTime();
    }

}