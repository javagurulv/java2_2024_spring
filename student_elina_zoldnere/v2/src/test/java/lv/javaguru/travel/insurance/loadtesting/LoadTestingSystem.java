package lv.javaguru.travel.insurance.loadtesting;

import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class LoadTestingSystem {

    private static final Logger logger = LoggerFactory.getLogger(LoadTestingSystem.class);

    public static void main(String[] args) {

        new LoadTestingSystem().executeForADefinedTime(20, 1, 5, "V2");
    }

    private void executeForADefinedTime(int timeInSeconds, int parallelThreadCount, int batchCount, String version) {
        int executionTimeInMillis = timeInSeconds * 1000;
        long intervalBetweenRequestsInMillis = executionTimeInMillis / batchCount;
        ExecutorService executorService = Executors.newFixedThreadPool(parallelThreadCount);

        performWarmUpCall();

        Stopwatch stopwatch = Stopwatch.createStarted();
        LoadTestingStatistic statistic = new LoadTestingStatistic();

        for (int i = 1; i <= batchCount; i++) {
            for (int j = 1; j <= parallelThreadCount; j++) {
                executorService.execute(new V2Call(statistic));
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

        stopwatch.stop();
        long elapsedTimeSeconds = stopwatch.elapsed(TimeUnit.SECONDS);

        logger.info("{}: average processing time {} (ms)", version, statistic.calculateAverage());
        logger.info("{}: min processing time {} (ms)", version, statistic.getMin());
        logger.info("{}: max processing time {} (ms)", version, statistic.getMax());
        logger.info("Actual performing time {} (s)", elapsedTimeSeconds);
        logger.info("Actual calls per second {}", (long) batchCount * parallelThreadCount / elapsedTimeSeconds);
    }

    private void performWarmUpCall() {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        try {
            executorService.execute(new V2Call(null));
            logger.info("Warm-up call completed.");
        } catch (RuntimeException e) {
            logger.error("Warm-up call failed.", e);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
