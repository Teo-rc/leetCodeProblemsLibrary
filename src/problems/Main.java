package problems;

import problems.concurrency.Foo;
import problems.concurrency.FooBar;
import problems.concurrency.ZeroEvenOdd;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        // Initialise the test Foo object
        System.out.println("Starting foo test case execution");
        var fooTestCase = new int[]{2, 0, 1};
        var foo = new Foo();
        foo.fooTest(fooTestCase);


        System.out.println();
        System.out.println("Starting fooBar test case execution");
        // Initialise the test FooBar object
        var fooBarTestCase = 3;
        var fooBar = new FooBar(fooBarTestCase);
        fooBar.foBarTest();

        // Initialise the zero odd even test
        System.out.println();
        System.out.println("Starting Zero Even Odd test case execution");
        var zeroEvenOdd = 90;
        var zeroEven = new ZeroEvenOdd(zeroEvenOdd);
        zeroEven.zeroEvenOddTest(zeroEvenOdd);
    }
}