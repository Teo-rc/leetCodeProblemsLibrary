package problems.concurrency.builH2O;

class H2O {

    private String current_atom_structure = "";

    public synchronized void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        while (countHydrogen() == 2) {
            wait(100);
        }
        this.current_atom_structure = this.current_atom_structure + "H";
        checkAtomComplete();
        releaseHydrogen.run();
        notifyAll();
    }

    public synchronized void oxygen(Runnable releaseOxygen) throws InterruptedException {
        while (countOxygen() == 1) {
            wait(100);
        }
        this.current_atom_structure = this.current_atom_structure + "O";
        checkAtomComplete();
        releaseOxygen.run();
        notifyAll();
    }
    private long countHydrogen() {
        return this.current_atom_structure.chars().filter(ch -> ch == 72).count();
    }

    private long countOxygen() {
        return this.current_atom_structure.chars().filter(ch -> ch == 79).count();
    }

    private void checkAtomComplete() {
        if (this.current_atom_structure.length() == 3) {
            this.current_atom_structure = "";
        }
    }
}