package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.api.command.TravelGetNotExportedAgreementUuidsCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelGetNotExportedAgreementUuidsCoreResult;
import lv.javaguru.travel.insurance.core.repositories.entities.AgreementEntityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelGetNotExportedAgreementUuidsServiceImplTest {

    @Mock
    private AgreementEntityRepository agreementRepositoryMock;

    @InjectMocks
    private TravelGetNotExportedAgreementUuidsServiceImpl service;

    @Test
    void getUuids_ShouldCorrectlyReturnResult(){
        List<String> uuids = List.of("UUID1", "UUID2");
        TravelGetNotExportedAgreementUuidsCoreCommand command = new TravelGetNotExportedAgreementUuidsCoreCommand();

        when(agreementRepositoryMock.findNotExportedUuids()).thenReturn(uuids);

        TravelGetNotExportedAgreementUuidsCoreResult result = service.getUuids(command);

        assertThat(result.getUuids())
                .hasSize(2)
                .containsExactly("UUID1", "UUID2");
    }

}
