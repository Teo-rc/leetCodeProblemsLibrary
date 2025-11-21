package problems.concurrency.printinorder;

public class Foo {

    private boolean first = false;
    private boolean second = false;

    public synchronized void first(Runnable printFirst) {
        printFirst.run();
        this.first = true;
        notifyAll();
    }

    public synchronized void second(Runnable printSecond) throws InterruptedException {
        while (!this.first) {
            this.wait();
        }
        printSecond.run();
        this.second = true;
        notifyAll();
    }

    public synchronized void third(Runnable printThird) throws InterruptedException {
        while (!this.second) {
            this.wait();
        }
        printThird.run();
    }
}