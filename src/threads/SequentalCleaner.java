package threads;

import interfaces.Floor;
import interfaces.Space;

public class SequentalCleaner implements Runnable {
    private Floor floor;
    private mySemaphore sem;

    public SequentalCleaner(Floor floor, mySemaphore sem) {
        this.sem = sem;
        this.floor = floor;
        new Thread(this).start();
    }

    @Override
    public void run() {
          System.out.println("Stream SequentalCleaner gets permission");

        int spacesAmount = floor.getAmountSpaces();
        Space[] spaces = floor.getMassSpace();
        for (int i = 0; i < spacesAmount; i++) {
            sem.acquire(this);
            System.out.printf("Cleaning space number %d with total area %s square meters%n", i, spaces[i].getSize());
            System.out.println("Stream SequentalCleaner frees permission");
            sem.release(this);
        }



    }

}
