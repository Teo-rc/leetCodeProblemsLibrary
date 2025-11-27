package problems.concurrency;

class H2O {

    private String current_atom_structure = "";

    public synchronized void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        while (countHydrogen() == 2) {
            wait(100);
        }
        this.current_atom_structure = this.current_atom_structure.length() == 2 ? "" : this.current_atom_structure + "H";
        notifyAll();
        releaseHydrogen.run();
    }

    public synchronized void oxygen(Runnable releaseOxygen) throws InterruptedException {
        while (countOxygen() == 1) {
            wait(100);
        }
        this.current_atom_structure = this.current_atom_structure.length() == 2 ? "" : this.current_atom_structure + "O";
        notifyAll();
        releaseOxygen.run();
    }
    private long countHydrogen() {
        return this.current_atom_structure.chars().filter(ch -> ch == 72).count();
    }

    private long countOxygen() {
        return this.current_atom_structure.chars().filter(ch -> ch == 79).count();
    }
}