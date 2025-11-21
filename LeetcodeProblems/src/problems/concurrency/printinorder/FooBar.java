package problems.concurrency.printinorder;

public class FooBar {
    private int n;
    private static boolean first = true;

    public FooBar(int n) {
        this.n = n;
    }

    public synchronized void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            while (!first) {
                wait();
            }
            notifyAll();
            printFoo.run();
            first = false;
        }
    }

    public synchronized void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            while (first) {
                wait();
            }
            notifyAll();
            printBar.run();
            first = true;
        }
    }
}
