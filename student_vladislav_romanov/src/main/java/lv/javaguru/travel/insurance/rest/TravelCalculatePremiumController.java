package lv.javaguru.travel.insurance.rest;

import lv.javaguru.travel.insurance.core.services.TravelCalculatePremiumService;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/insurance/travel")
class TravelCalculatePremiumController {

	@Autowired
	private TravelCalculatePremiumRequestLogger requestLogger;

	@Autowired
	private TravelCalculatePremiumResponseLogger responseLogger;

	@Autowired
	private TravelCalculatePremiumRequestProcessingTimeLogger processingTimeLogger;

	@Autowired
	private TravelCalculatePremiumService travelCalculatePremiumService;

	@PostMapping(path = "/",
			consumes = "application/json",
			produces = "application/json")
	public TravelCalculatePremiumResponse calculatePremium(@RequestBody TravelCalculatePremiumRequest request) {
		StopWatch timeMeasure = new StopWatch();

		timeMeasure.start();
		TravelCalculatePremiumResponse response = requestProcessing(request);
		timeMeasure.stop();
		processingTimeLogger.log(timeMeasure.getLastTaskTimeMillis());

		return response;
	}

	private TravelCalculatePremiumResponse requestProcessing(TravelCalculatePremiumRequest request) {
		TravelCalculatePremiumResponse response = travelCalculatePremiumService.calculatePremium(request);

		requestLogger.log(request);
		responseLogger.log(response);

		return response;
	}

}