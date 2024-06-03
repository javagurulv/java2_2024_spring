package lv.javaguru.travel.insurance.loadtesting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class LoadTestingSystem {

    private static final Logger logger = LoggerFactory.getLogger(LoadTestingSystem.class);

    public static void main(String[] args) {
        new LoadTestingSystem().executeForAMinute(5, 60000, "V1");
    }

    void executeForAMinute(int parallelThreadCount, int requestCount, String version) {
        long intervalBetweenRequestsInMillis = 60000L / requestCount;

        LoadTestingStatistic statistic = new LoadTestingStatistic();

        ExecutorService executorService = Executors.newFixedThreadPool(parallelThreadCount);
        List<Callable<Void>> tasks = new ArrayList<>();

        for (int i = 1; i <= requestCount; i++) {
            for (int j = 1; j <= parallelThreadCount; j++) {
                tasks.add(Executors.callable(new V1Call(statistic), null));
            }
            try {
                executorService.invokeAll(tasks);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                Thread.sleep(intervalBetweenRequestsInMillis);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        executorService.shutdown();

        try {
            boolean terminated = executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
            if (!terminated) {
                logger.error("Not all tasks were completed before shutdown.");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        logger.info("{}: average processing time {} (ms)", version, statistic.calculateAverage());
        logger.info("{}: min processing time {} (ms)", version, statistic.getMin());
        logger.info("{}: max processing time {} (ms)", version, statistic.getMax());
    }

}
