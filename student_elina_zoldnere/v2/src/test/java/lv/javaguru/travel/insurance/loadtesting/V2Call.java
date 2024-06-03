package lv.javaguru.travel.insurance.loadtesting;

class V2Call extends CallTemplate {
    public V2Call(LoadTestingStatistic loadTestingStatistics) {
        super(loadTestingStatistics);
    }

    @Override
    String getFilePath() {
        return "v2/agreement/AgreementV2Test_98_all_fields_are_present_and_valid-1.json";
    }

    @Override
    String getUrl() {
        return "http://localhost:8080/insurance/travel/api/v2/";
    }

    @Override
    String getIdentifier() {
        return "V2";
    }

}
