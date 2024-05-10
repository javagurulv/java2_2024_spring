package lv.javaguru.travel.insurance.rest.v1;

import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.core.services.TravelCalculatePremiumService;
import lv.javaguru.travel.insurance.dto.v1.DtoV1Converter;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumResponseV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/insurance/travel/api/v1")
class TravelCalculatePremiumControllerV1 {

    @Autowired
    private TravelCalculatePremiumService calculatePremiumService;
    @Autowired
    private DtoV1Converter dtoV1Converter;

    @PostMapping(path = "/",
            consumes = "application/json",
            produces = "application/json")
    public TravelCalculatePremiumResponseV1 calculatePremium(@RequestBody TravelCalculatePremiumRequestV1 request) {
        return processRequest(request);
    }

    private TravelCalculatePremiumResponseV1 processRequest(TravelCalculatePremiumRequestV1 request) {
        TravelCalculatePremiumCoreCommand coreCommand = dtoV1Converter.buildCoreCommand(request);
        TravelCalculatePremiumCoreResult coreResult = calculatePremiumService.calculatePremium(coreCommand);
        return dtoV1Converter.buildResponse(coreResult);
    }

}
