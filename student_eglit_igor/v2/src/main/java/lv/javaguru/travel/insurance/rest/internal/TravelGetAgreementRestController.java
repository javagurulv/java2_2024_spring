package lv.javaguru.travel.insurance.rest.internal;

import com.google.common.base.Stopwatch;
import lv.javaguru.travel.insurance.dto.internal.TravelGetAgreementResponse;
import lv.javaguru.travel.insurance.rest.common.TravelRestRequestExecutionTimeLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/insurance/travel/api/internal/agreement")
public class TravelGetAgreementRestController {

    @Autowired
    private TravelGetAgreementRequestLogger requestLogger;
    @Autowired
    private TravelGetAgreementResponseLogger responseLogger;
    @Autowired
    private TravelRestRequestExecutionTimeLogger executionTimeLogger;
    //@Autowired private DtoConverter dtoConverter;

    @GetMapping(path = "/{uuid}",
            produces = "application/json")
    public TravelGetAgreementResponse getAgreement(@PathVariable String uuid) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        TravelGetAgreementResponse response = processRequest(uuid);
        new TravelGetAgreementResponse();
        response.setAgreementDateFrom(LocalDate.now());
        response.setAgreementDateTo(LocalDate.now());
        response.setUuid(uuid);
        executionTimeLogger.logExecutionTime(stopwatch);
        return response;
    }
    private TravelGetAgreementResponse processRequest(String uuid){
        requestLogger.log(uuid);
        //TravelGetAgreementCoreCommand coreCommand = dtoConverter.buildCoreCommand(uuid);
        //TravelGetAgreementCoreResult coreResult = agreementService.getAgreement(coreCommand);
        //TravelGetAgreementResponse response = dtoV2Converter.buildResponse(coreResult);
        TravelGetAgreementResponse response = new TravelGetAgreementResponse();
        response.setAgreementDateFrom(LocalDate.now());
        response.setAgreementDateTo(LocalDate.now());
        response.setUuid(uuid);

        responseLogger.log(response);
        return response;
    }
}
