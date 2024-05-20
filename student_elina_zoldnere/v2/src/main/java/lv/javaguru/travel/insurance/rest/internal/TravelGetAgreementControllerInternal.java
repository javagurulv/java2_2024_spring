package lv.javaguru.travel.insurance.rest.internal;

import lv.javaguru.travel.insurance.core.api.command.TravelGetAgreementCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelGetAgreementCoreResult;
import lv.javaguru.travel.insurance.core.services.TravelGetAgreementService;
import lv.javaguru.travel.insurance.dto.internal.DtoInternalConverter;
import lv.javaguru.travel.insurance.dto.internal.TravelGetAgreementResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/insurance/travel/api/internal/agreement")
class TravelGetAgreementControllerInternal {

    @Autowired
    private TravelGetAgreementService getAgreementService;
    @Autowired
    private DtoInternalConverter dtoInternalConverter;

    @GetMapping(path = "/{uuid}",
            produces = "application/json")
    public TravelGetAgreementResponse getAgreement(@PathVariable String uuid) {
        return processRequest(uuid);
    }

    private TravelGetAgreementResponse processRequest(String uuid) {
        TravelGetAgreementCoreCommand command = dtoInternalConverter.buildCoreCommand(uuid);
        TravelGetAgreementCoreResult result = getAgreementService.getAgreement(command);
        return dtoInternalConverter.buildResponse(result);
    }

}