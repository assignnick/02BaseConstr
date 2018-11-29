package buildings.threads;

import interfaces.Floor;
import interfaces.Space;

public class SequentalCleaner implements Runnable {
    private Floor floor;
    private mySemaphore semaphore;

    public SequentalCleaner(Floor floor, mySemaphore semaphore) {
        this.semaphore = semaphore;
        this.floor = floor;
        new Thread(this).start();
    }


    public void run() {
          System.out.println("Stream SequentalCleaner gets permission");

        int spacesAmount = floor.getAmountSpaces();
        Space[] spaces = floor.getMassSpace();
        for (int i = 0; i < spacesAmount; i++) {
            semaphore.acquire(this);
            System.out.printf("Cleaning space number %d with total area %s square meters%n", i, spaces[i].getSize());
            System.out.println("Stream SequentalCleaner frees permission");
            semaphore.release(this);
        }



    }

}
