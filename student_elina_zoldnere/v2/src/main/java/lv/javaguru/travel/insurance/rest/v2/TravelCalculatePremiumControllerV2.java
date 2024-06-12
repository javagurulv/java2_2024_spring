package lv.javaguru.travel.insurance.rest.v2;

import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.core.services.TravelCalculatePremiumService;
import lv.javaguru.travel.insurance.dto.v2.DtoV2Converter;
import lv.javaguru.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import lv.javaguru.travel.insurance.dto.v2.TravelCalculatePremiumResponseV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/insurance/travel/api/v2")
class TravelCalculatePremiumControllerV2 {

    @Autowired
    private TravelCalculatePremiumService calculatePremiumService;
    @Autowired
    private DtoV2Converter dtoV2Converter;

    @PostMapping(path = "/",
            consumes = "application/json",
            produces = "application/json")
    public TravelCalculatePremiumResponseV2 calculatePremium(@RequestBody TravelCalculatePremiumRequestV2 request) {
        return processRequest(request);
    }

    private TravelCalculatePremiumResponseV2 processRequest(TravelCalculatePremiumRequestV2 request) {
        TravelCalculatePremiumCoreCommand coreCommand = dtoV2Converter.buildCoreCommand(request);
        TravelCalculatePremiumCoreResult coreResult = calculatePremiumService.calculatePremium(coreCommand);
        return dtoV2Converter.buildResponse(coreResult);
    }

}
