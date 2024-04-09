package org.javaguru.travel.insurance.core.services;

import org.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import org.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;

public interface TravelCalculatePremiumService {

    TravelCalculatePremiumCoreResult calculatePremium(TravelCalculatePremiumCoreCommand command);

}
