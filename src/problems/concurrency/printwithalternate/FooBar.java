package problems.concurrency.printwithalternate;

public class FooBar {
    private final int n;
    private boolean first = true;

    public FooBar(int n) {
        this.n = n;
    }

    public synchronized void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < this.n; i++) {
            while (!this.first) {
                wait();
            }
            this.first = false;
            notifyAll();
            printFoo.run();
        }
    }

    public synchronized void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < this.n; i++) {
            while (this.first) {
                wait();
            }
            this.first = true;
            notifyAll();
            printBar.run();
        }
    }
}
