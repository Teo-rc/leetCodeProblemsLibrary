package problems.concurrency;


import java.util.logging.Logger;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws InterruptedException {

        var testExecutor = new ProblemExecutor();
        var fooTestCase = new int[]{2, 0, 1};
        LOGGER.info("Starting foo test case execution");
        testExecutor.fooTest(fooTestCase);

        var fooBarTestCase = 3;
        LOGGER.info("Starting fooBar test case execution");
        testExecutor.foBarTest(fooBarTestCase);
    }

}
