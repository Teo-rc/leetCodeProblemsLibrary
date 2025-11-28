package problems;

import problems.arrays.sums.SubarraySumWithLengthCondition;
import problems.concurrency.Foo;
import problems.concurrency.FooBar;
import problems.concurrency.ZeroEvenOdd;


public class Main {
    private static final boolean runConcurrency = false;
    private static final boolean runArrays = true;

    public static void main(String[] args) throws InterruptedException {

        if (runConcurrency) {
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
        if (runArrays) {
            System.out.println();
            System.out.println("Starting Max Subarray Sum test case execution");

            int[] nums = {-17,17,6};
            int k = 1;
            SubarraySumWithLengthCondition.testMaxSubarraySum(nums, k);
        }
    }
}