package buildings.threads;

import interfaces.Floor;
import interfaces.Space;

import java.util.concurrent.Semaphore;

public class SequentalCleaner implements Runnable {
    private Floor floor;
    Semaphore sem;

    public SequentalCleaner(Floor floor,Semaphore s) {
        sem = s;
        this.floor = floor;
        new Thread(this).start();
    }



    public void run() {
        System.out.println("Start stream SequentalCleaner");
        try {

            System.out.println("Stream SequentalCleaner waiting for permission");
            sem.acquire();
            System.out.println("Stream SequentalCleaner gets permission");

            int spacesAmount = floor.getAmountSpaces();
            Space[] spaces = floor.getMassSpace();
            for(int i = 0; i < spacesAmount; i++) {
                System.out.println("Cleaning space number " + i + " with total area "
                        + spaces[i].getSize() + " square meters");
                Thread.sleep(10);
            }
        } catch (InterruptedException exc) {
            System.out.println(exc);
        }

        // освободить разрешение
        System.out.println("Stream SequentalCleaner frees permission");
        sem.release();
    }

}
