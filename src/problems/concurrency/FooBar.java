package problems.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class FooBar {

    private final int n;
    private boolean first = true;
    private final ExecutorService fooBarExecutorService = Executors.newCachedThreadPool();

    private static final Logger LOGGER = Logger.getLogger(FooBar.class.getName());
    private static final String EXCEPTION ="exception";

    public FooBar(int n) {
        this.n = n;
    }

    private synchronized void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < this.n; i++) {
            while (!this.first) {
                wait();
            }
            this.first = false;
            notifyAll();
            printFoo.run();
        }
    }

    private synchronized void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < this.n; i++) {
            while (this.first) {
                wait();
            }
            this.first = true;
            notifyAll();
            printBar.run();
        }
    }

    // Code for tests
    public void foBarTest() throws InterruptedException {

        // Initialise the Runnable for each method in FooBar
        Runnable printFoo = () -> System.out.print("Foo");
        Runnable printBar = () -> System.out.print("Bar");

        fooBarExecutorService.execute(() -> {
            try {
                this.bar(printBar);
            } catch (InterruptedException _) {
                LOGGER.warning(EXCEPTION);
                Thread.currentThread().interrupt();
            }
        });
        fooBarExecutorService.execute(() -> {
            try {
                this.foo(printFoo);
            } catch (InterruptedException _) {
                LOGGER.warning(EXCEPTION);
                Thread.currentThread().interrupt();
            }
        });

        var _ = fooBarExecutorService.awaitTermination(3, TimeUnit.SECONDS);
        fooBarExecutorService.shutdown();
    }
}
