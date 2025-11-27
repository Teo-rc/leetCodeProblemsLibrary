package problems.concurrency;

public class DiningPhilosophers {

    private int[] state = {1, 1, 1, 1, 1};

    // call the run() method of any runnable to execute its code
    public synchronized void wantsToEat(int philosopher,
                                        Runnable pickLeftFork,
                                        Runnable pickRightFork,
                                        Runnable eat,
                                        Runnable putLeftFork,
                                        Runnable putRightFork) throws InterruptedException {
        int leftIndex = (philosopher + 4) % 5;
        int rightIndex = (philosopher + 1) % 5;
        while (!forksReady(leftIndex, rightIndex)) {
            wait(100);
        }
        pickForks(leftIndex, rightIndex, pickLeftFork, pickRightFork);
        eat.run();
        dropForks(leftIndex, rightIndex, putLeftFork, putRightFork);
        notifyAll();
    }

    private boolean forksReady(int leftIndex,
                               int rightIndex) {
        return state[leftIndex] == 1 &&  state[rightIndex] == 1;
    }

    private void pickForks(int leftIndex,
                           int rightIndex,
                           Runnable pickLeftFork,
                           Runnable pickRightFork) {
        state[leftIndex] = 0;
        state[rightIndex] = 0;
        pickLeftFork.run();
        pickRightFork.run();
    }

    private void dropForks(int leftIndex,
                           int rightIndex,
                           Runnable putLeftFork,
                           Runnable putRightFork) {
        state[leftIndex] = 1;
        state[rightIndex] = 1;
        putLeftFork.run();
        putRightFork.run();
    }
}