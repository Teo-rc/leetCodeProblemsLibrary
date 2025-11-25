package problems.concurrency.fizzBuzz;

import java.util.function.IntConsumer;

public class FizzBuzz {
    private final int n;
    private int index = 1;

    public FizzBuzz(int n) {
        this.n = n + 1;
    }

    // printFizz.run() outputs "fizz".
    // output fizz when index is divisible by 3
    public synchronized void fizz(Runnable printFizz) throws InterruptedException {
        while (index < n) {
            if (index % 3 == 0 &&  index % 5 != 0) {
                printFizz.run();
                index++;
                notifyAll();
            } else {
                wait(100);
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    // output buzz when index is divisible by 5
    public synchronized void buzz(Runnable printBuzz) throws InterruptedException {
        while (index < n) {
            if (index % 3 != 0 &&  index % 5 == 0) {
                printBuzz.run();
                index++;
                notifyAll();
            } else {
                wait(100);
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    // output fizzbuzz when index is divisible by 3 and 5
    public synchronized void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (index < n) {
            if (index % 15 == 0) {
                printFizzBuzz.run();
                index++;
                notifyAll();
            } else {
                wait(100);
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    // output index when index is not divisible by 3 or 5
    public synchronized void number(IntConsumer printNumber) throws InterruptedException {
        while (index < n) {
            if (index % 3 != 0 &&  index % 5 != 0) {
                printNumber.accept(index);
                index++;
                notifyAll();
            } else {
                wait(100);
            }
        }
    }
}