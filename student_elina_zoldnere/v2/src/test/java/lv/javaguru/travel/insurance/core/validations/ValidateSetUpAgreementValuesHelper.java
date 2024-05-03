package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;

import org.mockito.Mockito;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

@Component
public class ValidateSetUpAgreementValuesHelper {

    public void setUpAgreementMockWithValues(AgreementDTO agreementMock) {
        //Mockito.lenient().when(personMock.getPersonFirstName()).thenReturn("Jānis");
        //Mockito.lenient().when(personMock.getPersonLastName()).thenReturn("Bērziņš");
        //Mockito.lenient().when(personMock.getPersonBirthDate()).thenReturn(new Date(90, 1, 1));
        Mockito.lenient().when(agreementMock.getAgreementDateFrom()).thenReturn(new Date(125, 2, 10));
        Mockito.lenient().when(agreementMock.getAgreementDateTo()).thenReturn(new Date(125, 2, 11));
        Mockito.lenient().when(agreementMock.getSelectedRisks())
                .thenReturn(Arrays.asList("TRAVEL_MEDICAL", "TRAVEL_CANCELLATION", "TRAVEL_LOSS_BAGGAGE"));
        Mockito.lenient().when(agreementMock.getCountry()).thenReturn("SPAIN");
        Mockito.lenient().when(agreementMock.getMedicalRiskLimitLevel()).thenReturn("LEVEL_10000");
    }

}