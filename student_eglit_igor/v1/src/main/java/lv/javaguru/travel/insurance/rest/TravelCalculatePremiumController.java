package lv.javaguru.travel.insurance.rest;

import com.google.common.base.Stopwatch;
import lv.javaguru.travel.insurance.core.services.TravelCalculatePremiumService;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumResponseV1;
import lv.javaguru.travel.insurance.loggers.RequestLogger;
import lv.javaguru.travel.insurance.loggers.ResponseLogger;
import lv.javaguru.travel.insurance.loggers.RequestResponseExecutionTimeLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/insurance/travel/api")
public class TravelCalculatePremiumController {

	@Autowired
	private TravelCalculatePremiumService calculatePremiumService;
	@Autowired private RequestLogger requestLogger;
	@Autowired private ResponseLogger responseLogger;
	@Autowired private RequestResponseExecutionTimeLogger requestResponseExecutionTimeLogger;

	@PostMapping(path = "/",
			consumes = "application/json",
			produces = "application/json")
	public TravelCalculatePremiumResponseV1 calculatePremium(@RequestBody TravelCalculatePremiumRequestV1 request) {
		Stopwatch stopwatch = Stopwatch.createStarted();
		requestLogger.logRequest(request);
		TravelCalculatePremiumResponseV1 response = calculatePremiumService.calculatePremium(request);
		responseLogger.logResponse(response);
		stopwatch.stop();
		long elapsedTime = stopwatch.elapsed(TimeUnit.MILLISECONDS);
		requestResponseExecutionTimeLogger.logTime(elapsedTime);
		return response;
	}

}