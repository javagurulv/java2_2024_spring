package lv.javaguru.travel.insurance.loadtesting;

class V1Call extends CallTemplate {

    public V1Call(LoadTestingStatistic loadTestingStatistics) {
        super(loadTestingStatistics);
    }

    @Override
    String getFilePath() {
        return "v1/agreement/AgreementV1Test_99_all_fields_present_and_valid.json";
    }

    @Override
    String getUrl() {
        return "http://localhost:8080/insurance/travel/api/v1/";
    }

    @Override
    String getIdentifier() {
        return "V1";
    }

}
