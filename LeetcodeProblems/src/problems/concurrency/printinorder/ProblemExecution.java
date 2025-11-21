package problems.concurrency.printinorder;

public class ProblemExecution {
    public static void main(String[] args) {
        var fooTestCase = new int[]{2, 0, 1};
        fooTest(fooTestCase);
    }

    private static void fooTest(int[] testCase) {
        // Initialise Test Foo Object
        var testObject = new Foo();

        // Initialise Runnable for each function in Foo
        var printFirst = new Runnable() {
            @Override
            public void run() {
                System.out.print("first");
            }
        };
        var printSecond = new Runnable() {
            @Override
            public void run() {
                System.out.print("second");
            }
        };
        var printThird = new Runnable() {
            @Override
            public void run() {
                System.out.print("third");
            }
        };

        // Set up the three threads for the test
        var thread1  = new Thread(() -> {testObject.first(printFirst);});
        var thread2  = new Thread(() -> {
            try {
                testObject.second(printSecond);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        var thread3  = new Thread(() -> {
            try {
                testObject.third(printThird);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        // Start each Thread in the order of the test case
        for (int j : testCase) {
            if (j == 0) {
                thread1.start();
            } else if (j == 1) {
                thread2.start();
            } else {
                thread3.start();
            }
        }
    }

    private static void foBarTest(int n) {
        // Initialise the test FooBar object
        var testObject = new FooBar(n);

        // Initialise the Runnable for each method in FooBar
        var printFoo = new Runnable() {
            @Override
            public void run() {
                System.out.print("Foo");
            }
        };
        var printBar = new Runnable() {
            @Override
            public void run() {
                System.out.print("Bar");
            }
        };

        // Prepare the threads
        var thread1  = new Thread(() -> {
            try {
                testObject.foo(printFoo);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        var thread2  = new Thread(() -> {
            try {
                testObject.bar(printBar);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });


    }
}