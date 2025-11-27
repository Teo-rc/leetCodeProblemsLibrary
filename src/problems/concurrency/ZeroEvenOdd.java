package problems.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.IntConsumer;
import java.util.logging.Logger;

public class ZeroEvenOdd {

    private static final Logger LOGGER = Logger.getLogger(ZeroEvenOdd.class.getName());
    private final ExecutorService executor = Executors.newFixedThreadPool(3);
    private static final String EXCEPTION = "Exception";

    private final int maxLength;
    private int current = 1;
    private int length = 0;

    public ZeroEvenOdd(int n) {
        this.maxLength = n * 2;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public synchronized void zero(IntConsumer printNumber) throws InterruptedException {
        while (this.length < this.maxLength) {
            if (this.length % 2 == 0) {
                this.length++;
                notifyAll();
                printNumber.accept(0);
            } else {
                wait(100);
            }
        }
    }

    public synchronized void even(IntConsumer printNumber) throws InterruptedException {
        while (this.length < this.maxLength) {
            if (this.length % 4 == 3) {
                this.length++;
                printNumber.accept(this.current);
                this.current++;
                notifyAll();
            } else {
                wait(100);
            }
        }
    }

    public synchronized void odd(IntConsumer printNumber) throws InterruptedException {
        while (this.length < this.maxLength) {
            if (this.length % 2 == 1 && this.current % 2 == 1) {
                this.length++;
                printNumber.accept(this.current);
                this.current++;
                notifyAll();
            } else {
                wait(100);
            }
        }
    }

    // Code for tests
    public void zeroEvenOddTest(int n) throws InterruptedException {

        // Initialise Test object
        var testObject = new ZeroEvenOdd(n);

        IntConsumer intConsumer = (int value) -> {
            var msg = String.valueOf(value);
            System.out.print(msg);
        };

        executor.execute(() -> {
            try {
                testObject.zero(intConsumer);
            } catch (InterruptedException _) {
                LOGGER.warning(EXCEPTION);
                Thread.currentThread().interrupt();
            }
        });

        executor.execute(() -> {
            try {
                testObject.even(intConsumer);
            } catch (InterruptedException _) {
                LOGGER.warning(EXCEPTION);
                Thread.currentThread().interrupt();
            }
        });

        executor.execute(() -> {
            try {
                testObject.odd(intConsumer);
            } catch (InterruptedException _) {
                LOGGER.warning(EXCEPTION);
                Thread.currentThread().interrupt();
            }
        });

        executor.awaitTermination(10, TimeUnit.SECONDS);
    }
}