package org.javaguru.travel.insurance.rest;

import org.junit.jupiter.api.Test;

public class TravelCalculatePremiumControllerTestCase2 extends TravelCalculatePremiumControllerTestCase {

    @Test
    public void execute() throws Exception {
        executeAndCompare();
    }

    @Override
    protected String getTestCaseFolderName() {
        return "test_case_2";
    }
}
