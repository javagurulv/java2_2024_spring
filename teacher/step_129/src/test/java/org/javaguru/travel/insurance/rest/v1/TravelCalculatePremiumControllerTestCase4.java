package org.javaguru.travel.insurance.rest.v1;

import org.junit.jupiter.api.Test;

public class TravelCalculatePremiumControllerTestCase4 extends TravelCalculatePremiumControllerV1TestCase {

    @Test
    public void execute() throws Exception {
        executeAndCompare();
    }

    @Override
    protected String getTestCaseFolderName() {
        return "test_case_4";
    }
}
