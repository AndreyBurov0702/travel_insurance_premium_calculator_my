package org.javaguru.travel.insurance.rest;

import org.junit.jupiter.api.Test;

class TravelCalculatePremiumControllerTestCase11 extends TravelCalculatePremiumControllerTestCase{

    @Test
    public void execute() throws Exception{
        executeAndCompare();
    }

    @Override
    protected String getTestCaseFolderName() {
        return "test_case_11";
    }
}
