package org.javaguru.travel.insurance.rest.v2.risks;

import org.javaguru.travel.insurance.rest.v2.TravelCalculatePremiumControllerV2TestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

public class RiskLevelV2TestCases extends TravelCalculatePremiumControllerV2TestCase {

    private static final String TEST_FILE_BASE_FOLDER = "risks";

    @Test
    @WithMockUser
    @DisplayName("Success case with [TRAVEL_MEDICAL, TRAVEL_CANCELLATION]")
    public void executeTestCase1() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/Success_TRAVEL_MEDICAL_TRAVEL_CANCELLATION",true);
    }

    @Test
    @WithMockUser
    @DisplayName("ERROR_CODE_6 selectedRisks is NULL, must not be empty")
    public void check_ERROR_CODE_6_NULL() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_6_selectedRisks_is_null");
    }

    @Test
    @WithMockUser
    @DisplayName("ERROR_CODE_6 selectedRisks is [], must not be empty")
    public void check_ERROR_CODE_6_EMPTY() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_6_selectedRisks_is_empty");
    }

    @Test
    @WithMockUser
    @DisplayName("ERROR_CODE_9 one selectedRisks is not supported")
    public void check_ERROR_CODE_9_one() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_9_one_invalid_selectedRisk");
    }

    @Test
    @WithMockUser
    @DisplayName("ERROR_CODE_9 two selectedRisks is not supported")
    public void check_ERROR_CODE_9_two() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_9_two_invalid_selectedRisk");
    }

}
