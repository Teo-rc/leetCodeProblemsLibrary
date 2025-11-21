package problems.concurrency;

import problems.concurrency.printinorder.Foo;
import problems.concurrency.printwithalternate.FooBar;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class ProblemExecutor {

    private final ExecutorService fooExecutorService = Executors.newCachedThreadPool();
    private final ExecutorService fooBarExecutorService = Executors.newCachedThreadPool();

    private static final Logger LOGGER = Logger.getLogger(ProblemExecutor.class.getName());
    private static final String EXCEPTION ="exception";

    public void fooTest(int[] testCase) throws InterruptedException {
        // Initialise Test Foo Object
        var testObject = new Foo();

        // Initialise Runnable for each function in Foo
        Runnable printFirst = () -> LOGGER.info("first");
        Runnable printSecond = () -> LOGGER.info("second");
        Runnable printThird = () -> LOGGER.info("third");

        // Start each Thread in the order of the test case
        for (int j : testCase) {
            switch (j) {
                case 0 -> fooExecutorService.execute(() -> testObject.first(printFirst));
                case 1 -> fooExecutorService.execute(() -> {
                    try {
                        testObject.second(printSecond);
                    } catch (InterruptedException _) {
                        LOGGER.warning(EXCEPTION);
                        Thread.currentThread().interrupt();
                    }
                });
                default -> fooExecutorService.execute(() -> {
                    try {
                        testObject.third(printThird);
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

    public void foBarTest(int n) throws InterruptedException {
        // Initialise the test FooBar object
        var testObject = new FooBar(n);

        // Initialise the Runnable for each method in FooBar
        Runnable printFoo = () -> LOGGER.info("Foo");
        Runnable printBar = () -> LOGGER.info("Bar");

        fooBarExecutorService.execute(() -> {
            try {
                testObject.bar(printBar);
            } catch (InterruptedException _) {
                LOGGER.warning(EXCEPTION);
                Thread.currentThread().interrupt();
            }
        });
        fooBarExecutorService.execute(() -> {
            try {
                testObject.foo(printFoo);
            } catch (InterruptedException _) {
                LOGGER.warning(EXCEPTION);
                Thread.currentThread().interrupt();
            }
        });

        var _ = fooBarExecutorService.awaitTermination(3, TimeUnit.SECONDS);
        fooBarExecutorService.shutdown();
    }
}