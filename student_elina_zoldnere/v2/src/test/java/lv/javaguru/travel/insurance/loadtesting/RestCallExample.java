package lv.javaguru.travel.insurance.loadtesting;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class RestCallExample {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(new V1Call());
        executorService.execute(new V2Call());

        executorService.shutdown();
    }

}
