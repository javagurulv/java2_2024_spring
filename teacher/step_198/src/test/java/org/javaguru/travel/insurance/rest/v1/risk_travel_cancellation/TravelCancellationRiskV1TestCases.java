package org.javaguru.travel.insurance.rest.v1.risk_travel_cancellation;

import org.javaguru.travel.insurance.rest.v1.TravelCalculatePremiumControllerV1TestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

public class TravelCancellationRiskV1TestCases extends TravelCalculatePremiumControllerV1TestCase {

    private static final String TEST_FILE_BASE_FOLDER = "risk_travel_cancellation";

    @Test
    @WithMockUser
    @DisplayName("ERROR_CODE_19 travelCost is NULL, must not be empty")
    public void check_ERROR_CODE_19() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_19_travelCost_is_null");
    }

}
