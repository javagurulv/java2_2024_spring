package lv.javaguru.travel.insurance.loadtesting;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class RestCallExample {

    public static void main(String[] args) {
        int numThreads = 50;
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads * 2);

        for (int i = 0; i < numThreads; i++) {
            executorService.execute(new V1Call());
            executorService.execute(new V2Call());
        }

        executorService.shutdown();
    }

}
