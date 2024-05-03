package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.mockito.Mockito;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ValidateSetUpPersonValuesHelper {

    public void setUpPersonMockWithValues(PersonDTO personMock) {
        Mockito.lenient().when(personMock.getPersonFirstName()).thenReturn("Jānis");
        Mockito.lenient().when(personMock.getPersonLastName()).thenReturn("Bērziņš");
        Mockito.lenient().when(personMock.getPersonBirthDate()).thenReturn(new Date(90, 1, 1));
    }

}