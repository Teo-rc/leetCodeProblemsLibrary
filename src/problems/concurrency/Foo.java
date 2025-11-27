package problems.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Foo {

    private final ExecutorService fooExecutorService = Executors.newCachedThreadPool();

    private static final Logger LOGGER = Logger.getLogger(Foo.class.getName());
    private static final String EXCEPTION ="exception";

    private boolean first = false;
    private boolean second = false;

    private synchronized void first(Runnable printFirst) {
        notifyAll();
        this.first = true;
        printFirst.run();
    }

    private synchronized void second(Runnable printSecond) throws InterruptedException {
        while (!this.first) {
            this.wait();
        }
        notifyAll();
        this.second = true;
        printSecond.run();
    }

    private synchronized void third(Runnable printThird) throws InterruptedException {
        while (!this.second) {
            this.wait();
        }
        printThird.run();
    }

    public void fooTest(int[] testCase) throws InterruptedException {

        // Initialise Runnable for each function in Foo
        Runnable printFirst = () -> System.out.print("first");
        Runnable printSecond = () -> System.out.print("second");
        Runnable printThird = () -> System.out.print("third");

        // Start each Thread in the order of the test case
        for (int j : testCase) {
            switch (j) {
                case 0 -> fooExecutorService.execute(() -> this.first(printFirst));
                case 1 -> fooExecutorService.execute(() -> {
                    try {
                        this.second(printSecond);
                    } catch (InterruptedException _) {
                        LOGGER.warning(EXCEPTION);
                        Thread.currentThread().interrupt();
                    }
                });
                default -> fooExecutorService.execute(() -> {
                    try {
                        this.third(printThird);
                    } catch (InterruptedException _) {
                        LOGGER.warning(EXCEPTION);
                        Thread.currentThread().interrupt();
                    }
                });
            }
        }
        var _ = fooExecutorService.awaitTermination(3, TimeUnit.SECONDS);
        fooExecutorService.shutdown();
    }
}