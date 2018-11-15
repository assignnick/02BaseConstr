package buildings.threads;

import interfaces.Floor;
import interfaces.Space;

import java.util.concurrent.Semaphore;

public class SequentalRepairer implements Runnable {
    private Floor floor;
    Semaphore sem;

    public SequentalRepairer(Floor floor,Semaphore s) {
        sem = s;
        this.floor = floor;
        new Thread(this).start();
    }
    public void run() {
        System.out.println("Start stream SequentalRepairer");
        try {

            System.out.println("Stream SequentalRepairer waiting for permission");
            sem.acquire();
            System.out.println("Stream SequentalRepairer gets permission");

            int spacesAmount = floor.getAmountSpaces();
            Space[] spaces = floor.getMassSpace();
            for(int i = 0; i < spacesAmount; i++) {
                System.out.println("Repairing space number " + i + " with total area " +
                        spaces[i].getSize() + " square meters");
                Thread.sleep(10);
            }
        } catch (InterruptedException exc) {
            System.out.println(exc);
        }

        // освободить разрешение
        System.out.println("Stream SequentalRepairer frees permission");
        sem.release();
    }

}
