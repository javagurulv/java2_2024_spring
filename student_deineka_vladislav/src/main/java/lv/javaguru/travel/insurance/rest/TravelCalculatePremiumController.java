package lv.javaguru.travel.insurance.rest;

import com.google.common.base.Stopwatch;
import lv.javaguru.travel.insurance.core.services.TravelCalculatePremiumService;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/insurance/travel")
public class TravelCalculatePremiumController {

    @Autowired
    private TravelCalculatePremiumService calculatePremiumService;
    @Autowired
    private TravelCalculatePremiumResponseLogger responseLogger;
    @Autowired
    private TravelCalculatePremiumRequestLogger requestLogger;
    @Autowired
    private TravelCalculatePremiumRequestExecutionTimeLogger timeLogger;


    @PostMapping(path = "/",
            consumes = "application/json",
            produces = "application/json")
    public TravelCalculatePremiumResponse calculatePremium(@RequestBody TravelCalculatePremiumRequest travelCalculatePremiumRequest) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        TravelCalculatePremiumResponse travelCalculatePremiumResponse = requestOfProcess(travelCalculatePremiumRequest);
        timeLogger.executionTimeLogging(stopwatch);
        return travelCalculatePremiumResponse;
    }

    private TravelCalculatePremiumResponse requestOfProcess(TravelCalculatePremiumRequest travelCalculatePremiumRequest) {
        requestLogger.logging(travelCalculatePremiumRequest);
        TravelCalculatePremiumResponse travelCalculatePremiumResponse = calculatePremiumService.calculatePremium(travelCalculatePremiumRequest);
        responseLogger.logging(travelCalculatePremiumResponse);
        return travelCalculatePremiumResponse;
    }
}


