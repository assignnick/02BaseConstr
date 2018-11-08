package buildings.threads;

import interfaces.Floor;
import interfaces.Space;

public class Cleaner extends Thread{
    protected Floor floor;

    public Cleaner(Floor floor) {
        this.floor = floor;
    }

    public void run() {
        int spacesAmount = floor.getAmountSpaces();
        Space[] spaces = floor.getMassSpace();
        for(int i = 0; i < spacesAmount; i++) {
            System.out.println("Cleaning space number " + i + " with total area " + spaces[i].getSize() + " square meters");
        }
    }
}
