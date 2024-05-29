package lv.javaguru.travel.insurance.core.rest;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TravelCalculatePremiumControlerTestCase11 extends TravelCalculatePremiumControllerTest{
    @Test
    @Disabled
    public void execute() throws Exception {
        executeAndCompare();
    }

    @Override
    protected String getTestCaseFolderName() {
        return "test_case_11";
    }
}
