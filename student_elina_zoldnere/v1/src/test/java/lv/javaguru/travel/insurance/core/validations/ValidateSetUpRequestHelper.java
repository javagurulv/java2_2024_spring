package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;

import org.mockito.Mockito;
import org.springframework.stereotype.Component;


import java.util.Arrays;
import java.util.Date;

@Component
public class ValidateSetUpRequestHelper {

    public void setUpRequestMockWithAllValues(TravelCalculatePremiumRequestV1 requestMock) {
        Mockito.lenient().when(requestMock.getPersonFirstName()).thenReturn("Jānis");
        Mockito.lenient().when(requestMock.getPersonLastName()).thenReturn("Bērziņš");
        Mockito.lenient().when(requestMock.getPersonBirthDate()).thenReturn(new Date(90, 1, 1));
        Mockito.lenient().when(requestMock.getAgreementDateFrom()).thenReturn(new Date(125, 2, 10));
        Mockito.lenient().when(requestMock.getAgreementDateTo()).thenReturn(new Date(125, 2, 11));
        Mockito.lenient().when(requestMock.getSelectedRisks())
                .thenReturn(Arrays.asList("TRAVEL_MEDICAL", "TRAVEL_CANCELLATION", "TRAVEL_LOSS_BAGGAGE"));
        Mockito.lenient().when(requestMock.getCountry()).thenReturn("SPAIN");
        Mockito.lenient().when(requestMock.getMedicalRiskLimitLevel()).thenReturn("LEVEL_10000");
    }

}