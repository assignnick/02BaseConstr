package threads;

import interfaces.Floor;
import interfaces.Space;


public class SequentalRepairer implements Runnable {
    private Floor floor;
    mySemaphore sem;

    public SequentalRepairer(Floor floor, mySemaphore sem) {
        this.sem = sem;
        this.floor = floor;
        new Thread(this).start();
    }

    public void run() {

        System.out.println("Stream SequentalRepairer gets permission");

        int spacesAmount = floor.getAmountSpaces();
        Space[] spaces = floor.getMassSpace();
        for (int i = 0; i < spacesAmount; i++) {
            sem.acquire(this);
            System.out.printf("Repairing space number %d with total area %s square meters%n", i, spaces[i].getSize());
            System.out.println("Stream SequentalRepairer frees permission");
            sem.release(this);
        }
    }
}
